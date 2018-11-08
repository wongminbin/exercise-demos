package com.wong.binven.demo.database;

import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * create by: HuangZhiBin
 * 2018年11月5日 下午2:49:53
 */

@Configuration
public class TransactionManagerConfig {

	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(60000);
		return userTransactionImp;
	}
	
	@Bean(name = "userTransactionManager", initMethod = "init", destroyMethod = "close")
	public UserTransactionManager userTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}
	
	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "userTransactionManager" })
	public JtaTransactionManager transactionManager() throws Throwable {
		JtaTransactionManager manager = new JtaTransactionManager(userTransaction(), userTransactionManager());
		return manager;
	}
}
