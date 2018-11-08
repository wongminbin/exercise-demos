package com.wong.poi.fuckcccs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.query.Query;

import com.alibaba.fastjson.JSON;
import com.wong.poi.mongo.Mongodb;
import com.wong.poi.mongo.MongodbConfig;
import com.wong.poi.mongo.MongodbFactory;

/**
* @author HuangZhibin
* 
* 2018年7月25日 上午11:39:46
*/
public class Analysis {
	
	private Mongodb mongo;
	
	@Before
	public void init() {
		System.out.println("................init mongo");
		mongo = MongodbFactory.getDatastore(new MongodbConfig("10.10.0.205", 27017, 
				"phs_erp_dev", "74uRtG58qaz^%$#", "phs_erp_dev", "com.wong.poi.fuckcccs"));
	}
	
	@Test
	public void analysi1() {
		Query<Medicine> query = mongo.getDatastore().createQuery(Medicine.class);
		query.criteria("company").contains("长生");
		List<Medicine> list = query.asList();
		Map<String, List<Medicine>> map = list.stream().collect(Collectors.groupingBy(Medicine::getPublicity, 
				TreeMap::new, Collectors.toList()));
		
		map.keySet().forEach(v->System.out.println(v));
	}
	
	@Test
	public void analysi2() {
		Query<SalesStatus> query = mongo.getDatastore().createQuery(SalesStatus.class);
		query.criteria("createCompany").contains("长生");
		List<SalesStatus> list = query.asList();
		
		Map<String, List<SalesStatus>> map = list.stream().collect(Collectors.groupingBy(SalesStatus::getProv));
		
		Map<String, Map<String, List<SalesStatus>>> groups = new HashMap<>(map.size());
		for (Entry<String, List<SalesStatus>> ss : map.entrySet()) {
			groups.put(ss.getKey(), ss.getValue().stream().collect(Collectors.groupingBy(SalesStatus::getYear)));
		}
		System.out.println(JSON.toJSONString(groups));
	}
	
}
