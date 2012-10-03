package com.taifook.adminportal.common.parameter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParameterManager {

	private static final Map paramMap = new HashMap();

	private static Log log = LogFactory.getLog(ParameterManager.class);

	private ParameterManager() {

	}

	public static void putParameter(String key, String value) {
		paramMap.put(key, value);
		log.info("paramName=" + key + "		paramValue=" + value);
	}

	public static String getParameterValueByKey(String key) {
		return (String) paramMap.get(key);
	}

	public static void clear() {
		paramMap.clear();
	}

}
