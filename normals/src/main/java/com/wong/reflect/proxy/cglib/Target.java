package com.wong.reflect.proxy.cglib;

/**
* @author Huang zhibin
* 
* 2018年9月14日 上午9:40:11
*/
public class Target {

	public String running() {
		System.out.println("================cglib target running");
		return "running";
	}
}
