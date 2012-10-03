package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: MosRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal.head
 * @File:MosRequestModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-26
 */
public class MosRequestModel extends AbstractRequestModel {

	private String messageId;
	private String messageSEQNum;
	private String accountId;
	private String marketCode;
	private String instrCode;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageSEQNum() {
		return messageSEQNum;
	}
	public void setMessageSEQNum(String messageSEQNum) {
		this.messageSEQNum = messageSEQNum;
	}
	
	
}
