package com.wong.binven.crawler.rule;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.SpiderBean;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午5:45:00
 */

@Getter
@Setter
@Gecco(matchUrl="https://www.jd.com/allSort.aspx", pipelines="jdSortPipeline")
public class JDSortRule implements SpiderBean, Rule {

	private static final long serialVersionUID = -893513055871658803L;

	@Request
	private HttpRequest request;

	//手机
	@HtmlField(cssPath=".category-items > div:nth-child(1) > div:nth-child(2) > div.mc > div.items > dl")
	private List<Category> mobile;
	
	//家用电器
	@HtmlField(cssPath=".category-items > div:nth-child(1) > div:nth-child(3) > div.mc > div.items > dl")
	private List<Category> domestic;

	@Getter
	@Setter
	public static class Category implements SpiderBean {
		
		private static final long serialVersionUID = -280585173942695799L;
		
		@Text
		@HtmlField(cssPath="dt a")
		private String parentName;
		
		@HtmlField(cssPath="dd a")
		private List<HrefBean> categorys;
	}
}
