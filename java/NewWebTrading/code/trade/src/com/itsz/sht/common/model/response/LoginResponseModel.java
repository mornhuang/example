package com.itsz.sht.common.model.response;

import java.util.Collection;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.common.model.response.login.RtqResponseModel;
import com.taifook.mcs.core.beans.msg.LoginResponse;

/**
 * $Id: LoginResponseModel.java,v 1.2 2010/11/24 06:10:20 kyzou Exp $
 * @Project:portal.head
 * @File:LoginResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class LoginResponseModel extends AbstractResponseModel {

	private LoginResponse loginResponse;
	private RtqResponseModel rtqResponseModel;
	private Collection errorCodes;
	private String loginId;

	public Collection getErrorCodes() {
		return errorCodes;
	}
	public RtqResponseModel getRtqResponseModel() {
		return rtqResponseModel;
	}
	public void setRtqResponseModel(RtqResponseModel rtqResponseModel) {
		this.rtqResponseModel = rtqResponseModel;
	}
	public void setErrorCodes(Collection errorCodes) {
		this.errorCodes = errorCodes;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public LoginResponse getLoginResponse() {
		return loginResponse;
	}
	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
}
