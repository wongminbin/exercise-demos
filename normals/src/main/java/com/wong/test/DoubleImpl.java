package com.wong.test;

/**
* @author Huang zhibin
* 
* 2017年12月5日 下午4:39:48
*/
public class DoubleImpl implements A, B{

	@Override
	public void print() {
		System.out.println("print......");
	}

	@Override
	public void test() {
		System.out.println("test......");
	}

	public static void main(String[] args) {
		A a = new DoubleImpl();
		a.test();
		
		B b = new DoubleImpl();
		b.test();
	}
}
