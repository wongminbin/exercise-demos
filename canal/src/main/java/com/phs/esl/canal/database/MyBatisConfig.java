package com.phs.esl.canal.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author HuangZhibin
 * 
 *         2018年10月25日 下午8:25:14
 */
@Configuration
@MapperScan(basePackages = "com.phs.esl.canal.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig {

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Autowired DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
