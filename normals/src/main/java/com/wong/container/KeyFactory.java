package com.wong.container;

/**
* @author Huang zhibin
* 
* 2017年9月18日 下午2:28:38
*/
public class KeyFactory {

	private KeyFactory() {}
	
	public static <T> Key<T> create(String identify, Class<T> clazz) {
		return new Key<>(identify, clazz);
	}
	
}
