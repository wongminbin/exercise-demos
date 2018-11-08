package com.wong.eventflows.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import com.wong.eventflows.anno.AfterWork;
import com.wong.eventflows.anno.BeforeWork;
import com.wong.eventflows.anno.Working;

/**
* @author HuangZhibin
* 
* 2018年7月3日 下午5:45:33
*/
public interface Event<T> {

	@SuppressWarnings("unchecked")
	default T work() {
		Method[] methods = getClass().getDeclaredMethods();
		Method before = null, after = null;
		Map<Integer, Method> works = new TreeMap<>();
		for (Method method : methods) {
			method.setAccessible(true);
			Annotation[] as = method.getAnnotations();
			for (Annotation a : as) {
				if (a.annotationType().isAssignableFrom(BeforeWork.class)) {
					before = method;
				} else if (a.annotationType().isAssignableFrom(AfterWork.class)) {
					after = method;
				} else if (a.annotationType().isAssignableFrom(Working.class)) {
					Working w = (Working)a;
					works.put(w.order(), method);
				}
			}
		}
		if (before != null) {
			try {
				before.invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		
		works.forEach((k, v) -> {
			try {
				v.invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		});
		
		if (after != null) {
			try {
				return (T)after.invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
}
