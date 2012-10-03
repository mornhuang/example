package com.itsz.sht.common.model.response;

import java.util.Collection;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: OrderHistoryResponseModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:OrderHistoryResponseModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderHistoryResponseModel extends AbstractResponseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3990814764044323912L;
	public Collection tradeHistory;
	public Collection order;
	
	public Collection getOrder() {
		return order;
	}
	public void setOrder(Collection order) {
		this.order = order;
	}
	public Collection getTradeHistory() {
		return tradeHistory;
	}
	public void setTradeHistory(Collection tradeHistory) {
		this.tradeHistory = tradeHistory;
	}
}
