package com.wong.poi.mongo;

import org.mongodb.morphia.Datastore;

import com.mongodb.MongoClient;

/**
 *
 * @author Huang Zhibin 
 * 2017年7月28日 上午9:34:52
 * <br> Mongodb链接实例
 */

public class Mongodb {

	/** Mongodb链接实例 */
	private MongoClient client;

	/** Mongodb链接池 */
	private Datastore datastore;

	public Mongodb(MongoClient client, Datastore datastore) {
		this.client = client;
		this.datastore = datastore;
	}

	/** 
	 * 获取Mongodb链接实例 
	 */
	public MongoClient getClient() {
		return client;
	}

	/** 
	 * 获取Mongodb链接池 
	 */
	public Datastore getDatastore() {
		return datastore;
	}

}
