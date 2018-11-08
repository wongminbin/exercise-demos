package com.wong.test;

/**
* @author Huang zhibin
* 
* 2017年9月26日 下午2:33:12
*/
public class TryCatchFinally {

	public static void main(String[] args) {
		System.out.println(test());
	}
	
	private static int test() {
		try {
			System.out.println("invoke try...");
			int i = 1, j=0;
			return i/j;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new IllegalArgumentException("error argument");
		} finally {
			System.out.println("invoke finally...");
		}
		
	}
}
