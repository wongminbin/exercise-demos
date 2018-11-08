package com.phs.esl.canal.database.entry.parse.impl;

import org.apache.commons.lang.StringUtils;

import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;

/**
 * @author HuangZhibin
 * 
 *         2018年10月30日 下午1:52:00
 */
public class ParseInsertStatement implements ParseStatement {

	private static final String INSERT_TEMP = "INSERT INTO %s ( %s ) VALUES ( %s )";

	@Override
	public String parse(MyTable table) {
		return String.format(INSERT_TEMP, table.getTableName(),
				StringUtils.join(table.getColumns().stream().map(v -> v.getName()).iterator(), ", "),
				StringUtils.join(table.getColumns().stream().map(v -> v.getValue()).iterator(), ", "));
	}

}
