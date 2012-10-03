package com.itsz.sht.common.model.response.placeorder;

import java.math.BigDecimal;

/**
 * $Id: ConfirmedOrderInfo.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:ConfirmedOrderInfo.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-6-5
 */
public class ConfirmedOrderInfo {
	
private  String OrderStatus;
private  Long MCSOrderID;
private  BigDecimal OrderQuantity;


public Long getMCSOrderID() {
	return MCSOrderID;
}
public void setMCSOrderID(Long orderID) {
	MCSOrderID = orderID;
}
public BigDecimal getOrderQuantity() {
	return OrderQuantity;
}
public void setOrderQuantity(BigDecimal orderQuantity) {
	OrderQuantity = orderQuantity;
}
public String getOrderStatus() {
	return OrderStatus;
}
public void setOrderStatus(String orderStatus) {
	OrderStatus = orderStatus;
}
	
}
