package com.wong.binven.demo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:03:47
 */
public interface Log {

	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}
}
