package cn.itsz.newsim.common;

import net.sf.json.JSONObject;

/**
 * $Id: JSONUtil.java,v 1.1 2011/03/04 06:45:40 bwu Exp $
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
