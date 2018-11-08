package com.wong.guice.test;

import com.google.inject.Inject;

/**
* @author HuangZhibin
* 
* 2018年9月4日 上午11:14:16
*/
public class Operation {

	private IOrderService orderService;
	
	private IUserService userService;

	@Inject
	public Operation(IOrderService orderService, IUserService userService) {
		this.orderService = orderService;
		this.userService = userService;
	}
	
	public void operation() {
		System.out.println(orderService.order());
		System.out.println(userService.name());
	}
}
