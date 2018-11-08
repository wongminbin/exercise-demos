package com.wong.map;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author HuangZhibin
 * 
 *         2018年7月14日 下午5:42:28
 */
public final class MyKey<V> {

	private final String name;

	private final Class<V> clazz;
	
	public static <V> MyKey<V> create(String name, Class<V> clazz) {
		return new MyKey<>(name, clazz);
	}

	private MyKey(String name, Class<V> clazz) {
		validate(name, clazz);
		
		this.name = name;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public Class<V> getClazz() {
		return clazz;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getClazz().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (Objects.isNull(obj)) {
			return false;
		}
		if (obj instanceof MyKey) {
			MyKey<?> temp = (MyKey<?>) obj;
			if (getName().equals(temp.getName()) && getClazz().equals(temp.getClazz())) {
				return true;
			}
		}
		return false;
	}

	private void validate(String name, Class<V> clazz) {
		checkName(name);
		checkClass(clazz);
	}

	private void checkName(String name) {
		if (Objects.isNull(name)) {
			throw new NullPointerException("identify is null");
		}
	}

	private void checkClass(Class<V> clazz) {
		if (Objects.isNull(clazz)) {
			throw new NullPointerException("class type is null");
		}
		if (Collection.class.isAssignableFrom(clazz.getClass())) {
			throw new RuntimeException("can`t support " + clazz.getClass());
		}
		if (Map.class.isAssignableFrom(clazz.getClass())) {
			throw new RuntimeException("can`t support " + clazz.getClass());
		}

	}
}
