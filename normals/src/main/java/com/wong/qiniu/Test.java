package com.wong.qiniu;

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
*
* @author Huang Zhibin
* 2017年7月17日 上午9:42:41
*
*/

public class Test {

	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	private static final UploadManager UPLOAD = new UploadManager(new Configuration(Zone.zone0()));
	
	private static final String DOMAIN = "";
	
	private static String SUB_STRING = "";
	
	//需要上传的文件或文件夹
	private static final String UPLOAD_ROOT_PAHT = "C:/Users/Administrator/Desktop/test";
	
	public static void main(String[] args) {
		Test test = new Test();
		
		String upToken = test.getUpToken();
		File rootDiretory = new File(UPLOAD_ROOT_PAHT);
		
		SUB_STRING = rootDiretory.getParent()+ "\\";
		test.upload(rootDiretory, upToken);
	}
	
	private void upload(File file, String upToken) {
		if (file.isDirectory()) {
			File[] fs = null;
			if (ArrayUtils.isNotEmpty((fs = file.listFiles()))) {
				for (File f : fs) {
					upload(f, upToken);
				}
			}
		} else {
			try {
				String key = file.getAbsolutePath().replace(SUB_STRING, "").replace("\\", "/");
				
				Response response = UPLOAD.put(file, key, upToken);
				//解析上传成功的结果
			    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				
				System.out.println(DOMAIN + putRet.key);
			} catch (QiniuException e) {
				e.printStackTrace();
				LOGGER.info("--------can't upload : " + file.getAbsolutePath());
			}
		}
		
	}
	
	private Auth getAuth() {
		String accessKey = "";
		String secretKey = "";
		return Auth.create(accessKey, secretKey);
	}
	
	private String getUpToken() {
		String bucket = "";
		Auth auth = getAuth();
		return auth.uploadToken(bucket);
	}
}
