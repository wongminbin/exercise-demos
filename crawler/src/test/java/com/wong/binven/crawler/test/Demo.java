package com.wong.binven.crawler.test;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.utils.UrlMatcher;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin 2019年3月19日 下午7:53:55
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
				.forEach((k, v) -> System.out.println(k + "=" + v));
	}

	public static void editRules() throws InterruptedException {
		// 初始化爬虫引擎，此时由于没有初始请求，爬虫引擎会阻塞初始队列，直到获取到初始请求
		GeccoEngine ge = GeccoEngine.create("com.geccocrawler.gecco.demo.dynamic").interval(5000).loop(true)
				.engineStart();
		
		// 定义爬取规则
		Class<?> rule = DynamicGecco.html().gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
				.requestField("request").request().build()
				.stringField("title").csspath(".repository-meta-content").text(false).build()
				.intField("star").csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
				.intField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
				.loadClass();

		// 注册规则
		ge.register(rule);

		// 加入初始请求，爬虫引擎开始工作
		ge.getScheduler().into(new HttpGetRequest("https://github.com/xtuhcy/gecco"));

		Thread.sleep(5000);

		System.out.println("修改规则");

		try {
			// 开始更新规则
			ge.beginUpdateRule();
			// 修改规则
			Class<?> newRule = DynamicGecco.html(rule.getName())
					.gecco("https://github.com/xtuhcy/gecco", "consolePipeline")
					.intField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
					.removeField("star")
					.loadClass();
			// 注册新规则
			ge.register(newRule);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 规则更新完毕
			ge.endUpdateRule();
		}

		Thread.sleep(5000);

		System.out.println("下线规则");
		try {
			// 开始更新规则
			ge.beginUpdateRule();
			// 下线之前的规则（也支持不下线规则，直接修改）
			ge.unregister(rule);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 规则更新完毕
			ge.endUpdateRule();
		}
		
	}

}
