package com.wong.binven.crawler.rule.aop;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.BeforeDownload;
import com.geccocrawler.gecco.request.HttpRequest;
import com.wong.binven.crawler.rule.JDProductDetailRule;

/**
 * create by: HuangZhiBin
 * 2019年3月20日 上午9:17:49
 */

@GeccoClass(JDProductDetailRule.class)
public class JDProductDetailBeforeDownload implements BeforeDownload {

	@Override
	public void process(HttpRequest request) {
		// 框架默认使用UTF-8
		// @Why
		// @在JDProductDetailRule中的@Ajax请求返回的内容的字符集是GB18030
		// @所以在下载@Ajax之前将request的编码改为GB18030
		request.setCharset("GB18030");
		// accept-language: zh-CN,zh;q=0.9
		request.addHeader("accept-language", "zh-CN,zh;q=0.9");
	}

}
