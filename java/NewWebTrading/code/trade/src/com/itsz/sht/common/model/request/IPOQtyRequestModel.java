package com.itsz.sht.common.model.request;

import java.math.BigDecimal;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: IPOQtyRequestModel.java,v 1.1 2010/12/08 04:31:44 kyzou Exp $
 * @Project:NewWebTrading
 * @File:IPOQtyRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-8
 */
public class IPOQtyRequestModel extends AbstractRequestModel {
    private long ipoMasterId;
    private int  shareQty;
    private BigDecimal amount;
	public long getIpoMasterId() {
		return ipoMasterId;
	}
	public void setIpoMasterId(long ipoMasterId) {
		this.ipoMasterId = ipoMasterId;
	}
	public int getShareQty() {
		return shareQty;
	}
	public void setShareQty(int shareQty) {
		this.shareQty = shareQty;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
