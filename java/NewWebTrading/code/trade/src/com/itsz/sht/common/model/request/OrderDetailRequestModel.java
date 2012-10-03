package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: OrderDetailRequestModel.java,v 1.2 2011/03/23 05:47:09 pbxie Exp $
 * @Project:portal.head
 * @File:OrderDetailRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderDetailRequestModel extends AbstractRequestModel {

	private String accountId;
	private Long mcsOrderId;
	private Long orderId;//mtss order id
	private String hasHisories;//是否需要交易记录
	private String transactionProtection;
	private String supportOverNightFlag;
	
	/**
	 * @return the supportOverNightFlag
	 */
	public String getSupportOverNightFlag() {
		return supportOverNightFlag;
	}
	/**
	 * @param supportOverNightFlag the supportOverNightFlag to set
	 */
	public void setSupportOverNightFlag(String supportOverNightFlag) {
		this.supportOverNightFlag = supportOverNightFlag;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(Long mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getHasHisories() {
		return hasHisories;
	}
	public void setHasHisories(String hasHisories) {
		this.hasHisories = hasHisories;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
}
