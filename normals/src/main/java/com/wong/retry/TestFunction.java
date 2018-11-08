package com.wong.retry;

import com.github.rholder.retry.Attempt;

/**
* @author Huang zhibin
* 
* 2017年9月2日 下午3:31:09
*/

@FunctionalInterface
public interface TestFunction<V> {

	void onRetry(Attempt<V> attempt);
}
