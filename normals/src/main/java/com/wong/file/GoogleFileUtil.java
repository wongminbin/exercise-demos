package com.wong.file;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import org.junit.Test;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;

/**
* @author HuangZhibin
* 
* 2018年8月29日 下午7:18:23
*/
public class GoogleFileUtil {

	@Test
	public void test() throws IOException {
		FileSystem system = Jimfs.newFileSystem("win",
		        Configuration.forCurrentPlatform()
		            .toBuilder()
		            .setRoots("C:\\", "E:\\")
		            .setAttributeViews("basic", "owner", "dos", "acl", "user")
		            .build());
		
		
		Path path = system.getPath("test", "demo");
		
		System.out.println(path.toString());
	}
}
