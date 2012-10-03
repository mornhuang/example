package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: CashDetailRequestModel.java,v 1.1 2011/03/11 10:24:39 pbxie Exp $
 * @Project:portal.head
 * @File:OrderDetailRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class CashDetailRequestModel extends AbstractRequestModel {

	private String accountId;
	private String marketCode;
	

	/**
	 * @return the marketCode
	 */
	public String getMarketCode() {
		return marketCode;
	}

	/**
	 * @param marketCode the marketCode to set
	 */
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
