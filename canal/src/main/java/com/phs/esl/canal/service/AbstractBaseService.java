package com.phs.esl.canal.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.phs.esl.canal.dao.IBaseDao;
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
	 * get dao
	 * @author HuangZhibin
	 *
	 * @return
	 */
	protected abstract IBaseDao getDao();
	
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
	
	/**
	 * 
	 * @author HuangZhibin
	 *
	 * @param holders
	 */
	protected abstract void executePlaceholder(Map<String, Object> holders);
	
	@Override
	public void execute(MyTable table) {
		if (isExecute(table)) {
			Map<String, Object> placeholders = getParse().parsePlaceholder(table);
			executePlaceholder(placeholders);
		}
		
	}
}
