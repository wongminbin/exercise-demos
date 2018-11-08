package com.wong.observer.event.work;

import com.wong.observer.event.Notifier;
import com.wong.test.Person;
import com.wong.test.Son;

/**
* @author Huang zhibin
* 
* 2017年10月12日 下午5:28:13
*/
public class Test {

	public static void main(String[] args) {
		Notifier notifier = new SimpleNotifier(new SimpleEventHandle());
		notifier.addListener(new Person(), "getName");
		notifier.addListener(new Son(), "work", "test notify");
		notifier.addListener(new Son(), "work", "arg1", "arg2");
		
		notifier.notifying();
	}
}
