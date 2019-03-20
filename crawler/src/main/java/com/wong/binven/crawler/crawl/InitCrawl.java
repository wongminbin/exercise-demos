package com.wong.binven.crawler.crawl;

import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.GeccoEngine;

/**
 * create by: HuangZhiBin
 * 2019年3月18日 下午8:20:13
 */

@Service
public class InitCrawl extends SpringCrawl {

	private String[] urls = new String[] {
			"https://github.com/xtuhcy/gecco", 
			"https://www.jd.com/allSort.aspx"
			};
	
	@Override
	public void init() {
		// 开始抓取
		GeccoEngine.create()
			.classpath(classpath)
			.start(urls)
			.thread(8)
			.interval(2000)
			.start();
	}

}
