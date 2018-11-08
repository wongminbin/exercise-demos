package com.wong.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
* @author HuangZhibin
* 
* 2018年7月9日 上午11:00:04
*/
public abstract class MyTask<V, R> extends RecursiveTask<R>{

	private V v;
	/**
	 * 
	 */
	private static final long serialVersionUID = -495715301066754539L;

	public MyTask(V v) {
		this.v = v;
	}
	
	@Override
	protected abstract R compute();

	protected V getValue() {
		return v;
	}
	
	protected void setValue(V v) {
		this.v = v;
	}
}
