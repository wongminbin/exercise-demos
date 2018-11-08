package com.wong.lombok;

import lombok.Builder;
import lombok.Getter;

/**
* @author Huang zhibin
* 
* 2017年10月31日 下午4:34:18
*/

@Builder
@Getter
public class BuilderTest {

	private String name;
	private String address;
	private int age;
	private String school;
	
	public static void main(String[] args) {
		BuilderTest test = BuilderTest.builder().address("").build();
		
		test.getAddress();
	}
}
