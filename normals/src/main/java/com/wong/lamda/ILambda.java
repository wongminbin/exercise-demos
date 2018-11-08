package com.wong.lamda;

/**
* @author Huang zhibin
* 
* 2017年9月12日 上午11:10:59
*/
public interface ILambda<T, R> {

	R apply(T t);
	
	default R invoke(T t, String str) {
		
		return null;
	}
}
