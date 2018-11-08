package com.wong.binven.demo.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:21:18
 */

@Configuration
public class SystemMybatisConfig {
	
	@Bean(name="dataSource", initMethod="init", destroyMethod="close")
	@ConfigurationProperties("spring.datasource.druid.sys")
	public DruidDataSource druidXADataSource() {
		return DruidDataSourceBuilder.create().build();
	}

}
