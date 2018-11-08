package com.wong.binven.demo.springboot.druid;

import com.alibaba.druid.pool.xa.DruidXADataSource;

/**
 * create by: HuangZhiBin
 * 2018年11月5日 下午2:26:37
 */
public class DruidXADataSourceBuilder {

	public static DruidXADataSourceBuilder create() {
        return new DruidXADataSourceBuilder();
    }

    /**
     * For build multiple DruidDataSource, detail see document.
     */
    public DruidXADataSource build() {
        return new DruidXADataSourceWrapper();
    }
}
