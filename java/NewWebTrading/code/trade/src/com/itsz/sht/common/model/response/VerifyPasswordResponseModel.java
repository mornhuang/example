package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.VerifyPasswordResponse;

/**
 * $Id: VerifyPasswordResponseModel.java,v 1.2 2011/03/10 03:03:58 xli Exp $
 * @Project:portal
 * @File:VerifyPasswordResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-12
 */
public class VerifyPasswordResponseModel  extends AbstractResponseModel{
	private String loginId;
	private String passwordMatch;
	private VerifyPasswordResponse verifyPwdResponse;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPasswordMatch() {
		return passwordMatch;
	}
	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
	public VerifyPasswordResponse getVerifyPwdResponse() {
		return verifyPwdResponse;
	}
	public void setVerifyPwdResponse(VerifyPasswordResponse verifyPwdResponse) {
		this.verifyPwdResponse = verifyPwdResponse;
	}
}
