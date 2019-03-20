package com.wong.binven.crawler.rule;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.Ajax;
import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.JsonBean;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.wong.binven.crawler.core.Log;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午7:01:23
 */

@Getter
@Setter
@Gecco(matchUrl="https://item.jd.com/{code}.html.*", pipelines="jdProductDetailPipeline")
public class JDProductDetailRule implements SpiderBean, Log {

	private static final long serialVersionUID = -5398132077233937045L;

	@Request
	private HttpRequest request;
	
	/**
	 * 商品代码
	 */
	@RequestParameter
	private String code;
	
	/**
	 * 标题
	 */
	@Text
	@HtmlField(cssPath="body > div:nth-child(11) > div > div.itemInfo-wrap > div.sku-name")
	private String title;
	
	/**
	 * ajax获取商品价格
	 */
	@Ajax(url="https://p.3.cn/prices/get?skuIds=J_[code]")
	private JDPrice price;

	/**
	 * 商品的推广语
	 */
	@Ajax(url="https://cd.jd.com/promotion/v2?skuId={code}&area=1_2805_2855_0&cat=737%2C794%2C798")
	private JDAd jdAd;
	
	/*
	 * 商品规格参数
	 */
	@Attr("title")
	@HtmlField(cssPath="#detail > div.tab-con > div:nth-child(1) > div.p-parameter > ul.parameter2.p-parameter-list > li")
	private List<String> detail;
	
	@Getter
	@Setter
	public static class JDPrice implements JsonBean {

		private static final long serialVersionUID = -3030949909695159415L;
		
		@JSONPath("$.id[0]")
		private String code;
		
		@JSONPath("$.p[0]")
		private float price;
		
		@JSONPath("$.m[0]")
		private float srcPrice;

	}
	
	@Getter
	@Setter
	public static class JDAd implements JsonBean {

		private static final long serialVersionUID = -4397575528579840308L;
		
		@JSONPath("$.ads[0].ad")
		private String ad;

		@JSONPath("$.ads")
		private List<JSONObject> ads;
	}
}
