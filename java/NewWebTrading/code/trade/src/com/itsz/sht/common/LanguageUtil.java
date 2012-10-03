package com.itsz.sht.common;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * $Id: LanguageUtil.java,v 1.1 2010/11/09 03:57:25 kyzou Exp $
 * @Project:portal.head
 * @File:LanguageUtil.java
 * @Description:
 * @Author:
 * @Date:2007-6-5
 */
public class LanguageUtil {

	public static Locale lang2locale(String lang) {
		Locale loc = Locale.TRADITIONAL_CHINESE;
		if(StringUtils.isNotBlank(lang)){
			if(Consts.Global.Language.ENG.equals(lang)){
				loc = Locale.US;
			}else if(Consts.Global.Language.GB.equals(lang)){
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
	public static String convertLang(String lang){
		String res = "GB";
		if("ENG".equals(lang)){
			res = "Eng";
		}else if("BIG5".equals(lang)){
			res = "Big5";
		}
		return res;
	}
}
