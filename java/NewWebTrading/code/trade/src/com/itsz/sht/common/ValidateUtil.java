package com.itsz.sht.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * $Id: ValidateUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * 
 * @Project:portal
 * @File:ValidateUtil.java
 * @Description:
 * @Author:swLiu
 * @Date:2007-5-28
 */
public class ValidateUtil {
	
	private static final Pattern numPattern = Pattern.compile("^([-+]?[0-9]+(\\.[0-9]*)?)$"); 

	public static boolean isBlankString(String str) {
		return str == null || str.trim().equals("");
	}

	public static boolean isBlankObj(Object obj) {
		return obj == null;
	}

	public static boolean isNum(String numstr) {		 
		Matcher m = numPattern.matcher(numstr);
		boolean b = true;
		b = m.matches();
		return b;
	}

}
