package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.dto.ipo.IPORecord;

/**
 * $Id: FilterIPOResponseModel.java,v 1.2 2010/12/01 11:21:49 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class FilterIPOResponseModel extends AbstractResponseModel{
	private IPORecord result;

	public IPORecord getResult() {
		return result;
	}

	public void setResult(IPORecord result) {
		this.result = result;
	}
}
