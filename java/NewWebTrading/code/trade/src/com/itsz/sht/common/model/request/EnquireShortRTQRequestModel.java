package com.itsz.sht.common.model.request;

import java.util.Vector;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: EnquireShortRTQRequestModel.java,v 1.1 2010/11/09 03:57:27 kyzou Exp $
 * @Project:portal
 * @File:EnquireShortRTQRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQRequestModel extends AbstractRequestModel{
	private Vector instrCode;
	private String quoteType;
	private String custCode;
	
	public Vector getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(Vector instrCode) {
		this.instrCode = instrCode;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

}
