package com.wong.fastjson;

import java.util.Date;

import com.alibaba.fastjson.JSON;

/**
* @author HuangZhibin
* 
* 2018年2月1日 下午5:18:00
*/
public class Test {

	public static void main(String[] args) {
//		Map<String, Object> json = JSON.parseObject("{\"cur_pay\":\"200.00\",\"give_money\":1,\"give_integral\":100,\"a\":100}", Feature.OrderedField);
//		json.forEach((k, v) -> System.out.println(k+"-"+v));
//		Integer i = 1;
//		Integer j = 3;
//		DecimalFormat df=new DecimalFormat("0.00");
//		df.setRoundingMode(RoundingMode.HALF_UP);
//		System.out.println(df.format(100.0*i/j));
		
		Datas datas = new Datas();
		datas.setDate(new Date());
		
		System.out.println(JSON.toJSONString(datas));
		
		String json = "{\"date\":\"15:55\"}";
		datas = JSON.parseObject(json, Datas.class);
		System.out.println(datas.getDate());
	}
}
