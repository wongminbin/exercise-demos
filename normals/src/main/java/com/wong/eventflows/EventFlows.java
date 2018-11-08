package com.wong.eventflows;

import com.wong.eventflows.anno.AfterWork;
import com.wong.eventflows.anno.BeforeWork;
import com.wong.eventflows.anno.Working;
import com.wong.eventflows.event.Event;

/**
* @author HuangZhibin
* 
* 2018年7月3日 下午6:08:57
*/
public class EventFlows<T> implements Event<T> {

	@BeforeWork
	private void init() {
		System.out.println("================init...");
	}
	
	@Working(order=1)
	private void open() {
		System.out.println("================open...");
	}
	
	@Working(order=2)
	private void calute() {
		System.out.println("================calute...");
	}
	
	@Working(order=3)
	private void order() {
		System.out.println("================order...");
	}
	
	@Working(order=4)
	private void save() {
		System.out.println("================save...");
	}
	
	@AfterWork
	private String close() {
		System.out.println("================close...");
		return "close";
	}
}
