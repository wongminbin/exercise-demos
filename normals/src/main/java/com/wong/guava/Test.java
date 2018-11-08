package com.wong.guava;

import java.util.concurrent.atomic.AtomicInteger;

/**
* @author Huang zhibin
* 
* 2017年9月27日 下午7:58:35
*/
public class Test {

	public static void main(String[] args) {
//		ImmutableSet.of("");
		
		AtomicInteger NEXT_COUNTER = new AtomicInteger(Integer.MAX_VALUE);
		
		for (int i = 0; i < 1000; i++) {
			System.out.println(NEXT_COUNTER.getAndIncrement());
		}
	}
}
