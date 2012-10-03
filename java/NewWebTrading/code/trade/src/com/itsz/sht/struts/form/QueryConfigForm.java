package com.itsz.sht.struts.form;

/**
 * $Id: QueryConfigForm.java,v 1.1 2010/12/01 06:33:16 kyzou Exp $
 * @Project:portal
 * @File:QueryParamForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-3-25
 */
public class QueryConfigForm extends ITSZForm {
	private String configKey;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
}
