package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.dto.ipo.IPORequest;
import com.itsz.sht.dto.ipo.IPOResult;

/**
 * $Id: QueryCodeResponseModel.java,v 1.2 2010/12/04 10:02:38 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOResultResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-4
 */
public class QueryCodeResponseModel extends AbstractResponseModel {
	private IPOResult ipoResult;
	private IPORecord ipoRecord;
	private IPORequest ipoRequest;
	public IPORequest getIpoRequest() {
		return ipoRequest;
	}
	public void setIpoRequest(IPORequest ipoRequest) {
		this.ipoRequest = ipoRequest;
	}
	public IPOResult getIpoResult() {
		return ipoResult;
	}
	public void setIpoResult(IPOResult ipoResult) {
		this.ipoResult = ipoResult;
	}
	public IPORecord getIpoRecord() {
		return ipoRecord;
	}
	public void setIpoRecord(IPORecord ipoRecord) {
		this.ipoRecord = ipoRecord;
	}

}
