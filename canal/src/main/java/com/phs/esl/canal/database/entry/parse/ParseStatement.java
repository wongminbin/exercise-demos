package com.phs.esl.canal.database.entry.parse;

import com.phs.esl.canal.database.entry.MyTable;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午1:51:11
*/
public interface ParseStatement {

	/**
	 * parse tabel to sql
	 * @param table
	 * @return
	 */
	String parse(MyTable table);
}
