package cn.itsz.newsim.view.form;

import cn.itsz.newsim.common.Constants;

/**
 * $Id: OrderFeeForm.java,v 1.1 2011/03/04 09:47:34 zxfan Exp $
 * @Project:portal.head
 * @File:OrderFeeForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class OrderFeeForm extends BaseForm {

	private static final long serialVersionUID = 9111880044205816639L;
	private String accountId;
	private String marketCode;
	private String instrCode;
	private String orderQuantity;
	private String orderPrice;
	private String orderSide;
	private String orderType;
	private String conditionType;
	private String allOrNothing = Constants.Flag.NEGATIVE;
	private String triggerPrice;
	private String password;
	private String token;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAllOrNothing() {
		return allOrNothing;
	}
	public void setAllOrNothing(String allOrNothing) {
		this.allOrNothing = allOrNothing;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public String getTriggerPrice() {
		return triggerPrice;
	}
	public void setTriggerPrice(String triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
