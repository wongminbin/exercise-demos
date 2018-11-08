package com.wong.binven.demo.task;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.wong.binven.demo.core.Log;
import com.wong.binven.demo.elasticjob.annotation.ElasticSimpleJob;

/**
 * create by: HuangZhiBin
 * 2018年11月6日 下午4:06:06
 */

@Component
@ElasticSimpleJob(cron="0/30 * * * * ?", name="my-task2", sharding=5)
public class Task2 implements SimpleJob, Log {

	@Override
	public void execute(ShardingContext shardingContext) {
		getLogger().info(getClass().getCanonicalName() + "====" + JSON.toJSONString(shardingContext));
	}

}
