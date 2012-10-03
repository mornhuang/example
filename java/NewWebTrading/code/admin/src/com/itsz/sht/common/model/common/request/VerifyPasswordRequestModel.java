package com.itsz.sht.common.model.common.request;


import com.itsz.sht.common.model.common.AbstractRequestModel;
public class VerifyPasswordRequestModel  extends AbstractRequestModel{
	private String loginId;
	private String passwordMatch;
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
}
