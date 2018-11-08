package com.wong.security;

import org.mindrot.jbcrypt.BCrypt;

/**
* @author Huang zhibin
* 
* 2017年8月30日 下午1:46:30
*/
public class BcryptTest {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String salt = BCrypt.gensalt();
			System.out.println(salt);
			String hashed = BCrypt.hashpw("123456", salt);
			System.out.println(hashed);
			System.out.println("========================================");
		}
		
		
//		String hashed = BCrypt.hashpw("123456", BCrypt.gensalt(10));
//		
//		System.out.println(hashed);
//		
//		boolean check = BCrypt.checkpw("123456", hashed);
//		
//		System.out.println(check);
//		long allStart = System.currentTimeMillis();
//		for (int i = 0; i < 1000; i++) {
//			long start = System.currentTimeMillis();
//			BCrypt.hashpw("123456"+i, BCrypt.gensalt(12));
//			System.out.println("i=" + i + " use time is " + (System.currentTimeMillis() - start));
//		}
//		System.out.println("total use time is " + (System.currentTimeMillis() - allStart));
	}
}