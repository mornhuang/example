package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: FilterIPOListResponseModel.java,v 1.4 2010/12/06 04:04:55 kyzou Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOListResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class FilterIPOListResponseModel extends AbstractResponseModel {
	private List result;

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
