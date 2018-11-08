package com.wong.reflect.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
* @author Huang zhibin
* 
* 2018年9月14日 上午9:40:58
*/
public class Proxy implements MethodInterceptor  {
	
	private Object target;
	
	public Proxy(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("=======================cglib proxy running");
		return arg1.invoke(target, arg2);
	}

}
