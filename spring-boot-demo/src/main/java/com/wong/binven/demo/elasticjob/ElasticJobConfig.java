package com.wong.binven.demo.elasticjob;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.wong.binven.demo.elasticjob.annotation.ElasticSimpleJob;

import lombok.Setter;

/**
 * create by: HuangZhiBin 2018年11月6日 下午3:06:25
 */
@Setter
@Component
@Configuration
@ConfigurationProperties(prefix = "elaticjob.zookeeper")
public class ElasticJobConfig {

	private String servers;

	private String namespace;

	@Autowired
	private ApplicationContext context;

	@PostConstruct
	public void initJob() {
		ZookeeperRegistryCenter center = new ZookeeperRegistryCenter(new ZookeeperConfiguration(servers, namespace));
		center.init();
		Map<String, SimpleJob> jobs = context.getBeansOfType(SimpleJob.class);
		
		jobs.values().stream().filter(job -> Objects.nonNull(job.getClass().getAnnotation(ElasticSimpleJob.class)))
				.forEach(job -> {
					ElasticSimpleJob ann = job.getClass().getAnnotation(ElasticSimpleJob.class);

					String cron = ann.cron();
					String name = StringUtils.defaultIfBlank(ann.name(), job.getClass().getName());
					int sharding = ann.sharding();

					SimpleJobConfiguration jobConfig = new SimpleJobConfiguration(
							JobCoreConfiguration.newBuilder(name, cron, sharding).description(ann.description())
									.shardingItemParameters(ann.itemParameters()).jobParameter(ann.parameter()).build(),
							job.getClass().getCanonicalName());
					
					LiteJobConfiguration liteJobConfig = LiteJobConfiguration.newBuilder(jobConfig).overwrite(ann.overwrite()).build();
					
					Optional.ofNullable(context.getBean(ann.dataSource()))
						.map(ds->new SpringJobScheduler(job, center, liteJobConfig, new JobEventRdbConfiguration((DataSource)ds)))
						.orElse(new SpringJobScheduler(job, center, liteJobConfig)).init();
				});
	}
}
