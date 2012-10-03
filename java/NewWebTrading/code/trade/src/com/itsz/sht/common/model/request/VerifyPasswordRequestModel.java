package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: VerifyPasswordRequestModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:VerifyPassword.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-18
 */
public class VerifyPasswordRequestModel extends AbstractRequestModel {
	private String loginId;
	private String password;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
