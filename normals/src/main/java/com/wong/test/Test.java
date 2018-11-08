package com.wong.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Huang zhibin
 * 
 *         2017年10月16日 上午10:00:36
 */
public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
//		List<? extends Fruit> list1 = extendsTest();
//		list1.add(new Apple());
//		Fruit fruit = list1.get(0);
		
		List<? super Fruit> list2 = superTest();
		list2.add(new Apple());
//		Object object = list2.get(0);
//		Apple apple = list2.get(0);
	}
	
	public static List<? extends Fruit> extendsTest() {
//		List<Orange> fruits = new ArrayList<Orange>();
		List<Apple> fruits = new ArrayList<Apple>();
		return fruits;
	}
	
	public static List<? super Fruit> superTest() {
//		List<Fruit> fruits = new ArrayList<Fruit>();
		List<Object> fruits = new ArrayList<Object>();
		return fruits;
	}
}
