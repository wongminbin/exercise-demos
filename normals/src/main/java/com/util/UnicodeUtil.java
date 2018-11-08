package com.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Huang zhibin
 * 
 *         2017年10月25日 下午2:13:35
 */
public class UnicodeUtil {

	private static final char[] HEX_CHARS = new char[] { 
			'0', '1', '2', '3', '4', '5', '6', '7', 
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String unicodeEncoder(char c) {
		StringBuilder sb = new StringBuilder("\\u");
		for (int i = 12; i >= 0; i -= 4) {
			char t = HEX_CHARS[c >> i & 0xf];
			sb.append(t);
		}
		return sb.toString();
	}

	public static char unicodeDecoder(String unicode) {
		if (StringUtils.containsAny(unicode, "\\u", "\\U")) {
			unicode = unicode.substring(2);
		}
		byte[] bs = new byte[2];
		for (int i = 0; i < 2; i++) {
			bs[i] = (byte) Integer.parseUnsignedInt(unicode.substring(i * 2, i * 2 + 2), 16);
		}

		return (char) ((bs[0] & 0xff) << 8 | (bs[1] & 0xff));
	}

}
