package com.wong.binven.crawler.pipeline;

import org.apache.commons.lang3.StringUtils;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.wong.binven.crawler.core.Log;
import com.wong.binven.crawler.rule.JDProductsRule;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午6:56:54
 */

@PipelineName("jdProductPipeline")
public class JDProductPipeline implements Pipeline<JDProductsRule>, Log {

	@Override
	public void process(JDProductsRule rule) {
		HttpRequest request = rule.getRequest();
		//下一页继续抓取
		int currPage = rule.getCurrPage();
		int nextPage = currPage + 1;
		int totalPage = rule.getTotalPage();
		if(nextPage <= totalPage) {
			String nextUrl = "";
			String currUrl = request.getUrl();
			if(currUrl.indexOf("page=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "page=" + nextPage;
			}
			DeriveSchedulerContext.into(request.subRequest(nextUrl));
			
			getLog().info("jd products url: {}", nextUrl);
		}
		
	}

}
