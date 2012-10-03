package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: ChangeTAndCRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:ChangeTAndCRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-5-26
 */
public class ChangeTAndCRequestModel  extends AbstractRequestModel {
	private String loginId;
	private String newTermsAndConditions;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getNewTermsAndConditions() {
		return newTermsAndConditions;
	}
	public void setNewTermsAndConditions(String newTermsAndConditions) {
		this.newTermsAndConditions = newTermsAndConditions;
	}

}
