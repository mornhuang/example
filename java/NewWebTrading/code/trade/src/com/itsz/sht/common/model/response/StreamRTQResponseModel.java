package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: StreamRTQResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:StreamRTQResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class StreamRTQResponseModel extends AbstractResponseModel{

	private boolean isEsDown;
	private boolean isRtqAvailable;
    private String quoteProvider;
	private String validQuoteProvider;
	private String validQuoteURL;
	private String validLoginID;
	private String validPassword;
	
	public String getValidLoginID() {
		return validLoginID;
	}
	public void setValidLoginID(String validLoginID) {
		this.validLoginID = validLoginID;
	}
	public String getValidPassword() {
		return validPassword;
	}
	public void setValidPassword(String validPassword) {
		this.validPassword = validPassword;
	}
	public String getValidQuoteProvider() {
		return validQuoteProvider;
	}
	public void setValidQuoteProvider(String validQuoteProvider) {
		this.validQuoteProvider = validQuoteProvider;
	}
	public String getValidQuoteURL() {
		return validQuoteURL;
	}
	public void setValidQuoteURL(String validQuoteURL) {
		this.validQuoteURL = validQuoteURL;
	}
	public boolean isEsDown() {
		return isEsDown;
	}
	public void setEsDown(boolean isEsDown) {
		this.isEsDown = isEsDown;
	}
	public boolean isRtqAvailable() {
		return isRtqAvailable;
	}
	public void setRtqAvailable(boolean isRtqAvailable) {
		this.isRtqAvailable = isRtqAvailable;
	}
    public String getQuoteProvider() {
        return quoteProvider;
    }
    public void setQuoteProvider(String quoteProvider) {
        this.quoteProvider = quoteProvider;
    }
	
//	private Object isRTQAvailable;
//	private Object subSvcId;
//	private Integer accessUnits;

}
