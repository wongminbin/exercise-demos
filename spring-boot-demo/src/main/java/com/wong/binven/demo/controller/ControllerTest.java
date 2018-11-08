package com.wong.binven.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wong.binven.demo.core.Log;
import com.wong.binven.demo.service.IService;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:13:11
 */

@RestController
public class ControllerTest implements Log {

	@Autowired
	private ConfigTest configTest;
	
	@Autowired
    private ConfigurableEnvironment env;
	
	@Autowired
	private IService service;
	
	@RequestMapping("/test")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConfigTest test() {
		PropertySource<?> source = env.getPropertySources().get("custom");
		getLogger().info(JSON.toJSONString(source.getSource()));
		
		// 动态修改
		Map<String, String> map = (Map)source.getSource();
		map.put("custom.random", String.valueOf(Math.random()));
		
		getLogger().info("=======================");
		return configTest;
	}
	
	@RequestMapping("/test1")
	public void test1() {
		service.execute();
	}
	
	@RequestMapping("/session")
	public void session(HttpServletRequest request) {
		// 生成Session同时保存到持久化存储，并返回到response
		HttpSession session = request.getSession();
		getLogger().info(JSON.toJSONString(session));
	}
}
