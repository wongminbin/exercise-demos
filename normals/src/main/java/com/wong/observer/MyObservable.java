package com.wong.observer;

import java.util.Observable;

/**
* @author Huang zhibin
* 
* 2017年10月11日 上午11:17:50
*/
public class MyObservable extends Observable {

	public void doWork() {
		setChanged();
		
		notifyObservers();
	}
}
