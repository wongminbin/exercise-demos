package com.wong.test;

/**
* @author Huang zhibin
* 
* 2017年8月19日 下午12:01:47
*/
public class Son extends Person {

	private String name = "son";
	
	@Override
	public String getName() {
		return name;
	}
	
	public void work(String arg, String arg1) {
		System.out.println(String.format("Son work method , arg is %s, %s", arg, arg1));
	}

	public void work(String str) {
		System.out.println("Son work method , arg is " + str);
	}
}
