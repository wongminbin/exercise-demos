package com.wong.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
* @author Huang zhibin
* 
* 2017年11月13日 上午11:25:12
*/
@SuppressWarnings("unused")
public class Test {

	private static final Integer count3 = 512;
	
	private static Integer count2 = 256;
	
	private final Integer count1 = 1<<10;
	
	private Integer count0 = 128;
	
	private static final int count = 64;
	
	public static void main(String[] args) throws Exception {
		Test test = new Test();
		
		Field field = Test.class.getDeclaredField("count1");
		field.setAccessible(true);
		
		// field.getModifiers()&~Modifier.FINAL 这句话就是去掉final。
		// 其实java的访问权限信息啥的都是以2的N次幂来作为表示的，具体都是在java.lang.reflect.Modifier这个类里。
		// getModifiers()&~Modifier.FINAL  具体看下位运算，如果有（111111&000000=000000.）抹去了16这个final标识。
		
		Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers()&~Modifier.FINAL);

        field.set(test, 32);
        
		test.print();
		
	}
	
	public void print() {
		System.out.println(count1);
	}
}
