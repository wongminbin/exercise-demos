package com.wong.poi.mongo;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 *
 * @author Huang Zhibin 
 * 2017年7月28日 上午9:09:56
 * <br>Mongodb工厂
 */

public class MongodbFactory {

	private MongodbFactory() {
		throw new IllegalArgumentException("can't initiate this class...");
	}

	public static Mongodb getDatastore(MongodbConfig config) {
		// 配置链接地址，可以多个
		ServerAddress serverAddress = new ServerAddress(config.getHost(), config.getPort());
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
		seeds.add(serverAddress);

		// 配置登陆权限
		MongoCredential credentials = MongoCredential.createScramSha1Credential(config.getUserName(), 
				config.getDbName(), config.getPassword().toCharArray());

		// 配置连接池参数
		Builder builder = MongoClientOptions.builder();
		builder.connectionsPerHost(config.getMaxConnections()).minConnectionsPerHost(config.getMinConnections())
				.threadsAllowedToBlockForConnectionMultiplier(config.getThreadConnections());

		MongoClientOptions options = builder.build();

		// mongodb driver
		MongoClient client = new MongoClient(seeds, credentials, options);

		// mongodb mapper
		Morphia morphia = new Morphia();
		morphia.getMapper().getOptions().setMapSubPackages(true);

		Datastore datastore = morphia.mapPackage(config.getMapPackage())
				.createDatastore(client, config.getDbName());

		// 使用索引
		datastore.ensureIndexes();

		return new Mongodb(client, datastore);
	}
}
