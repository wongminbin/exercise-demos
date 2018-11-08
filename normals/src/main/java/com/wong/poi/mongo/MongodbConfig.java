package com.wong.poi.mongo;

/**
 *
 * @author Huang Zhibin 
 * 2017年7月28日 上午9:13:58
 * <br> Mongodb连接配置参数
 */

public class MongodbConfig {

	/** 链接IP地址 */
	private String host = "127.0.0.1";

	/** 链接IP端口 */
	private int port = 27017;

	/** 最大链接数 */
	private int maxConnections = 300;

	/** 最小链接数 */
	private int minConnections = 50;

	/** 每个线程最大链接数 */
	private int threadConnections = 50;

	/** 连接用户名 */
	private String userName;

	/** 连接密码 */
	private String password;

	/** 连接数据库名称 */
	private String dbName;

	/** ORM扫描的包名 */
	private String mapPackage;

	public MongodbConfig(String userName, String password, 
			String dbName, String mapPackage) {
		this.userName = userName;
		this.password = password;
		this.dbName = dbName;
		this.mapPackage = mapPackage;
	}

	public MongodbConfig(String host, int port, String userName, 
			String password, String dbName, String mapPackage) {
		this(userName, password, dbName, mapPackage);
		this.host = host;
		this.port = port;
	}
	
	

	public MongodbConfig(String host, int port, int maxConnections, int minConnections, int threadConnections,
			String userName, String password, String dbName, String mapPackage) {
		this(host, port, userName, password, dbName, mapPackage);
		this.maxConnections = maxConnections;
		this.minConnections = minConnections;
		this.threadConnections = threadConnections;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getMapPackage() {
		return mapPackage;
	}

	public void setMapPackage(String mapPackage) {
		this.mapPackage = mapPackage;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getMinConnections() {
		return minConnections;
	}

	public void setMinConnections(int minConnections) {
		this.minConnections = minConnections;
	}

	public int getThreadConnections() {
		return threadConnections;
	}

	public void setThreadConnections(int threadConnections) {
		this.threadConnections = threadConnections;
	}

}
