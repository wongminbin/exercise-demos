package com.wong.retry;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.BlockStrategies;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
* @author Huang zhibin
* 
* 2017年9月1日 下午3:33:43
*/
public class GuavaRetryerTest {

	public static void main(String[] args) {
		retry(() -> {
			
			return "";
		});
		
	}	
	
	private static <T> void retry(Callable<T> callable) {
		
		Retryer<T> retryer = RetryerBuilder.<T>newBuilder()
				// 当发生异常时重试
				.retryIfException()
				.retryIfRuntimeException()
				.retryIfExceptionOfType(ArrayIndexOutOfBoundsException.class)
				.retryIfException(Predicates.isNull())
				// 根据返回结果是否需要重试，true表示需要重试
				.retryIfResult((result) -> {
					
					return false;
				})
				// 正常或重试的监听者
				.withRetryListener(new RetryListener() {
					
					@Override
					public <V> void onRetry(Attempt<V> attempt) {
						attempt.getResult();
						
					}
				})
				.withBlockStrategy(BlockStrategies.threadSleepStrategy())
				// 设置重试3次，同样可以设置重试超时时间
				.withStopStrategy(StopStrategies.stopAfterAttempt(3))
				// 设置每次重试间隔
				.withWaitStrategy(WaitStrategies.fixedWait(100, TimeUnit.MILLISECONDS))
				.build();
		
		try {
			retryer.call(callable);
		} catch (ExecutionException | RetryException e) {
			
		}
	}
	
	
}
