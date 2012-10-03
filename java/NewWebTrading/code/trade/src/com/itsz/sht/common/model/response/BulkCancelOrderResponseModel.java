package com.itsz.sht.common.model.response;

import java.util.Collection;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.BulkCancelOrderResponse;

/**
 * $Id: BulkCancelOrderResponseModel.java,v 1.2 2011/03/14 08:26:53 pbxie Exp $
 * @Project:portal.head
 * @File:CancelOrderResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class BulkCancelOrderResponseModel extends AbstractResponseModel {

	private Collection orderIdInfos; //其实是BulkCancelOrderInfo对象集
	private String listSize;
	private BulkCancelOrderResponse bulkCancelOrderResponse;
	

	/**
	 * @return the bulkCancelOrderResponse
	 */
	public BulkCancelOrderResponse getBulkCancelOrderResponse() {
		return bulkCancelOrderResponse;
	}
	/**
	 * @param bulkCancelOrderResponse the bulkCancelOrderResponse to set
	 */
	public void setBulkCancelOrderResponse(
			BulkCancelOrderResponse bulkCancelOrderResponse) {
		this.bulkCancelOrderResponse = bulkCancelOrderResponse;
	}
	public Collection getOrderIdInfos() {
		return orderIdInfos;
	}
	public void setOrderIdInfos(Collection orderIdInfos) {
		this.orderIdInfos = orderIdInfos;
	}
	public String getListSize() {
		return listSize;
	}
	public void setListSize(String listSize) {
		this.listSize = listSize;
	}
}
