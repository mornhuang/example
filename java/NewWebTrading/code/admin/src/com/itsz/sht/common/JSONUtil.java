package com.itsz.sht.common;

import net.sf.json.JSONObject;

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