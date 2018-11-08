package com.wong.reflect.proxy;

import org.junit.Test;

import com.wong.reflect.proxy.cglib.Proxy;
import com.wong.reflect.proxy.cglib.Target;
import com.wong.reflect.proxy.jdk.ITarget;

import net.sf.cglib.proxy.Enhancer;

/**
* @author Huang zhibin
* 
* 2018年9月14日 上午9:52:38
*/
public class ProxyTest {

	@Test
	public void cglib() {
		Target proxy = (Target)Enhancer.create(Target.class, new Proxy(new Target()));
		proxy.running();
	}
	
	@Test
	public void jdk() {
		com.wong.reflect.proxy.jdk.Target target = new com.wong.reflect.proxy.jdk.Target();
		com.wong.reflect.proxy.jdk.Proxy proxy = new com.wong.reflect.proxy.jdk.Proxy(target);
		
		ITarget obj = (ITarget) java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxy);
		
		obj.running();
	}
}
