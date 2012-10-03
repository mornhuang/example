/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.util;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import com.itsz.common.Constants;

public class LangUtil {

	public static String locale2lang(Locale locale) {
		if (locale == null) {
			return "C";
		}
		if (Locale.US.toString().equals(locale.toString())) {
			return "en";
		}
		if (Locale.SIMPLIFIED_CHINESE.toString().equals(locale.toString())) {
			return "GB";
		}
		return "C";
	}

	public static void saveLocale2Session(HttpSession session, String lang) {
		Locale lc = Locale.US;
		String userLang="EN";
		if(lang!=null){
			if (lang.equals("C")) {
				lc = Locale.TRADITIONAL_CHINESE;
				userLang=lang;
			} else if (lang.equals("GB")) {
				lc = Locale.SIMPLIFIED_CHINESE;
				userLang=lang;
			}
		}
		session.setAttribute(Constants.defaultLocaleAttributeName, lc);
		session.setAttribute(Constants.LANGUAGE,userLang);
	}
}
