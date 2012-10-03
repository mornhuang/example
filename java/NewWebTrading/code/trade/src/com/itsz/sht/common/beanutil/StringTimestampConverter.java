package com.itsz.sht.common.beanutil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * $Id: StringTimestampConverter.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:StringTimestampConverter.java
 * @Description:
 * @Author:edxu
 * @Date:2007-6-29
 */
public class StringTimestampConverter implements Converter {

	public Object convert(Object origObj) {
		Timestamp res = null;
		try{
			if(origObj!=null){
				String str = (String)origObj;
				if(StringUtils.isNotBlank(str)){
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = format.parse(str);
					res = new Timestamp(date.getTime());
				}				
			}
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return res;
	}

}
