/**
 * 
 */
package com.itsz.web.rtq.util;

/**
 * @author swliu
 *
 */
public class TFStartLangConvert {

	public static String convert(String sourceLang) {
		String convertedLang = "en";
		if (sourceLang.equals("C")) {
			convertedLang = "zh_TW";
		} else
			if (sourceLang.equals("GB")) {
				convertedLang = "zh_CN";
			}

		return convertedLang;
	}
}
