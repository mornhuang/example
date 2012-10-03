package com.itsz.sht.common.model.common.request;

import com.itsz.sht.common.model.common.AbstractRequestModel;

public class UserProfileRequestModel extends AbstractRequestModel {

	private String clientId;
	
	private String clntLoginId;
	
	private String defaultDebitAccount;
	
	private String chinaDiscountFlag;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClntLoginId() {
		return clntLoginId;
	}
	public void setClntLoginId(String clntLoginId) {
		this.clntLoginId = clntLoginId;
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
	
	
}
