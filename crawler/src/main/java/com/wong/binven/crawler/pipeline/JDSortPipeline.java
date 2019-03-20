package com.wong.binven.crawler.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import com.geccocrawler.gecco.spider.HrefBean;
import com.wong.binven.crawler.core.Log;
import com.wong.binven.crawler.rule.JDSortRule;
import com.wong.binven.crawler.rule.JDSortRule.Category;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午5:46:36
 */

@PipelineName("jdSortPipeline")
public class JDSortPipeline implements Pipeline<JDSortRule>, Log  {

	@Override
	public void process(JDSortRule rule) {
		List<Category> categorys = new ArrayList<>();
		categorys.addAll(rule.getMobile());
		categorys.addAll(rule.getDomestic());
		
		for(Category category : categorys) {
			List<HrefBean> hrefs = category.getCategorys();
			for(HrefBean href : hrefs) {
				String url = href.getUrl() + "&delivery=1&page=1&JL=4_10_0&go=0";
				HttpRequest request = rule.getRequest();
				
				DeriveSchedulerContext.into(request.subRequest(url));
				
				getLog().info("jd sort url: {}", url);
			}
		}
	}

}
