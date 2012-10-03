/**
 * <p>Title: 3G Portal</p>
 * <p>Description: 3G Portal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.user;

import java.io.Serializable;

public class AcEnquiryInfo implements Serializable {	
    private String accountId;   
    private String accountType;
    private String bankCode;
    private String bankAccountCode;
    private String bankShortName;
    private String bankName;
    private String dailyWithdrawableLimit;
    private String dailyUsedWithdrawableLimit;
    private String allowFundTranferFlag; 
    private String isAccountValid;
    private String accountStatus;
   
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return Returns the accountId.
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId The accountId to set.
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return Returns the accountStatus.
	 */
	public String getAccountStatus() {
		return accountStatus;
	}
	/**
	 * @param accountStatus The accountStatus to set.
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	/**
	 * @return Returns the accountType.
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType The accountType to set.
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return Returns the allowFundTranferFlag.
	 */
	public String getAllowFundTranferFlag() {
		return allowFundTranferFlag;
	}
	/**
	 * @param allowFundTranferFlag The allowFundTranferFlag to set.
	 */
	public void setAllowFundTranferFlag(String allowFundTranferFlag) {
		this.allowFundTranferFlag = allowFundTranferFlag;
	}
	/**
	 * @return Returns the bankAccountCode.
	 */
	public String getBankAccountCode() {
		return bankAccountCode;
	}
	/**
	 * @param bankAccountCode The bankAccountCode to set.
	 */
	public void setBankAccountCode(String bankAccountCode) {
		this.bankAccountCode = bankAccountCode;
	}
	/**
	 * @return Returns the bankCode.
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * @param bankCode The bankCode to set.
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * @return Returns the bankShortName.
	 */
	public String getBankShortName() {
		return bankShortName;
	}
	/**
	 * @param bankShortName The bankShortName to set.
	 */
	public void setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
	}
	
	/**
	 * @return Returns the isAccountValid.
	 */
	public String getIsAccountValid() {
		return isAccountValid;
	}
	/**
	 * @param isAccountValid The isAccountValid to set.
	 */
	public void setIsAccountValid(String isAccountValid) {
		this.isAccountValid = isAccountValid;
	}	
    /**
     * @return Returns the dailyUsedWithdrawableLimit.
     */
    public String getDailyUsedWithdrawableLimit() {
        return dailyUsedWithdrawableLimit;
    }
    /**
     * @param dailyUsedWithdrawableLimit The dailyUsedWithdrawableLimit to set.
     */
    public void setDailyUsedWithdrawableLimit(String dailyUsedWithdrawableLimit) {
        this.dailyUsedWithdrawableLimit = dailyUsedWithdrawableLimit;
    }
    /**
     * @return Returns the dailyWithdrawableLimit.
     */
    public String getDailyWithdrawableLimit() {
        return dailyWithdrawableLimit;
    }
    /**
     * @param dailyWithdrawableLimit The dailyWithdrawableLimit to set.
     */
    public void setDailyWithdrawableLimit(String dailyWithdrawableLimit) {
        this.dailyWithdrawableLimit = dailyWithdrawableLimit;
    }
}
