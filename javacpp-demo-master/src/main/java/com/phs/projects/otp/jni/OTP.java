package com.phs.projects.otp.jni;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Platform;

/**
 * 
 * @author HuangZhiBin
 *
 */

@Platform(include="OTP.h", link="ftauthng", linkpath="jni")
public class OTP extends Pointer {

	static {
		Loader.load();
	}
	
	public OTP() {
		allocate();
	}
	
	public native void allocate();

	/**
	 * 
	 * @return
	 */
	public native String version();
	
	/**
	 * 
	 * @param priateData
	 * @param pin
	 * @param serial
	 * @return
	 */
	public native boolean enableToken(String priateData, String pin, String serial);
	
	/**
	 * 
	 * @param priateData
	 * @param pin
	 * @param code
	 * @param curSeconds
	 * @return
	 */
	public native boolean checkCode(String priateData, String pin, String code, long curSeconds);
	
	/**
	 * 
	 * @param priateData
	 * @param curSeconds
	 * @return
	 */
	public native void genCode(String priateData, long curSeconds);
}
