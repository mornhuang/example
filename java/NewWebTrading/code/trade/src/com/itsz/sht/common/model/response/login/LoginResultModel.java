package com.itsz.sht.common.model.response.login;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.common.model.request.LoginRequestModel;
import com.taifook.mcs.core.beans.msg.LoginResponse;

/**
 * $Id: LoginResultModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:LoginResultModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-23
 */
public class LoginResultModel extends AbstractResponseModel {

	private LoginResponse loginResponse;
	private LoginRequestModel loginRequestModel;

	public LoginRequestModel getLoginRequestModel() {
		return loginRequestModel;
	}

	public void setLoginRequestModel(LoginRequestModel loginRequestModel) {
		this.loginRequestModel = loginRequestModel;
	}

	public LoginResponse getLoginResponse() {
		return loginResponse;
	}

	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}


}
