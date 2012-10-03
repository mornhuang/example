package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: PPSEnquiryRequestModel.java,v 1.3 2010/12/08 09:45:47 kyzou Exp $
 * @Project:NewWebTrading
 * @File:PPSEnquiryRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class PPSEnquiryRequestModel extends AbstractRequestModel {
	private String loginId;
	private String accountId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
