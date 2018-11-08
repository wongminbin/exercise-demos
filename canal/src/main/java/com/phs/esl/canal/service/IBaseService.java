package com.phs.esl.canal.service;

import com.phs.esl.canal.core.ILogger;

/**
* @author HuangZhibin
* 
* 2018年10月26日 上午9:10:31
*/
public interface IBaseService<T> extends ILogger{

	/**
	 * execute sql
	 * @param sql
	 */
	void execute(String sql);
	
	/**
	 * execute vo
	 * @param t
	 */
	void execute(T t);
	
}
