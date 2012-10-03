package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;

/**
 * $Id: RemoveIPOResponseModel.java,v 1.2 2010/12/04 09:18:14 kyzou Exp $
 * @Project:NewWebTrading
 * @File:RemoveIPOResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class RemoveIPOResponseModel extends AbstractResponseModel {
	private IPORecord ipoRecord;
	private IPORequest ipoRequest;

	public IPORequest getIpoRequest() {
		return ipoRequest;
	}

	public void setIpoRequest(IPORequest ipoRequest) {
		this.ipoRequest = ipoRequest;
	}

	public IPORecord getIpoRecord() {
		return ipoRecord;
	}

	public void setIpoRecord(IPORecord ipoRecord) {
		this.ipoRecord = ipoRecord;
	}
}
