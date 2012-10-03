package com.itsz.sht.common.beanutil;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * $Id: StringDecimalConverter.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:StringDecimalConverter.java
 * @Description:
 * @Author:edxu
 * @Date:2007-6-29
 */
public class StringDecimalConverter implements Converter {

	public Object convert(Object origObj) {
		String str = (String) origObj;
		BigDecimal res = null;
		try{
			if(StringUtils.isNotBlank(str)){
				res = new BigDecimal(str);
			}				
		}catch (NumberFormatException ex) {
			//ex.printStackTrace();
		}
		return res;
	}
}
