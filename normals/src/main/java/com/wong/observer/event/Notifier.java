package com.wong.observer.event;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午4:11:38
*/
public interface Notifier {

	void addListener(Object obj, String method, Object... args);
	
	void notifying();
}
