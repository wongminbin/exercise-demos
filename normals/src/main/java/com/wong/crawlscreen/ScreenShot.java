package com.wong.crawlscreen;

import java.io.IOException;

import org.junit.Test;

/**
* @author HuangZhibin
* 
* 2018年7月26日 上午11:34:02
* @see http://phantomjs.org/
*/
public class ScreenShot {
	
	private static final String PHANTOM = "D:/phantomjs-2.1.1-windows/";
	private static final String BLANK = "  ";
	private static final String EXE = "bin/phantomjs.exe";
	private static final String SHOT_SCREEN = "examples/screenshot.js";

	private static final String INVOKE_CMD = PHANTOM + EXE + BLANK + //你的phantomjs.exe路径
											 PHANTOM + SHOT_SCREEN + BLANK + //就是上文中那段javascript脚本的存放路径
											 "%s" + BLANK + "%s"; // args[0]你的目标url地址, args[1]你的图片输出路径
	@Test
    public void screen() throws IOException {
       screen("https://www.baidu.com", 
    		  "D:/baidu2.png");
    }
	
	public void screen(String url, String img) throws IOException {
		Runtime.getRuntime().exec(String.format(INVOKE_CMD, url, img));//你的图片输出路径
	}
	
}
