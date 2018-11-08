package com.phs.esl.canal.slave;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.service.IBaseService;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HuangZhibin
 * 
 *         2018年10月26日 下午1:43:48
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "canal.client")
public class CanalConfig {

	private String ip;

	private String destination;
	
	private String remoteSchema;

	private int port;
	
	private int batchSize;

	public CanalConfig() {}

	@Bean(name="canalClient")
	@Primary
	public CanalClient canalClient(
			@Autowired IBaseService<MyTable> ddlService, @Autowired IBaseService<MyTable> insertService, 
			@Autowired IBaseService<MyTable> updateService, @Autowired IBaseService<MyTable> deleteService) {
		
		Map<EventType, IBaseService<MyTable>> commonds = new HashMap<>(5);
		
		// DML
		commonds.put(EventType.INSERT, insertService);
		commonds.put(EventType.UPDATE, updateService);
		commonds.put(EventType.DELETE, deleteService);
		
		// DDL
		commonds.put(EventType.CREATE, ddlService);
		commonds.put(EventType.ALTER,  ddlService);
		
		CanalClient canalClient = new CanalClient(commonds);
		canalClient.starting(ip, port, destination, batchSize, remoteSchema);
		
		return canalClient;
	}
	

}
