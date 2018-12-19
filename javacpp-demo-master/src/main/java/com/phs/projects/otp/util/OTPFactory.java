package com.phs.projects.otp.util;

import com.phs.projects.otp.jni.OTP;

public class OTPFactory {

	private OTPFactory() {}
	
	private static final OTP OTP = new OTP();

	private static final String PRI_DATA = "gQAKMDAwMDAwMDAwMxRWVYqACeqvQ3oeHhTcpl/"
													+ "GLyQLFCpuAAACBgAAAAAAADwAAAAAAAAAAAAAO5rKAHc1lAAACv"
													+ "////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
													+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAABtPQ1JBLTE6SE9UUC1TSEEx"
													+ "LTg6UU4wOC1UMU0bT0NSQS0xOkhPVFAtU0hBMS04OlFONjQtVDF"
													+ "NF09DUkEtMTpIT1RQLVNIQTEtODpRTjA4AAAAAAAAAAAAAAAAAHCl";
	
	private static final String TEST_PIN = "1234";
	
	private static final String TOTP_SN = "0000000003";
	
	public static String version() {
		return OTP.version();
	}
	
	public static void main(String[] args) {
//		System.out.println(OTP.version());
		System.out.println(OTP.enableToken(PRI_DATA, TEST_PIN, TOTP_SN));
//		OTP.genCode(PRI_DATA, System.currentTimeMillis()/1000);
	}
}
