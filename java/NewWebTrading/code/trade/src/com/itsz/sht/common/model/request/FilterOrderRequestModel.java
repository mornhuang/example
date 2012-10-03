package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: FilterOrderRequestModel.java,v 1.4 2011/03/23 05:47:09 pbxie Exp $
 * @Project:portal.head
 * @File:ListOrderRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class FilterOrderRequestModel extends AbstractRequestModel{
	
	private String accountId;
	private String orderStateType;//order state type,such as:
	private String marketCode;
	
	private String pageSize;
	private String pageNo;
	private int fromIdx;
	private int toIdx;
	private String instrCode;
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
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public int getFromIdx() {
		return fromIdx;
	}
	public void setFromIdx(int fromIdx) {
		this.fromIdx = fromIdx;
	}
	public int getToIdx() {
		return toIdx;
	}
	public void setToIdx(int toIdx) {
		this.toIdx = toIdx;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOrderStateType() {
		return orderStateType;
	}
	public void setOrderStateType(String orderStateType) {
		this.orderStateType = orderStateType;
	}
}
