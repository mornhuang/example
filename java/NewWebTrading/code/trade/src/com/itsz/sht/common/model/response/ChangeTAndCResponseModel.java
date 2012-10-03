package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: ChangeTAndCResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:ChangeTAndCResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-5-26
 */
public class ChangeTAndCResponseModel extends AbstractResponseModel {
	private String loginId;
	private String tAndCUpdate;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTAndCUpdate() {
		return tAndCUpdate;
	}
	public void setTAndCUpdate(String andCUpdate) {
		tAndCUpdate = andCUpdate;
	}
}
