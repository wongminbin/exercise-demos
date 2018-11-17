package com.wong.binven.demo.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月17日 上午9:25:57
 */

@Component
public class Consumer implements Log {

	@KafkaListener(topics={"topic-1", "topic-2"})
	public void consumer1(ConsumerRecord<String, String> record) {
		getLogger().info("common-group : ====== {}", record.toString());
	}
	
	@KafkaListener(topics={"topic-1"}, groupId="group-topic-1")
	public void consumer2(ConsumerRecord<String, String> record) {
		getLogger().info("consumer2 ==== group-topic-1 : ====== {}", record.toString());
	}
	
	@KafkaListener(topics={"topic-1"}, groupId="group-topic-1")
	public void consumer2_1(ConsumerRecord<String, String> record) {
		// 1、同一消费组对同一主题，只有一个消费者
		getLogger().info("consumer2_1 ==== group-topic-1 : ====== {}", record.toString());
	}
	
	@KafkaListener(topics={"topic-2"}, groupId="group-topic-2")
	public void consumer3(ConsumerRecord<String, String> record) {
		getLogger().info("group-topic-2 : ====== {}", record.toString());
	}
	
}
