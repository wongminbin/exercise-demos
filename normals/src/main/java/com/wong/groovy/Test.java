package com.wong.groovy;

/**
* @author Huang zhibin
* 
* 2017年9月11日 上午10:12:04
*/
public class Test {

	public String invoke(Integer userId, TestDto dto) {
		System.out.println("--------------test method invoke");
		
		return String.format("userId : %d, userName is %s, age is %d", userId, dto.getName(), dto.getAge());
	}
}
