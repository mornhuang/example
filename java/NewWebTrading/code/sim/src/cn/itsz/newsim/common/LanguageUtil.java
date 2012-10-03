package cn.itsz.newsim.common;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LanguageUtil {
	public static Locale lang2locale(String lang) {
		Locale loc = Locale.TRADITIONAL_CHINESE;
		if(StringUtils.isNotBlank(lang)){
			if(Constants.Lang.ENG.equals(lang)){
				loc = Locale.US;
			}else if(Constants.Lang.GB.equals(lang)){
				loc = Locale.SIMPLIFIED_CHINESE;
			}
			
		}
		return loc;
	}
	
	/**
	 * 语言版本的表示不统一造成的
	 * @Author:Cimenon Saint
	 * @Time:2007-10-9 上午10:43:11
	 * @param lang
	 * @return
	 */
	public static String convertLang(final String lang){
		String res = "GB";
		if("ENG".equals(lang)){
			res = "Eng";
		}else if("BIG5".equals(lang)){
			res = "Big5";
		}
		return res;
	}
	
	public static String converCLV(String clv) {
		String lang;
		if(StringUtils.isBlank(clv)){
			lang = "GB";
	    } else if (clv.startsWith("WG25")){
			lang = "GB";
		} else if (clv.startsWith("WE25")){
			lang = "ENG";
		} else {
			lang = "BIG5";
		}
		return lang;
	}
	
	public static String cookie2CLV(HttpServletRequest request) {
		Cookie[] c = request.getCookies();
		String CLV = "WT25S";
		if(c!= null){
			for(int i=0;i<c.length;i++){
				Cookie co = c[i];
				if(Constants.Common.COOKIE_LANGUAGE.equals(co.getName())){
					CLV = co.getValue();
					break;
				}
			}
		}
		return CLV;
	}
	
	public static void saveCookie(HttpServletRequest request,
			HttpServletResponse response, String clv) {
		Cookie[] c = request.getCookies();
		if( c!= null&&c.length>0){
			for(int i=0;i<c.length;i++){
				if(Constants.Common.COOKIE_LANGUAGE.equals(c[i].getName())){
					c[i].setValue(clv);  
					response.addCookie(c[i]);    
					break;
				}
			}
		}else{
			Cookie cookie = new Cookie(Constants.Common.COOKIE_LANGUAGE, clv);
        	cookie.setMaxAge(Constants.Common.COOKIE_MAX_AGE);
        	response.addCookie(cookie);
		}
	}
}
