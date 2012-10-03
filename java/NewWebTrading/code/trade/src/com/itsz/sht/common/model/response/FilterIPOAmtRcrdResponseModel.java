package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;

/**
 * $Id: FilterIPOAmtRcrdResponseModel.java,v 1.2 2010/12/08 06:34:42 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOAmtRcrdResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-8
 */
public class FilterIPOAmtRcrdResponseModel extends AbstractResponseModel {
	private IPORequest ipoRequest;
	private IPORecord ipoRecord;

	public IPORecord getIpoRecord() {
		return ipoRecord;
	}

	public void setIpoRecord(IPORecord ipoRecord) {
		this.ipoRecord = ipoRecord;
	}

	public IPORequest getIpoRequest() {
		return ipoRequest;
	}

	public void setIpoRequest(IPORequest ipoRequest) {
		this.ipoRequest = ipoRequest;
	}
}
