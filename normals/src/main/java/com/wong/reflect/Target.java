package com.wong.reflect;

/**
* @author Huang zhibin
* 
* 2017年11月14日 下午4:34:07
*/
public class Target {

	//@Override
	public String run() {
		System.out.println("this is target run method...");
		return "test";
	}
	
	//@Override
	public void test() {
		System.out.println("test method running...");
	}
}
