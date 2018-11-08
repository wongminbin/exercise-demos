package com.wong.guice.test;

/**
* @author HuangZhibin
* 
* 2018年9月4日 上午11:12:13
*/
public class OrderService implements IOrderService {

	@Override
	public String order() {
		return "this is test order...";
	}

}
