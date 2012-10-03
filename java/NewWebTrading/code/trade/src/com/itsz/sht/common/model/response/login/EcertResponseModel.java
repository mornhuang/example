package com.itsz.sht.common.model.response.login;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: EcertResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:EcertResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-6-6
 */
public class EcertResponseModel extends AbstractResponseModel {

    private String loginId;
    private String custCode;
    private String loginStatus;
    private String custName;
    private String smsProvider;
    private String status;
    private Timestamp firstLoginDate;
    private Timestamp lastPasswordAdmenDate;
    private BigDecimal invalidPasswordCount;
    private Timestamp registrationDate;
    private BigDecimal gracefulLoginCount;
    private String gracefulLoginFlag;
    private String trackingFlag;
    private Collection rtqInfoCol;
    private String disclaimer;
    private String transactionProtection;
    private String isValid;
    private String isValidECert;
    private String termsAndConditions;
    private BigDecimal maxInvalidPasswordCount;
    private BigDecimal forceChangePasswordDay;
    private String graceLoginIndicator;
    private BigDecimal maxGraceLoginCount;
    private String firstLoginFlag;
    private String channelStatus;
    private String isValidDocId;
    private String newStatus;
    private String newSubCode;
    private String allowTradeStatusFlag;
    private String shkSubscriptionFlag;
    private String tradingAccount;
    private String conditionalOrderTermsAndConditions;
    private String isRTQInfoAvailable;
    
    
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public Timestamp getFirstLoginDate() {
		return firstLoginDate;
	}
	public void setFirstLoginDate(Timestamp firstLoginDate) {
		this.firstLoginDate = firstLoginDate;
	}
	public BigDecimal getGracefulLoginCount() {
		return gracefulLoginCount;
	}
	public void setGracefulLoginCount(BigDecimal gracefulLoginCount) {
		this.gracefulLoginCount = gracefulLoginCount;
	}
	public String getGracefulLoginFlag() {
		return gracefulLoginFlag;
	}
	public void setGracefulLoginFlag(String gracefulLoginFlag) {
		this.gracefulLoginFlag = gracefulLoginFlag;
	}
	public BigDecimal getInvalidPasswordCount() {
		return invalidPasswordCount;
	}
	public void setInvalidPasswordCount(BigDecimal invalidPasswordCount) {
		this.invalidPasswordCount = invalidPasswordCount;
	}
	public Timestamp getLastPasswordAdmenDate() {
		return lastPasswordAdmenDate;
	}
	public void setLastPasswordAdmenDate(Timestamp lastPasswordAdmenDate) {
		this.lastPasswordAdmenDate = lastPasswordAdmenDate;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Timestamp getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Collection getRtqInfoCol() {
		return rtqInfoCol;
	}
	public void setRtqInfoCol(Collection rtqInfoCol) {
		this.rtqInfoCol = rtqInfoCol;
	}
	public String getSmsProvider() {
		return smsProvider;
	}
	public void setSmsProvider(String smsProvider) {
		this.smsProvider = smsProvider;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrackingFlag() {
		return trackingFlag;
	}
	public void setTrackingFlag(String trackingFlag) {
		this.trackingFlag = trackingFlag;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getAllowTradeStatusFlag() {
		return allowTradeStatusFlag;
	}
	public void setAllowTradeStatusFlag(String allowTradeStatusFlag) {
		this.allowTradeStatusFlag = allowTradeStatusFlag;
	}
	public String getChannelStatus() {
		return channelStatus;
	}
	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}
	public String getConditionalOrderTermsAndConditions() {
		return conditionalOrderTermsAndConditions;
	}
	public void setConditionalOrderTermsAndConditions(
			String conditionalOrderTermsAndConditions) {
		this.conditionalOrderTermsAndConditions = conditionalOrderTermsAndConditions;
	}
	public String getFirstLoginFlag() {
		return firstLoginFlag;
	}
	public void setFirstLoginFlag(String firstLoginFlag) {
		this.firstLoginFlag = firstLoginFlag;
	}
	public BigDecimal getForceChangePasswordDay() {
		return forceChangePasswordDay;
	}
	public void setForceChangePasswordDay(BigDecimal forceChangePasswordDay) {
		this.forceChangePasswordDay = forceChangePasswordDay;
	}
	public String getGraceLoginIndicator() {
		return graceLoginIndicator;
	}
	public void setGraceLoginIndicator(String graceLoginIndicator) {
		this.graceLoginIndicator = graceLoginIndicator;
	}
	public String getIsRTQInfoAvailable() {
		return isRTQInfoAvailable;
	}
	public void setIsRTQInfoAvailable(String isRTQInfoAvailable) {
		this.isRTQInfoAvailable = isRTQInfoAvailable;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getIsValidDocId() {
		return isValidDocId;
	}
	public void setIsValidDocId(String isValidDocId) {
		this.isValidDocId = isValidDocId;
	}
	public String getIsValidECert() {
		return isValidECert;
	}
	public void setIsValidECert(String isValidECert) {
		this.isValidECert = isValidECert;
	}
	public BigDecimal getMaxGraceLoginCount() {
		return maxGraceLoginCount;
	}
	public void setMaxGraceLoginCount(BigDecimal maxGraceLoginCount) {
		this.maxGraceLoginCount = maxGraceLoginCount;
	}
	public BigDecimal getMaxInvalidPasswordCount() {
		return maxInvalidPasswordCount;
	}
	public void setMaxInvalidPasswordCount(BigDecimal maxInvalidPasswordCount) {
		this.maxInvalidPasswordCount = maxInvalidPasswordCount;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public String getNewSubCode() {
		return newSubCode;
	}
	public void setNewSubCode(String newSubCode) {
		this.newSubCode = newSubCode;
	}
	public String getShkSubscriptionFlag() {
		return shkSubscriptionFlag;
	}
	public void setShkSubscriptionFlag(String shkSubscriptionFlag) {
		this.shkSubscriptionFlag = shkSubscriptionFlag;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	public String getTradingAccount() {
		return tradingAccount;
	}
	public void setTradingAccount(String tradingAccount) {
		this.tradingAccount = tradingAccount;
	}

}
