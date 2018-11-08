package com.wong.binven.demo.dao.order;

import org.apache.ibatis.annotations.Insert;

/**
 * create by: HuangZhiBin
 * 2018年11月5日 下午4:06:11
 */
public interface IOrderDao {

	@Insert("insert into orders(order_no) values(#{value})")
	public void save(String orderNo);
}
