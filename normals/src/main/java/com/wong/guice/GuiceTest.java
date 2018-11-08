package com.wong.guice;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.wong.guice.test.BillingModule;
import com.wong.guice.test.Operation;

/**
* @author HuangZhibin
* 
* 2018年9月4日 上午11:00:38
*/
public class GuiceTest {

	@Test
	public void test() {
		Injector injector = Guice.createInjector(new BillingModule());
		
		Operation opt = injector.getInstance(Operation.class);
		
		opt.operation();
		
	}
}
