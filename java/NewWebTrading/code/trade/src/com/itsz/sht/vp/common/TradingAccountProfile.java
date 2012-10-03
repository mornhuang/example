package com.itsz.sht.vp.common;

import java.io.Serializable;

public class TradingAccountProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String DEFAULT_CLIENTCLASSCODE_NORMAL = "NORMAL";
	private String accountID;
	private String status;
	private String category;
	private boolean displayFlag;
	private String icbcAcName;
	private String icbcAcNO;
	private boolean isCIESFlag;
	private boolean accountSelect; // Account displayed for selection
	private String clientClassCode;

	public TradingAccountProfile() {
	}

	public boolean isCIESFlag() {
		return isCIESFlag;
	}

	public void setCIESFlag(boolean isCIESFlag) {
		this.isCIESFlag = isCIESFlag;
	}

	public boolean haveIcbcProfile() {
		if (icbcAcName != null && icbcAcNO != null
				&& !icbcAcName.trim().equals("") && !icbcAcNO.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the status
	 */
	public String isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the displayFlag
	 */
	public boolean isDisplayFlag() {
		return displayFlag;
	}

	/**
	 * @param displayFlag
	 *            the displayFlag to set
	 */
	public void setDisplayFlag(boolean displayFlag) {
		this.displayFlag = displayFlag;
	}

	/**
	 * @return the idbcAcName
	 */
	public String getIcbcAcName() {
		return icbcAcName;
	}

	/**
	 * @param idbcAcName
	 *            the idbcAcName to set
	 */
	public void setIcbcAcName(String icbcAcName) {
		this.icbcAcName = icbcAcName;
		// this.icbcAcName = "icbcAcName";
	}

	/**
	 * @return the idbcAcNO
	 */
	public String getIcbcAcNO() {
		return icbcAcNO;
	}

	/**
	 * @param idbcAcNO
	 *            the idbcAcNO to set
	 */
	public void setIcbcAcNO(String icbcAcNO) {
		this.icbcAcNO = icbcAcNO;
		// this.icbcAcNO = "icbcAcNO";
	}

	public void setAccountSelect(boolean accountSelect) {
		this.accountSelect = accountSelect;
	}

	public boolean isAccountSelect() {
		return accountSelect;
	}

	public String getClientClassCode() {
		return clientClassCode;
	}

	public void setClientClassCode(String clientClassCode) {
		this.clientClassCode = clientClassCode;
	}

}
