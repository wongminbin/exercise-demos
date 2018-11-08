package com.wong.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
* @author HuangZhibin
* 
* 2018年7月14日 下午5:41:36
*/
public class MyMap {

	private Map<MyKey<?>, Object> containers;

	public MyMap() {
		containers = new HashMap<>();
	}
	
	public MyMap(Map<? extends MyKey<?>, ?> m) {
		containers = new HashMap<>(m);
	}
	
	public int size() {
		return containers.size();
	}

	public boolean isEmpty() {
		return containers.isEmpty();
	}

	public boolean containsKey(MyKey<?> key) {
		return containers.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return containers.containsValue(value);
	}

	public <V> V get(MyKey<V> key) {
		return Optional.ofNullable(containers.get(key)).map(v -> key.getClazz().cast(v))
				.orElse(null);
	}
	
	public <V> V put(MyKey<V> key, V value) {
		containers.put(key, value);
		return value;
	}
	
	public <V> V remove(MyKey<V> key) {
		return Optional.ofNullable(containers.remove(key)).map(v -> key.getClazz().cast(v))
				.orElse(null);
	}

	public <V> void putAll(Map<? extends MyKey<V>, ? extends V> m) {
		containers.putAll(m);
	}

	public void clear() {
		containers.clear();
	}
	
	public Set<MyKey<?>> keySet() {
		return containers.keySet();
	}

	public Collection<?> values() {
		return containers.values();
	}

	public Set<Entry<MyKey<?>, Object>> entrySet() {
		return containers.entrySet();
	}
	
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>(containers.size());
		containers.forEach((k, v) -> map.put(k.getName(), v));
		return map;
	}
}
