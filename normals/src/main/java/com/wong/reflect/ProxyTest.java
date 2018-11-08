package com.wong.reflect;

import net.sf.cglib.proxy.Enhancer;

/**
* @author Huang zhibin
* 
* 2017年11月14日 下午4:43:03
*/
public class ProxyTest {

	public static void main(String[] args) {
		Target target = (Target)Enhancer.create(Target.class, new TargetProxy());
		String result = target.run();
		System.out.println("result：" + result);
		target.test();
	}
}
