package com.phs.esl.canal.slave;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.Assert;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;
import com.phs.esl.canal.core.ILogger;
import com.phs.esl.canal.database.entry.MyColumn;
import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.service.IBaseService;

/**
 * @author HuangZhibin
 * 
 *         2018年10月26日 下午1:43:48
 */

public class CanalClient implements ILogger {

	private final Map<EventType, IBaseService<MyTable>> commonds;
	
	private final Map<EventType, Function<RowData, List<Column>>> columns = new HashMap<>();
	
	{
		columns.put(EventType.INSERT, (row) -> row.getAfterColumnsList());
		columns.put(EventType.UPDATE, (row) -> row.getAfterColumnsList());
		columns.put(EventType.DELETE, (row) -> row.getBeforeColumnsList());
	}
	
	public CanalClient(Map<EventType, IBaseService<MyTable>> commonds) {
		this.commonds = commonds;
	}

	/**
	 * 
	 * @author HuangZhibin
	 *
	 * @param ip
	 * @param port
	 * @param destination
	 * @param batchSize
	 * @param remoteSchema
	 */
	public void starting(String ip, int port, String destination, int batchSize, String remoteSchema) {
		
		
		Executors.newSingleThreadScheduledExecutor().schedule(() -> {
			
			CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, port), destination, "", "");
			try {
				connector.connect();
				connector.subscribe(".*\\..*");
				connector.rollback();
				while (true) {
					// 获取指定数量的数据
					Message message = connector.getWithoutAck(batchSize); 
					long batchId = message.getId();
					int size = message.getEntries().size();
					if (batchId == -1 || size == 0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {}
					} else {
						getLogger().info("{} =======================>> {}", batchId, size);
						syncData(message.getEntries(), remoteSchema);
					}
					// 提交确认
					connector.ack(batchId); 
				}
			} finally {
				connector.disconnect();
			}
		}, 10, TimeUnit.SECONDS);
		
	}
	
	private ExecutorService pools = Executors.newFixedThreadPool(512);

	private void syncData(List<Entry> entrys, String remoteSchema) {
		for (Entry entry : entrys) {
			if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
					|| entry.getEntryType() == EntryType.TRANSACTIONEND
					|| !StringUtils.equals(remoteSchema, entry.getHeader().getSchemaName())) {
				continue;
			}

			RowChange row = null;
			try {
				row = RowChange.parseFrom(entry.getStoreValue());
			} catch (Exception e) {
				getLogger().error("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
			}
			Assert.notNull(row, "ERROR ROW DATA IS NULL");
			getLogger().info("================>> binlog[{}:{}:{}] , name[{},{}] , eventType : {}",
					entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), 
					DateFormatUtils.format(entry.getHeader().getExecuteTime(), "yyyy-MM-dd HH:mm:ss"),
					entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), row.getEventType());
			
			EventType eventType = row.getEventType();
			if (row.getIsDdl()) {
				// DDL
				String sql = row.getSql();
				pools.execute(() -> Optional.ofNullable(commonds.get(eventType)).ifPresent(cons -> cons.execute(sql)));
			} else {
				// DML
				for (RowData rowData : row.getRowDatasList()) {
					
					MyTable table = new MyTable();
					table.setTableName(entry.getHeader().getTableName());
					table.setColumns(transferColumn(columns.get(eventType).apply(rowData)));
					
					pools.execute(() -> Optional.ofNullable(commonds.get(eventType)).ifPresent(cons -> cons.execute(table)));
				}
			}
		}
	}

	private List<MyColumn> transferColumn(List<Column> columns) {
		// 过滤掉字段允许为NULL且无值的列
		return columns.stream().filter(col -> !(col.getIsNull() && StringUtils.isEmpty(col.getValue()))).map(col -> {
			MyColumn column = new MyColumn();
			column.setName(col.getName());
			column.setValue(col.getValue());
			column.setKey(col.getIsKey());
			column.setUpdate(col.getUpdated());
			column.setMysqlType(col.getMysqlType());

			return column;
		}).collect(Collectors.toList());
	}
}
