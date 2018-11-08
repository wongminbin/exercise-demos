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

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.wong.binven.demo.springboot.druid.DruidXADataSourceBuilder;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 下午2:21:18
 */

@Configuration
@MapperScan(basePackages = "com.wong.binven.demo.dao.goods", sqlSessionTemplateRef = "goodsSqlSessionTemplate")
public class GoodsMybatisConfig {
	
	@Bean(name="goodsDruidXADataSource", initMethod="init", destroyMethod="close")
	@ConfigurationProperties("spring.datasource.druid.goods")
	public DruidXADataSource druidXADataSource() {
		return DruidXADataSourceBuilder.create().build();
	}

	@Bean("goodsDataSource")
	public DataSource dataSource(@Qualifier("goodsDruidXADataSource") DruidXADataSource druidXA) {
		AtomikosDataSourceBean xa = new AtomikosDataSourceBean();
		xa.setXaDataSource(druidXA);
		xa.setBeanName("goodsDruidXADataSource");
		xa.setMinPoolSize(druidXA.getMinIdle());
		xa.setMaxPoolSize(druidXA.getMaxActive());
		xa.setMaxIdleTime((int)druidXA.getMaxWait()/1000);
		
		return xa;
	}
	
	@Bean("goodsSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("goodsDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}

	@Bean("goodsSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("goodsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
//	@Primary
//	@Bean("goodsDataSourceTransactionManager")
//	public DataSourceTransactionManager transactionManager(@Autowired DataSource goodsDataSource) {
//		return new DataSourceTransactionManager(goodsDataSource);
//	}
}
