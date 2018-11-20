package com.wong.binven.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月20日 上午9:12:53
 */

@RestController
public class KafkaStreamController implements Log {

	@Autowired
	private KafkaTemplate<String, String> template;

	@RequestMapping(value="/stream")
	public void stream(String msg) {
		String topic = "streams-plaintext-input";
		
		template.send(topic, "streams", msg);
		
		getLogger().info("================ {}", msg);
	}
}
