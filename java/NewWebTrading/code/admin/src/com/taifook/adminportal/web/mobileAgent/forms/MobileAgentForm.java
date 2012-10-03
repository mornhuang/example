package com.taifook.adminportal.web.mobileAgent.forms;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * $Id: MobileAgentForm.java,v 1.2 2010/11/09 04:31:54 kyzou Exp $
 * @Project:admin-portal
 * @File:MobileAgentForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-26
 */
public class MobileAgentForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramName;

	private String paramValue;

	private String description;

	private String updateTime;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
