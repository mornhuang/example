package com.itsz.sht.struts.form;

/**
 * $Id: EnquireShortRTQForm.java,v 1.1 2010/12/01 06:33:15 kyzou Exp $
 * @Project:portal
 * @File:EnquireShortRTQForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQForm extends ITSZForm {
	private static final long serialVersionUID = 241893578022434542L;
	private String instrCode;
	private String quoteType;
	
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
}
