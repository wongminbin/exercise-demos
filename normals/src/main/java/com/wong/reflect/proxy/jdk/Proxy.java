package com.wong.reflect.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
* @author Huang zhibin
* 
* 2018年9月14日 上午9:41:56
*/
public class Proxy implements InvocationHandler {
	
	private Object target;
	
	public Proxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("===============jdk dynamic running");
		return method.invoke(target, args);
	}

}
