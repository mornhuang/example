package com.itsz.sht.common.model.response.login;

import java.sql.Timestamp;
import java.util.Collection;


/**
 * $Id: LoginModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:LoginModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-23
 */
public class LoginModel {
	
	private String custCode;
    private String transactionProtection;
    private String firstLoginFlag;
    private Timestamp firstLoginDate; 
    private String allowTradeStatusFlag;
    private String tradingAccount;
    private Collection<String> tradingAccountCol;
    private String newSubCode;
    private String custName;
    private String loginId;
    private String messageSEQNum;
    private String loginStatus;
    private String termsAndConditions;
    private String basketEnable;
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getMessageSEQNum() {
		return messageSEQNum;
	}
	public void setMessageSEQNum(String messageSEQNum) {
		this.messageSEQNum = messageSEQNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getAllowTradeStatusFlag() {
		return allowTradeStatusFlag;
	}
	public void setAllowTradeStatusFlag(String allowTradeStatusFlag) {
		this.allowTradeStatusFlag = allowTradeStatusFlag;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getFirstLoginFlag() {
		return firstLoginFlag;
	}
	public void setFirstLoginFlag(String firstLoginFlag) {
		this.firstLoginFlag = firstLoginFlag;
	}
	public String getTradingAccount() {
		return tradingAccount;
	}
	public void setTradingAccount(String tradingAccount) {
		this.tradingAccount = tradingAccount;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getNewSubCode() {
		return newSubCode;
	}
	public void setNewSubCode(String newSubCode) {
		this.newSubCode = newSubCode;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Collection<String> getTradingAccountCol() {
		return tradingAccountCol;
	}
	public void setTradingAccountCol(Collection<String> tradingAccountCol) {
		this.tradingAccountCol = tradingAccountCol;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public Timestamp getFirstLoginDate() {
		return firstLoginDate;
	}
	public void setFirstLoginDate(Timestamp firstLoginDate) {
		this.firstLoginDate = firstLoginDate;
	}
	public String getBasketEnable() {
		return basketEnable;
	}
	public void setBasketEnable(String basketEnable) {
		this.basketEnable = basketEnable;
	}

}
