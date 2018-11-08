package com.wong.zip;

import java.io.File;

import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

/**
* @author HuangZhibin
* 
* 2018年7月14日 下午4:48:43
*/
public class ZipDemo {

	private String desktop = "C:\\Users\\Administrator\\Desktop\\";
	private File zip = new File(desktop + "K9PaymentDemo.zip");
	private File file = new File(desktop + "echarts");
	
	@Test
	public void unzip() {
		ZipUtil.unpack(zip, new File(desktop));
		
//		ZipUtil.explode(zip);
		
	}
	
	@Test
	public void containsFile() {
		System.out.println(ZipUtil.containsEntry(zip, "K9PaymentDemo/lint.xml"));
		System.out.println(ZipUtil.containsEntry(zip, "K9PaymentDemo/lint.txt"));
	}
	
	@Test
	public void iterators() {
//		ZipUtil.iterate(zip, (is, entry) -> {
//			System.out.println(entry.getName());
//		});
//		
		ZipUtil.iterate(zip, (entry) -> {
			System.out.println(entry.getName());
		});
	}
	
	@Test
	public void zip() {
		ZipUtil.pack(file, new File(desktop + "echarts.zip"));
		
//		ZipUtil.unexplode(file);
	}
}
