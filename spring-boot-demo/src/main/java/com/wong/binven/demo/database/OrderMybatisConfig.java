package com.wong.binven.demo.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.wong.binven.demo.springboot.druid.DruidXADataSourceBuilder;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:21:18
 */

@Configuration
@MapperScan(basePackages = "com.wong.binven.demo.dao.order", sqlSessionTemplateRef = "orderSqlSessionTemplate")
public class OrderMybatisConfig {

	@Bean(name="orderDruidXADataSource", initMethod="init", destroyMethod="close")
	@ConfigurationProperties("spring.datasource.druid.order")
	public DruidXADataSource druidXADataSource() {
		return DruidXADataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean("orderDataSource")
	public DataSource dataSource(@Qualifier("orderDruidXADataSource") DruidXADataSource druidXA) {
		AtomikosDataSourceBean xa = new AtomikosDataSourceBean();
		xa.setXaDataSource(druidXA);
		xa.setBeanName("orderDruidXADataSource");
		xa.setMinPoolSize(druidXA.getMinIdle());
		xa.setMaxPoolSize(druidXA.getMaxActive());
		xa.setMaxIdleTime((int)druidXA.getMaxWait()/1000);
		
		return xa;
	}
	
	@Primary
	@Bean("orderSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	
	@Primary
	@Bean("orderSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
//  使用分布式事务后, 取消本地事务
//	@Primary
//	@Bean("orderDataSourceTransactionManager")
//	public DataSourceTransactionManager transactionManager(@Autowired DataSource orderDataSource) {
//		return new DataSourceTransactionManager(orderDataSource);
//	}
	
}
