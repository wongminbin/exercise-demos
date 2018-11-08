package com.wong.eventflows;

import org.junit.Test;

import com.wong.eventflows.event.Event;

/**
* @author HuangZhibin
* 
* 2018年7月3日 下午6:08:57
*/
public class EventFlowsTest {

	@Test
	public void test() {
		Event<String> event = new EventFlows<>();
		String result = event.work();
		System.out.println("===resutl:" + result);
	}
}
