package com.wong.reflect;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
* @author Huang zhibin
* 
* 2017年11月14日 下午4:34:54
*/
public class TargetProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("动态代理--前");
		Object obj = arg3.invokeSuper(arg0, arg2);
		System.out.println("动态代理--后");
		
		return obj;
	}

}
