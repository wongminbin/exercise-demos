package com.wong.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;

/**
* @author Huang zhibin
* 
* 2017年9月18日 下午2:13:19
*/
public class Container {

	private Map<Key<?>, Object> container = new HashMap<>();
	
	public <T> T put(Key<T> key, T value) {
		
		container.put(key, key.getClazz().cast(value));
		
		return value;
	}

	public <T> T get(Key<T> key) {
		if (contains(key)) {
			return key.getClazz().cast(container.get(key));
		}
		return null;
	}
	
	public <T> T remove(Key<T> key) {
		Object obj = container.remove(key);
		return Optional.ofNullable(obj).map(v -> key.getClazz().cast(v)).orElse(null);
	}
	
	public <T> boolean contains(Key<T> key) {
		return container.containsKey(key);
	}
	
	public static void main(String[] args) {
		Container c = new Container();
		
//		Key<List> key11 = KeyFactory.create("test1", List.class);
//		c.put(key11, Arrays.asList("123"));
		
		Key<String[]> key = KeyFactory.create("test1", String[].class);
		System.out.println(key);
		
		c.put(key, new String[] {"1", "2"});
		
		System.out.println(ArrayUtils.toString(c.get(key)));
		
		key = KeyFactory.create("test2", String[].class);
		System.out.println(key);
		
		c.put(key, null);
		
		System.out.println(ArrayUtils.toString(c.get(key)));
		
		Key<Integer> key1 = KeyFactory.create("test1", Integer.class);
		System.out.println(key1);
		
		c.put(key1, 10);
		System.out.println(c.get(key1));
		
		System.out.println(c.container.size());
	}
}
