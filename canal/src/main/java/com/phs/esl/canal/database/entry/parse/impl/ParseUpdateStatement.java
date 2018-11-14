package com.phs.esl.canal.database.entry.parse.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.phs.esl.canal.database.entry.MyColumn;
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
						.map(v -> v.getName() + " = " + v.getValueFormatStr()).iterator(), ", "),
				StringUtils.join(table.getColumns().stream().filter(v -> v.isKey())
						.map(v -> v.getName() + " = " + v.getValueFormatStr()).iterator(), " AND "));
	}

	@Override
	public Map<String, Object> parsePlaceholder(MyTable table) {
		Map<String, Object> placeholdMap = new HashMap<>();
		// 判断更新的列是否与查询的列相同 update table set name = 'lisi' where name = 'zhangsan'
		// 则占位符标识需要不同 update table set name = #{name1} where name = #{name2} or name = #{name3}
		List<MyColumn> updates = table.getColumns().stream().filter(v -> v.isUpdate()).collect(Collectors.toList());
		List<MyColumn> wheres = table.getColumns().stream().filter(v -> v.isKey()).collect(Collectors.toList());
		
		
		final AtomicInteger i = new AtomicInteger();
		String sql = String.format(UPDATE_TEMP, table.getTableName(),
				StringUtils.join(updates.stream().map(v -> {
					int index = i.getAndIncrement();
					placeholdMap.put(v.name(index), v.getValue());
					
					return v.getName() + " = " + v.namePlaceholder(index);
				}).iterator(), ", "),
				
				StringUtils.join(wheres.stream().map(v -> {
					int index = i.getAndIncrement();
					placeholdMap.put(v.name(index), v.getValue());
							
					return v.getName() + " = " + v.namePlaceholder(index);
				}).iterator(), " AND "));
		
		placeholdMap.put("UPDATE_SQL", sql);
		
		
		return placeholdMap;
	}

}
