package com.phs.esl.canal.service;

import org.springframework.transaction.annotation.Transactional;

import com.phs.esl.canal.database.entry.MyTable;
import com.phs.esl.canal.database.entry.parse.ParseStatement;

/**
* @author HuangZhibin
* 
* 2018年10月30日 下午4:21:26
*/

@Transactional(rollbackFor = Exception.class)
public abstract class AbstractBaseService implements IBaseService<MyTable> {

	/**
	 * get parse
	 * @return
	 */
	protected abstract ParseStatement getParse();
	
	/**
	 * is execute
	 * @param table
	 * @return
	 */
	protected abstract boolean isExecute(MyTable table);
	
	@Override
	public void execute(MyTable table) {
		if (isExecute(table)) {
			String sql = getParse().parse(table);
			execute(sql);
		}
		
	}
}
