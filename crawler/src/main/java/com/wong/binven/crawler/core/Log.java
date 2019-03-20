package com.wong.binven.crawler.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 上午10:10:46
 */
public interface Log {

	default Logger getLog() {
		return LoggerFactory.getLogger(getClass());
	}
}
