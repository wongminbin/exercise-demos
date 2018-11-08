package com.wong.guice.test;

import com.google.inject.AbstractModule;

/**
* @author HuangZhibin
* 
* 2018年9月4日 上午11:17:58
*/
public class BillingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IOrderService.class).to(OrderService.class);
		bind(IUserService.class).to(UserService.class);
	}
}
