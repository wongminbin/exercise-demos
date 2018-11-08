package com.phs.esl.canal.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author HuangZhibin
* 
* 2018年10月26日 下午1:04:46
*/
public interface ILogger {

	/**
	 *<pre> 默认日志输出类</pre>
	 * @return
	 */
	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}
