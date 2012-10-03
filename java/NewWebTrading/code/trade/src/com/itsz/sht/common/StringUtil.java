package com.itsz.sht.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * $Id: StringUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * 
 * @Project:portal
 * @File:StringUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-5
 */
public class StringUtil {

	public StringUtil() {
	}

	public static String toUTF8(String src) {
		String s = null;
		try {
			s = new String(src.getBytes("UTF8"));
		} catch (Exception e) {
		}
		return s;
	}

	public static String addLeadingZero(String inString, int length) {
		StringBuffer bs = new StringBuffer(inString);
		int i = 0;
		int size = bs.length();
		for (int diff = length - size; diff > i; i++)
			bs.insert(0, '0');

		return bs.toString();
	}

	public static Collection string2Collection(String str, String delim, boolean includeRepeat) {
		List rs = new ArrayList();
		if (str != null) {
			String temp = "";
			StringTokenizer st = new StringTokenizer(str, delim);
			while (st.hasMoreTokens()) {
				temp = st.nextToken();
				if (temp != null) {
					if (!includeRepeat && rs.contains(temp)) {
						continue;
					} else {
						rs.add((String)temp);
					}
				}
			}
		}
		return rs;
	}
	
	public static String Object2String(Object obj){
		if(obj==null){
			return null;
		}
		return obj.toString();
	}
}
