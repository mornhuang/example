package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.PPSTransferDetailResponse;

/**
 * $Id: PPSEnquiryResponseModel.java,v 1.3 2011/01/04 08:31:14 xlliu Exp $
 * @Project:NewWebTrading
 * @File:PPSEnquiryResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-6
 */
public class PPSEnquiryResponseModel extends AbstractResponseModel {
	private PPSTransferDetailResponse ppsTransferDetailResponse;
	private String lang;
	private String locale;
	private String opCode;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public PPSTransferDetailResponse getPpsTransferDetailResponse() {
		return ppsTransferDetailResponse;
	}

	public void setPpsTransferDetailResponse(
			PPSTransferDetailResponse ppsTransferDetailResponse) {
		this.ppsTransferDetailResponse = ppsTransferDetailResponse;
	}
}
