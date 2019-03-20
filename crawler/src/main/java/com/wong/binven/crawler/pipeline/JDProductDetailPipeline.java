package com.wong.binven.crawler.pipeline;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.wong.binven.crawler.core.Log;
import com.wong.binven.crawler.rule.JDProductDetailRule;


/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午7:07:40
 */

@PipelineName("jdProductDetailPipeline")
public class JDProductDetailPipeline implements Pipeline<JDProductDetailRule>, Log {

	@Override
	public void process(JDProductDetailRule rule) {
		
		getLog().info("================================================");
		getLog().info("");
		getLog().info(JSON.toJSONString(rule));
		getLog().info("");
		getLog().info("================================================");
	}

}
