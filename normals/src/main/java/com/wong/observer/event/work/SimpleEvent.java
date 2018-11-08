package com.wong.observer.event.work;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import org.apache.commons.lang3.ClassUtils;

import com.wong.observer.event.Event;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午4:33:07
*/
public class SimpleEvent implements Event {

	private Object obj;
	
	private String method;
	
	private Object[] args;
	
	private Class<?>[] argsClass;

	public SimpleEvent(Object obj, String method, Object[] args) {
		this.obj = obj;
		this.method = method;
		this.args = args;
		this.argsClass = ClassUtils.toClass(args);
	}
	
	@Override
	public void invoke() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Method m = obj.getClass().getMethod(method, argsClass);
			m.invoke(obj, args);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (Objects.isNull(obj)) {
			return false;
		}
		if (obj instanceof SimpleEvent) {
			SimpleEvent temp = (SimpleEvent) obj;
			return Objects.equals(this.obj, temp.obj)
					&& Objects.equals(this.method, temp.method);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(obj, method);
	}
}
