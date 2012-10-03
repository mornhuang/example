package com.itsz.sht.common;

import net.sf.json.JSONObject;

/**
 * $Id: JSONUtil.java,v 1.2 2010/12/08 02:03:57 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:JSONUitl.java
 * @Description:
 * @Author:swliu
 * @Date:2007-5-24
 */
public class JSONUtil {

	/**
	 * 
	 * bean to json
	 * 
	 * @Time:15:14:06
	 * @param bean
	 * @return
	 */
	public static String bean2JSON(Object bean) {
		return JSONObject.fromObject(bean).toString();
	}

}
