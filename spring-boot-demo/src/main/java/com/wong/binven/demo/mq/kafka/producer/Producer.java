package com.wong.binven.demo.mq.kafka.producer;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月17日 上午9:25:44
 */

@Component
public class Producer implements Log {
	
	@Autowired
	private KafkaTemplate<String, String> template;

	@Scheduled(cron = "0/30 * * * * *")
	public void producer1() {
		String topic = "topic-1";
		String msg = topic + " : " + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
		
		template.send(topic, msg);
		
		getLogger().info("================ {} ", msg);
	}
	
	@Scheduled(cron = "0/10 * * * * *")
	public void producer2() {
		String topic = "topic-2";
		String msg = topic + " : " + DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
		
		template.send(topic, msg);
		
		getLogger().info("================ {} ", msg);
	}
}
