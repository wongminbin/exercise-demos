package com.phs.esl.canal.database.entry.parse;

import java.util.Map;

import com.phs.esl.canal.database.entry.MyTable;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午1:51:11
*/
public interface ParseStatement {

	/**
	 * parse tabel to sql
	 * <pre>insert into table(name, age) values('lisi', 28)</pre>
	 * @param table
	 * @return
	 */
	String parse(MyTable table);
	
	/**
	 * parse tabel to placeholder sql
	 * <pre>sql:insert into table(name, age) values(#{name}, #{age})</pre>
	 * <pre>name:lisi</pre>
	 * <pre>age:28</pre>
	 * @author HuangZhibin
	 *
	 * @param table
	 * @return
	 */
	Map<String, Object> parsePlaceholder(MyTable table);
}
