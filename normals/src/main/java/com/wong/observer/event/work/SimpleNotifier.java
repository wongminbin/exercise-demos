package com.wong.observer.event.work;

import com.wong.observer.event.Event;
import com.wong.observer.event.EventHandle;
import com.wong.observer.event.Notifier;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午5:22:41
*/
public class SimpleNotifier implements Notifier {
	
	private EventHandle eventHandle;
	
	public SimpleNotifier(EventHandle eventHandle) {
		this.eventHandle = eventHandle;
	}

	@Override
	public void addListener(Object obj, String method, Object... args) {
		Event event = new SimpleEvent(obj, method, args);
		eventHandle.addEvent(event);
	}

	@Override
	public void notifying() {
		eventHandle.doInvoke();
	}
}
