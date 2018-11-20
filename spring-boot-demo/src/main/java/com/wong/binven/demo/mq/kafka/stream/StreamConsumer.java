package com.wong.binven.demo.mq.kafka.stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月20日 上午9:10:16
 */

@Component
public class StreamConsumer implements Log {

	@KafkaListener(topics={"streams-wordcount-output"}, groupId="group-streams")
	public void consumer1(ConsumerRecord<String, String> record) {
		getLogger().info("streams-wordcount-output : {} ====== {}", record.key(), record.value());
	}
	
}
