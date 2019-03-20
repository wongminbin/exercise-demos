package com.wong.binven.crawler.test;

import com.geccocrawler.gecco.utils.UrlMatcher;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午7:53:55
 */

@Getter
@Setter
public class Demo {

	private String name;
	
	@Getter
	@Setter
	public static class Test {
		
		private int age;
	}
	
	public static void main(String[] args) throws Exception {
		Class<Test> clazz = Test.class;
		Test test = clazz.newInstance();
		
		test.setAge(18);
		
		System.out.println(test.getAge());
		
		
		UrlMatcher.match("https://item.jd.com/3133827.html?dist=jd", "https://item.jd.com/{code}.html.*")
			.forEach((k, v)->System.out.println(k + "=" + v));
	}
}
