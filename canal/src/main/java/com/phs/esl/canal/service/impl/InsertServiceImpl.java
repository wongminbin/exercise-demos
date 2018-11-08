package com.phs.esl.canal.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phs.esl.canal.dao.IBaseDao;
import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;
import com.phs.esl.canal.database.entry.parse.impl.ParseInsertStatement;
import com.phs.esl.canal.service.AbstractBaseService;
import com.phs.esl.canal.service.IBaseService;

/**
* @author HuangZhibin
* 
* 2018年10月26日 上午9:11:02
*/

@Service("insertService")
@Transactional(rollbackFor = Exception.class)
public class InsertServiceImpl extends AbstractBaseService implements IBaseService<MyTable> {

	@Autowired
	private IBaseDao baseDao;
	
	private ParseStatement parse = new ParseInsertStatement();
	
	@Override
	public void execute(String sql) {
		getLogger().debug(sql);
		try {
			baseDao.insertCommondSql(sql);
		} catch (Exception e) {
			getLogger().error(sql, e);
		}
	}

	@Override
	protected ParseStatement getParse() {
		return parse;
	}

	private static String COUNT_TEMP = "SELECT COUNT(*) count FROM %s WHERE %s";
	
	@Override
	protected boolean isExecute(MyTable table) {
		String sql = String.format(COUNT_TEMP, table.getTableName(),
				StringUtils.join(table.getColumns().stream().filter(v -> v.isKey())
						.map(v -> v.getName() + " = " + v.getValue()).iterator(), " AND "));
		Map<String, Object> result = baseDao.selectCommondSql(sql);
		
		int count = NumberUtils.toInt(String.valueOf(result.get("count")));
		return count == 0;
	}
	
}