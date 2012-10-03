package com.itsz.sht.common.model.response;

/**
 * $Id: ConfigInfo.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:ConfigInfo.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-3-26
 */
public class ConfigInfo {
	private String configKey;
	private String configValue;
	public ConfigInfo(String paramKey,String paramValue) {
		this.configKey = paramKey;
		this.configValue = paramValue;
	}
	public ConfigInfo() {
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String paramValue) {
		this.configValue = paramValue;
	}
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String paramKey) {
		this.configKey = paramKey;
	}

}
