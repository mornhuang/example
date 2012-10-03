package com.itsz.sht.common.model.response;

import java.util.List;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: FilterEIPOListResponseModel.java,v 1.2 2011/05/05 02:57:11 lpchen Exp $
 * @Project:NewWebTrading
 * @File:FilterIPOListResponseModel.java
 * @Description:
 * @Author:Arthur Chen
 * @Date:2011-4-16
 */
public class FilterEIPOListResponseModel extends AbstractResponseModel {
	private List result;

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
}
