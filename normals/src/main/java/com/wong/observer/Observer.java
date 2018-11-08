package com.wong.observer;

/**
* @author Huang zhibin
* 
* 2017年10月11日 下午1:36:59
*/
public interface Observer {

	<T extends Observable, M> void update(T observable, M message);
}
