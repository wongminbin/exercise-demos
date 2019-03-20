package com.wong.binven.crawler.rule;

import java.util.List;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.SpiderBean;
import com.wong.binven.crawler.core.Log;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: HuangZhiBin
 * 2019年3月19日 下午6:52:15
 */
@Getter
@Setter
@Gecco(matchUrl="https://list.jd.com/list.html?cat={cat}&delivery={delivery}&page={page}&JL={JL}&go=0", pipelines="jdProductPipeline")
public class JDProductsRule implements SpiderBean, Log {

	private static final long serialVersionUID = -5277679156944188788L;

	@Request
	private HttpRequest request;
	
	/**
	 * 抓取列表项的详细内容，包括titile，价格，详情页地址等
	 */
	@HtmlField(cssPath="#plist .gl-item")
	private List<ProductBrief> details;
	/**
	 * 获得商品列表的当前页
	 */
	@Text
	@HtmlField(cssPath="#J_topPage > span > b")
	private int currPage;
	/**
	 * 获得商品列表的总页数
	 */
	@Text
	@HtmlField(cssPath="#J_topPage > span > i")
	private int totalPage;
	
	@Getter
	@Setter
	public static class ProductBrief implements SpiderBean {

		private static final long serialVersionUID = -2482141094210739573L;
		
		@Attr("data-sku")
		@HtmlField(cssPath=".j-sku-item")
		private String code;
		
		@Text
		@HtmlField(cssPath=".p-name> a > em")
		private String title;
		
		@Image({"data-lazy-img", "src"})
		@HtmlField(cssPath=".p-img > a > img")
		private String preview;
		
		@Href(click=true)
		@HtmlField(cssPath=".p-name > a")
		private String detailUrl;

	}
}
