package com.phs.esl.canal.database.entry.parse.impl;

import org.apache.commons.lang.StringUtils;

import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午1:52:00
*/
public class ParseDeteleStatement implements ParseStatement {
	
	/** 修改SQL，delete转update */
	private static final String DELETE_TEMP = "UPDATE %s SET update_user = -1, update_time = NOW() WHERE %s";

	@Override
	public String parse(MyTable table) {
		return String.format(DELETE_TEMP, table.getTableName(),
				StringUtils.join(table.getColumns().stream().filter(v -> v.isKey())
						.map(v -> v.getName() + " = " + v.getValue()).iterator(), " AND "));
	}
	
}
