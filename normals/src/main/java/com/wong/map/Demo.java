package com.wong.map;

import org.junit.Test;

import com.wong.lombok.Person;

/**
* @author HuangZhibin
* 
* 2018年7月16日 上午9:11:42
*/
public class Demo {

	@Test
	public void test() {
		MyMap map = new MyMap();
		map.put(MyKey.create("string", String.class), "this is String");
		map.put(MyKey.create("integer", Integer.class), 1);
		map.put(MyKey.create("person", Person.class), Person.builder().name("zhangsan").build());

		System.out.println(MyKey.create("string", String.class));
		System.out.println(MyKey.create("string", String.class));
		System.out.println(MyKey.create("string", Integer.class));
		
	}
}
