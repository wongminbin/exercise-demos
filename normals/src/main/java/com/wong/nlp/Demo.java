package com.wong.nlp;

import org.ansj.domain.Result;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.junit.Before;
import org.junit.Test;

/**
* @author HuangZhibin
* 
* 2018年6月26日 下午6:47:26
*/
public class Demo {

	private String text = "中国广东省广州市天河区员村二横路牧业大厦";

	@Before
	public void init() {
		DicLibrary.insert(DicLibrary.DEFAULT, "大数据");
		DicLibrary.insert(DicLibrary.DEFAULT, "优衣库");
		DicLibrary.insert(DicLibrary.DEFAULT, "员村");
		DicLibrary.insert(DicLibrary.DEFAULT, "横路");
		DicLibrary.insert(DicLibrary.DEFAULT, "牧业大厦");
	}
	
	@Test
	public void test() {
		Result result = DicAnalysis.parse(text);
		result.forEach(r->System.out.println(r.getName()));
	}
	
	
}
