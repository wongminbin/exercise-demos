package com.wong.lamda;

import com.wong.test.Person;

/**
* @author Huang zhibin
* 
* 2017年9月12日 上午11:10:13
*/
public class LambdaTest<T> {

	private T t;
	
	public LambdaTest() {}
	
	public LambdaTest(T t) {
		this.t = t;
	}

	public <U> U apply(ILambda<T, U> lambda) {
		return lambda.apply(t);
	}
	
	public <U> U apply(ILambda<T, U> lambda, T name) {
		return lambda.apply(name);
	}
	
	public T get(ISuplier<T> suplier) {
		return suplier.get();
	}
	
	public static void main(String[] args) {
		LambdaTest<Person> test = new LambdaTest<>(new Person());
		
		test.apply(Person::getName);
		
		test.apply(v -> v.getName(), new Person());
		
		test.get(() -> new Person());
		
		test.get(Person::new);
	}
}
