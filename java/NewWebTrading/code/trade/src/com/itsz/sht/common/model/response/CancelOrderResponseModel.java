package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: CancelOrderResponseModel.java,v 1.1 2010/11/09 03:57:28 kyzou Exp $
 * @Project:portal
 * @File:CancelOrderResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-11-13
 */
public class CancelOrderResponseModel extends AbstractResponseModel {
	private String OrderStatus;

	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
}
