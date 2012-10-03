package com.itsz.sht.common.model.common;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: OrderModel.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal.head
 * @File:OrderModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderModel extends AbstractRequestModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3150761678939183487L;
	private String mcsOrderId;
	private String mtsOrderId;
	private String orderStatus;
	
	public String getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(String mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public String getMtsOrderId() {
		return mtsOrderId;
	}
	public void setMtsOrderId(String mtsOrderId) {
		this.mtsOrderId = mtsOrderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
