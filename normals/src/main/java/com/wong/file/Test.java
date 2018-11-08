package com.wong.file;

import java.io.File;

/**
* @author Huang zhibin
* 
* 2017年11月28日 上午9:10:03
*/
public class Test {

	private static final String DIR = "D:\\development\\workspace-test\\esl-projects\\esl-service\\src\\main\\resources\\mybatis";
	
	private static String name;
	
	private static long bytes;
	
	public static void main(String[] args) {
		File dir = new File(DIR);
		selectFileMaxByte(dir);
		
		System.out.println(name + "--" + (bytes/1024));
	}
	
	private static void selectFileMaxByte(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				selectFileMaxByte(f);
			}
		} else {
			String name = file.getAbsolutePath();
			long bytes = file.length();
			if (bytes > Test.bytes) {
				Test.name = name;
				Test.bytes = bytes;
			}
		}
	}
}
