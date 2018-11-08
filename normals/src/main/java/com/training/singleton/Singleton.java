package com.training.singleton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

/**
* @author Huang zhibin
* 
* 2017年9月8日 上午11:20:28
*/
public class Singleton {

	// 单例模式下的属性的所有操作都要保证线程安全
	private String params;
	
	// 构造方法必须为private，让对象的创建只在内部实现
	private Singleton() {}
	
	private Singleton(String params) {
		this.params = params;
	}
	
	
//	恶加载，我们追求的应该是用才创建
	private static final Singleton INSTANCE = new Singleton();
	
	public static Singleton getInstance() {
		return INSTANCE;
	}
	
	
//	=========================================
//	=========================================
	private static Singleton INSTANCE2;
	
//	懒加载方法体加锁方式，方法每次都加锁，效率极低
	public static synchronized Singleton getInstance2() {
		if (Objects.isNull(INSTANCE2)) {
			INSTANCE2 = new Singleton();
		}
		return INSTANCE2;
	}
	
//	懒加载 lock-double-check模式(该方式会出现INSTANCE2赋值的可见问题，由可见性引起 
//	INSTANCE2 = new Singleton()运行多次不能保证绝对单例，以及可能出现NPE)
	public static Singleton getInstance3() {
		if (Objects.isNull(INSTANCE2)) {
			synchronized (Singleton.class) {
				if (Objects.isNull(INSTANCE2)) {
					INSTANCE2 = new Singleton();
				}
			}
		}
		return INSTANCE2;
	}
	
	
//	=========================================
//	=========================================
	private static volatile Singleton INSTANCE4;
	
//	懒加载 lock-double-check模式2(volatile jdk1.5后保证可见性，所以不会出现上述问题)
//	这种方式可以保证单例的合法性，但代码风格复杂，且显性使用锁
	public static Singleton getInstance4() {
		if (Objects.isNull(INSTANCE4)) {
			synchronized (Singleton.class) {
				if (Objects.isNull(INSTANCE4)) {
					INSTANCE4 = new Singleton();
				}
			}
		}
		return INSTANCE4;
	}
	
	
//	=========================================
//	=========================================
//	懒加载 无锁模式(我推荐的单例创建方式，代码与恶加载一样简洁，同是获得懒加载的优势，锁是基于JVM ClassLoader级别)
	private static class INNER {
		public static final Singleton INSTANCE5 = new Singleton();
	}
	
	public static Singleton getInstance5() {
		return INNER.INSTANCE5;
	}
	
	public String method() {
//		doSomething...
		
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.entrySet();
		
		ListIterator<Map.Entry<String, String>> i = new ArrayList<>(map.entrySet())
				.listIterator(map.size());
		while (i.hasPrevious()) {
			Map.Entry<String, String> entry = i.previous();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		return params;
	}
}
