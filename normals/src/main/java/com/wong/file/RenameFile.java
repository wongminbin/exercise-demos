package com.wong.file;

import java.io.File;
import java.util.Optional;

/**
* @author HuangZhibin
* 
* 2018年6月6日 上午10:45:02
*/
public class RenameFile {

	public static void main(String[] args) {
		String dir = "C:\\Users\\Administrator\\Desktop\\20161";
		File dirFile = new File(dir);
		if (dirFile.isDirectory()) {
			File[] files = Optional.ofNullable(dirFile.listFiles()).orElse(new File[] {});
			for (File file : files) {
				if (file.isDirectory()) {
					renameListFileName(file);
				}
			}
		}
	}
	
	private static void renameListFileName(File file) {
		File[] files = Optional.ofNullable(file.listFiles()).orElse(new File[] {});
		String path = file.getAbsolutePath();
		for (int i = 0; i < files.length; i++) {
			File sub = files[i];
			String suffix = getSuffix(sub.getName());
			sub.renameTo(new File(path + "-" + (i+1) + suffix));
		}
	}
	
	private static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
