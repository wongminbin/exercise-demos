package com.wong.binven.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wong.binven.demo.dao.goods.IGoodsDao;
import com.wong.binven.demo.dao.order.IOrderDao;

/**
 * create by: HuangZhiBin
 * 2018年11月5日 下午4:18:15
 */
@Service("service")
@Transactional(rollbackFor = Exception.class)
public class ServiceImpl implements IService {

	@Autowired
	private IGoodsDao goodsDao;
	
	@Autowired
	private IOrderDao orderDao;
	
	@Override
	public void execute() {
		goodsDao.save("test-goods-" + Math.random());
		
		orderDao.save("test-order-" + Math.random());
		
	}

}
