package com.wong.html2Image;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import gui.ava.html.Html2Image;

/**
* @author HuangZhibin
* 
* 2018年7月19日 下午4:11:08
*/
public class Html2ImageTest {

	@Test
	public void test() throws MalformedURLException {
		String url = "http://www.linkshow.com.cn/";
		Html2Image.fromURL(new URL(url)).getImageRenderer()
			.saveImage("C:\\Users\\Administrator\\Desktop\\test.png");
	}
	
	@Test
	public void test1() {
		Html2Image.fromFile(new File("C:\\Users\\Administrator\\Desktop\\test1.html"))
			.getImageRenderer()
			.saveImage("C:\\Users\\Administrator\\Desktop\\test1.png");;
	}
	
}
