package com.wong.retry;

import java.util.Collections;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author Huang zhibin
 * 
 *         2017年9月1日 下午3:33:43
 */
public class SpringRetryerTest {

	public static void main(String[] args) {
		retry();
	}
	
	private static void retry() {
		// 构建重试模板实例
		RetryTemplate retry = new RetryTemplate();
		
		// 设置重试策略，主要设置重试次数
		SimpleRetryPolicy policy = new SimpleRetryPolicy(3,
				Collections.<Class<? extends Throwable>, Boolean>singletonMap(Exception.class, true));
		
		// 设置重试回退操作策略，主要设置重试间隔时间
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(300);
		
		retry.setRetryPolicy(policy);
		retry.setBackOffPolicy(backOffPolicy);
		
		
		try {
			retry.execute(new RetryCallback<String, Exception>() {
				
				@Override
				public String doWithRetry(RetryContext context) throws Exception {
					
					return null;
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
