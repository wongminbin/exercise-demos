package com.wong.browers;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author HuangZhibin
 * 
 *         2018年7月26日 上午9:41:11
 */
public class BrowersTest {

	@Test
	public void load() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());
			
			HtmlPage page = webClient.getPage("http://we.zgps168.com/estore/v234/wx_address.html?wx_token=0a5e2d3a-84a3-451c-9f2c-55cacf6f5e91");
			
			System.out.println(page.querySelector("div#receivingAddress").asXml());
			
		}

	}
}
