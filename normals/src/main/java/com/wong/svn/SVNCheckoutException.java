package com.wong.svn;

/**
* @author Huang zhibin
* 
* 2017年9月14日 下午2:19:25
*/
public class SVNCheckoutException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6602846415373635740L;

	public SVNCheckoutException(String message) {
		super(message);
	}
	
	public SVNCheckoutException(String message, Exception e) {
		super(message, e);
	}
}
