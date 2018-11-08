package com.phs.esl.canal.database.entry.parse.impl;

import org.apache.commons.lang.StringUtils;

import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;

/**
 * @author HuangZhibin
 * 
 *         2018年10月30日 下午1:52:00
 */
public class ParseUpdateStatement implements ParseStatement {

	private static final String UPDATE_TEMP = "UPDATE %s SET %s WHERE %s";

	@Override
	public String parse(MyTable table) {
		return String.format(UPDATE_TEMP, table.getTableName(),
				StringUtils.join(table.getColumns().stream().filter(v -> v.isUpdate())
						.map(v -> v.getName() + " = " + v.getValue()).iterator(), ", "),
				StringUtils.join(table.getColumns().stream().filter(v -> v.isKey())
						.map(v -> v.getName() + " = " + v.getValue()).iterator(), " AND "));
	}

}
