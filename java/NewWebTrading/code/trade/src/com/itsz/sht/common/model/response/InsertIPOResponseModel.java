package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;

/**
 * $Id: InsertIPOResponseModel.java,v 1.3 2010/12/17 06:18:40 kyzou Exp $
 * @Project:NewWebTrading
 * @File:InsertIPOResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class InsertIPOResponseModel extends AbstractResponseModel {
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
