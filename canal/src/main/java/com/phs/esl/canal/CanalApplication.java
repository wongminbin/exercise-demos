package com.phs.esl.canal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author HuangZhibin
 * 
 *         2018年10月26日 下午1:43:48
 */

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CanalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanalApplication.class, args);
	}
}
