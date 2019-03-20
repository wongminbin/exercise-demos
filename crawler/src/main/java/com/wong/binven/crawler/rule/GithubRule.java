package com.wong.binven.crawler.rule;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.SpiderBean;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2019年3月18日 下午8:53:56
 */
@Getter
@Setter
@Gecco(matchUrl="https://github.com/{user}/{project}", pipelines="githubPipeline")
public class GithubRule implements SpiderBean, Rule {
	private static final long serialVersionUID = -7013146417349836466L;

	@RequestParameter("user")
	private String user;
	
	@RequestParameter("project")
	private String project;
	
	@Text(own=false)
	@HtmlField(cssPath="title")
	private String title;
	
	@Text(own=false)
	@HtmlField(cssPath=".pagehead-actions li:nth-child(2) .social-count")
	private String star;
	
	@Text(own=false)
	@HtmlField(cssPath=".pagehead-actions li:nth-child(3) .social-count")
	private String fork;
	
	@Href
	@HtmlField(cssPath="ul.numbers-summary > li > a")
	private List<String> contributors;
	
}
