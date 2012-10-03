package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;

/**
 * $Id: FilterIPOQtyAmtRcrdResponseModel.java,v 1.3 2010/12/17 11:18:26 xlliu Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOQtyAmtRcrdResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class FilterIPOQtyAmtRcrdResponseModel extends AbstractResponseModel{
	private List result;
	private IPORecord ipoRecord;

	public IPORecord getIpoRecord() {
		return ipoRecord;
	}

	public void setIpoRecord(IPORecord ipoRecord) {
		this.ipoRecord = ipoRecord;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
