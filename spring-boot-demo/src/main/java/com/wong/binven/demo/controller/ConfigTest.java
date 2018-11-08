package com.wong.binven.demo.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:13:29
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "custom")
public class ConfigTest {

	private String test;
	
	private String name;
	
	private String address;
	
	private int age;
}
