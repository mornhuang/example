package com.itsz.sht.common.beanutil;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * $Id: StringLongConverter.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:StringLongConverter.java
 * @Description:
 * @Author:edxu
 * @Date:2007-6-29
 */
public class StringLongConverter implements Converter {

	public Object convert(Object origObj) {		
		Long res = null;
		try{
			if(origObj!=null){
				String str = (String)origObj;
				if(StringUtils.isNotBlank(str)){
					res = new Long((str));	
				}				
			}			
		}catch (NumberFormatException ex) {
			//ex.printStackTrace();
		}
		return res;
	}
}
