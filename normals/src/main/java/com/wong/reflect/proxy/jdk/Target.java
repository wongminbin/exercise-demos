package com.wong.reflect.proxy.jdk;

/**
* @author Huang zhibin
* 
* 2018年9月14日 上午9:41:45
*/
public class Target implements ITarget {

	@Override
	public String running() {
		System.out.println("==================jdk target running");
		return "running";
	}

}
