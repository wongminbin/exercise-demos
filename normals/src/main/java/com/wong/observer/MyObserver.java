package com.wong.observer;

import java.util.Observable;
import java.util.Observer;

/**
* @author Huang zhibin
* 
* 2017年10月11日 上午11:16:10
*/
public class MyObserver implements Observer {

	private String name;
	
	public MyObserver(String name) {
		this.name = name;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(name);
	}

}
