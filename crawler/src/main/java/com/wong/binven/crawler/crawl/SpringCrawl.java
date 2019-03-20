package com.wong.binven.crawler.crawl;

import org.springframework.beans.factory.InitializingBean;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 上午10:30:51
 */
public abstract class SpringCrawl implements InitializingBean {
	
	protected String classpath = "com.wong.binven.crawler";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public abstract void init();
}
