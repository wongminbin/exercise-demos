package com.wong.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.ansj.dic.PathToStream;
import org.ansj.exception.LibraryException;

/**
* @author HuangZhibin
* 
* 2018年6月26日 下午7:50:19
*/
public class MyPathToStream extends PathToStream {

	@Override
	public InputStream toStream(String path) {
		String root = MyPathToStream.class.getClassLoader().getResource("").getPath();

		root += path.substring(8).split("\\|")[1];
		File file = new File(root);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		throw new LibraryException(" path :" + path + " file:" + file.getAbsolutePath() + " not found or can not to read");
	}

}
