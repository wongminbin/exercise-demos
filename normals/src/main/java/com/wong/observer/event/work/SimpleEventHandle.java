package com.wong.observer.event.work;

import java.util.HashSet;
import java.util.Set;

import com.wong.observer.event.Event;
import com.wong.observer.event.EventHandle;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午4:49:21
*/
public class SimpleEventHandle implements EventHandle {
	
	private Set<Event> events = new HashSet<>();

	@Override
	public synchronized void addEvent(Event event) {
		if (events.contains(event)) {
			return;
		}
		events.add(event);
	}

	@Override
	public synchronized void removeEvent(Event event) {
		if (events.contains(event)) {
			events.remove(event);
		}
	}
	
	@Override
	public void doInvoke() {
		Object[] local = null;
		synchronized (this) {
			local = events.toArray();
		}
		for (Object event : local) {
			try {
				((Event)event).invoke();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized int eventSize() {
		return events.size();
	}

}
