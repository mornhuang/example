package com.itsz.sht.common.model.common.response;

import com.itsz.sht.common.model.common.AbstractResponseModel;

public class UserProfileResponseModel extends AbstractResponseModel{
	
	private  String clientId;
	
	private String clientLoginId;

	private  String defaultDebitAccount;
	
	private  String	chinaDiscountFlag;

	public String getClientLoginId() {
		return clientLoginId;
	}

	public void setClientLoginId(String clientLoginId) {
		this.clientLoginId = clientLoginId;
	}

	public String getDefaultDebitAccount() {
		return defaultDebitAccount;
	}

	public void setDefaultDebitAccount(String defaultDebitAccount) {
		this.defaultDebitAccount = defaultDebitAccount;
	}

	public String getChinaDiscountFlag() {
		return chinaDiscountFlag;
	}

	public void setChinaDiscountFlag(String chinaDiscountFlag) {
		this.chinaDiscountFlag = chinaDiscountFlag;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
