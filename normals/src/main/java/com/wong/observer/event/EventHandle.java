package com.wong.observer.event;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午4:07:12
*/
public interface EventHandle {

	void addEvent(Event event);
	
	void removeEvent(Event event);
	
	void doInvoke();
	
	int eventSize();
}
