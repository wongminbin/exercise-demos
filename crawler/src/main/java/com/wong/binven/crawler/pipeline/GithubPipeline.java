package com.wong.binven.crawler.pipeline;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.wong.binven.crawler.core.Log;
import com.wong.binven.crawler.rule.GithubRule;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 上午10:02:45
 */

@PipelineName("githubPipeline")
public class GithubPipeline implements Pipeline<GithubRule>, Log {

	@Override
	public void process(GithubRule github) {
		getLog().info(github.getTitle());
	}

}
