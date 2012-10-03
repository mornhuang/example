package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.ModifyOrderResponse;

/**
 * $Id: ModifyOrderResponseModel.java,v 1.2 2011/03/15 03:02:43 xli Exp $
 * @Project:portal.head
 * @File:ModifyOrderResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ModifyOrderResponseModel extends AbstractResponseModel {

	private String orderStatus;
    private String errorStatus;
    private ModifyOrderResponse modifyOrderRes;
    
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public ModifyOrderResponse getModifyOrderRes() {
		return modifyOrderRes;
	}
	public void setModifyOrderRes(ModifyOrderResponse modifyOrderRes) {
		this.modifyOrderRes = modifyOrderRes;
	}
}
