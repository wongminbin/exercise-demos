package com.wong.test;

import java.util.ArrayList;
import java.util.Optional;

/**
* @author Huang zhibin
* 
* 2017年9月11日 下午3:22:40
*/
public class OptionalTest {

	public static void main(String[] args) {
		Person person = new Person();
		
		ArrayList<String> list = new ArrayList<>();
		list.stream().forEach(v -> System.out.println(v));;
		
		
		// person 不能为null
		Optional<Person> opt = Optional.of(person);
		
		// 允许person为null
		opt = Optional.ofNullable(person);
		
		// person不为null时返回true,为null时返回false
		opt.isPresent();
		
		// person不为null时返回person，为null时抛出NoSuchElementsException
		opt.get();
		
		// person不为null时返回person，为null时，返回else时创建的Person对象
		opt.orElse(new Person());
		
		// person不为null时返回person，为null时 抛出自定义异常
		opt.orElseThrow(NullPointerException::new);
//		opt.orElseThrow(() -> new NullPointerException());
//		opt.orElseThrow(() -> {
//			System.out.println("-------");
//			return new NullPointerException();
//		}) ;
		
		// person不为null时返回person执行consumer方法，否则什么也不处理
		opt.ifPresent(v -> v.getName());
//		opt.ifPresent(v -> {
//			System.out.println("======");
//			v.getName();
//		});
//		opt.ifPresent(Person :: getName);
		
		// person不为null时，执行Function，返回结果的Optional
		opt.map(v -> v.getName());
//		opt.map(Person::getName);
		
		// person不为null时，执行Function，返回结果的Optional,与map唯一不同的是Function
		// 返回Optional参数
		opt.flatMap(v -> Optional.ofNullable(v.getName()));
		
	}
}
