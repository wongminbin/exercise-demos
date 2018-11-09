package com.phs.esl.canal.database.entry;

import java.util.function.Function;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午1:38:08
*/

@Getter
@Setter
public class MyColumn {

	/** 是否是主键 */
	private boolean key;
	
	/** 值是否有变化 */
	private boolean update;
	
	/** 列名 */
	private String name;
	
	/** 值 */
	private String value;
	
	/** 字段类型 varchar/int/datetime */
	private String mysqlType;
	
	public String getValue() {
		return MysqlType.getMysqlType(mysqlType).function.apply(value);
	}
	
	public enum MysqlType {
		/** 数值类型 */
		NUMBER((v) -> v, "bit", "tinyint", "smallint", "mediumint", "int", "integer", "bigint", "float", "double", "decimal"),
		/** 字符类型 */
		STRING((v) -> String.format("'%s'", v), "char", "varchar", "tinyblob", "blob", "mediumblob", "longblob", "tinytext", "mediumtext", "text", "longtext"),
		/** 日期类型 */
		DATETIME((v) -> String.format("'%s'", v), "datetime", "timestamp", "year", "date", "time");
		
		private String[] types;
		
		private Function<String, String> function;
		
		private MysqlType(Function<String, String> function, String... types) {
			this.function = function;
			this.types = types;
		}
		
		public static MysqlType getMysqlType(String type) {
			for (MysqlType mysqlType : values()) {
				if (StringUtils.startsWithAny(type, mysqlType.types)) {
					return mysqlType;
				}
			}
			return STRING;
		}
	}
	
}
