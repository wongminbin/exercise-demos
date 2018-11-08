package com.wong.binven.demo.dao.goods;

import org.apache.ibatis.annotations.Insert;

/**
 * create by: HuangZhiBin
 * 2018年11月5日 下午4:05:58
 */
public interface IGoodsDao {

	@Insert("insert into goods(goods_name) values(#{value})")
	public void save(String goodsName);
}
