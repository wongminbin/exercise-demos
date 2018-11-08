package com.wong.file;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
* @author HuangZhibin
* 
* 2018年7月12日 下午5:40:31
*/
public class RenameFile2 {

	@Test
	public void rename() {
		String path = "C:\\Users\\Administrator\\Desktop\\鼎品写字楼";
		String renamePath = "C:\\Users\\Administrator\\Desktop\\鼎品写字楼\\rename\\";
		File file = new File(path);
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				continue;
			}
			String name = f.getName();
			System.out.println(name);
			int index = StringUtils.lastIndexOf(name, "-");
			StringBuilder sb = new StringBuilder(name);
			sb.replace(index, index+1, "_");
			
			String rename = sb.toString();
			
			f.renameTo(new File(renamePath + rename));
		}
	}
}
