package com.itsz.sht.common.model.response.placeorder;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.AbstractRequestModel;
import com.taifook.mcs.core.beans.msg.PreOrderPlacingResponse;

/**
 * $Id: PrePlaceOrderResponseModel.java,v 1.2 2011/03/09 06:45:18 pbxie Exp $
 * @Project:portal.head
 * @File:PrePlaceOrderResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-26
 */
public class PrePlaceOrderResponseModel extends AbstractRequestModel {

	private String returnCode;
	private String accountID;//账户id
	private String instrCode;//产品(股票)代码
	private String triggerRightAway;//是否立即触发
	private String isOverMaxLotSize = Consts.Global.Flag.NEGATIVE;//whether over max lot size or not
	private PreOrderPlacingResponse preOrderPlacingResponse;
	
	/**
	 * @return the preOrderPlacingResponse
	 */
	public PreOrderPlacingResponse getPreOrderPlacingResponse() {
		return preOrderPlacingResponse;
	}
	/**
	 * @param preOrderPlacingResponse the preOrderPlacingResponse to set
	 */
	public void setPreOrderPlacingResponse(
			PreOrderPlacingResponse preOrderPlacingResponse) {
		this.preOrderPlacingResponse = preOrderPlacingResponse;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getTriggerRightAway() {
		return triggerRightAway;
	}
	public void setTriggerRightAway(String triggerRightAway) {
		this.triggerRightAway = triggerRightAway;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getIsOverMaxLotSize() {
		return isOverMaxLotSize;
	}
	public void setIsOverMaxLotSize(String isOverMaxLotSize) {
		this.isOverMaxLotSize = isOverMaxLotSize;
	}

}
