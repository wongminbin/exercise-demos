package com.wong.binven.demo.config;

import java.util.Map;

import org.springframework.core.env.EnumerablePropertySource;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 上午11:37:10
 */
public class ConfigSource extends EnumerablePropertySource<Map<String, String>> {

	public ConfigSource(String name, Map<String, String> source) {
		super(name, source);
	}

	@Override
	public String[] getPropertyNames() {
		return source.keySet().toArray(new String[source.size()]);
	}

	@Override
	public Object getProperty(String name) {
		return source.get(name);
	}

	/** 允许动态修改配置 */
	public void setProperty(String name, String value) {
		source.put(name, value);
	}
}
