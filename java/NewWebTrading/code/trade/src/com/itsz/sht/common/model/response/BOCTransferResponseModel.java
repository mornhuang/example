package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.BOCTransferResponse;

/**
 * $Id: BOCTransferResponseModel.java,v 1.3 2010/12/31 08:07:52 xlliu Exp $
 * @Project:NewWebTrading
 * @File:BOCTransferResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class BOCTransferResponseModel extends AbstractResponseModel {
	private BOCTransferResponse bocTransferResponse;
	private String mch_custID;
    private String mch_payAmt;
    private String mch_merchID;
    private String mch_locale;
    private String mch_timeStamp;
    private String mch_returnURL;
    private String itsHandlerURL;
    public String getItsHandlerURL() {
		return itsHandlerURL;
	}

	public void setItsHandlerURL(String itsHandlerURL) {
		this.itsHandlerURL = itsHandlerURL;
	}

	public String getMch_custID() {
		return mch_custID;
	}

	public void setMch_custID(String mchCustID) {
		mch_custID = mchCustID;
	}

	public String getMch_payAmt() {
		return mch_payAmt;
	}

	public void setMch_payAmt(String mchPayAmt) {
		mch_payAmt = mchPayAmt;
	}

	public String getMch_merchID() {
		return mch_merchID;
	}

	public void setMch_merchID(String mchMerchID) {
		mch_merchID = mchMerchID;
	}

	public String getMch_locale() {
		return mch_locale;
	}

	public void setMch_locale(String mchLocale) {
		mch_locale = mchLocale;
	}

	public String getMch_timeStamp() {
		return mch_timeStamp;
	}

	public void setMch_timeStamp(String mchTimeStamp) {
		mch_timeStamp = mchTimeStamp;
	}

	public String getMch_returnURL() {
		return mch_returnURL;
	}

	public void setMch_returnURL(String mchReturnURL) {
		mch_returnURL = mchReturnURL;
	}

	public BOCTransferResponse getBocTransferResponse() {
		return bocTransferResponse;
	}

	public void setBocTransferResponse(BOCTransferResponse bocTransferResponse) {
		this.bocTransferResponse = bocTransferResponse;
	}
}
