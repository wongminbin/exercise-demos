package com.wong.container;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author Huang zhibin
 * 
 * 2017年9月18日 下午2:13:50
 */
public final class Key<T> {

	private String identify;

	private Class<T> clazz;

	public Key(String identify, Class<T> clazz) {
		this.identify = identify;
		this.clazz = clazz;
		
		validate();
	}

	public String getIdentify() {
		return identify;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	@Override
	public int hashCode() {
		return getIdentify().hashCode() + getClazz().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (Objects.isNull(obj)) {
			return false;
		}
		if (obj instanceof Key) {
			Key<?> temp = (Key<?>)obj;
			if (getIdentify().equals(temp.getIdentify()) && 
					getClazz().equals(temp.getClazz())) {
				 return true;
			}
		}
		return false;
	}
	
	private void validate() {
		checkName(identify);
		checkClass(clazz);
	}
	
	private void checkName(String identify) {
		if (Objects.isNull(identify)) {
			throw new NullPointerException("identify is null");
		}
	}

	private void checkClass(Class<T> clazz) {
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
