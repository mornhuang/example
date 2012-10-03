package cn.itsz.newsim.dto.request;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class ModfiyOrderRequest extends BaseDTO {
	private String loginId;
	private String orderId;
	private BigDecimal newOrderPrice;
	private BigDecimal newOrderQty;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getNewOrderPrice() {
		return newOrderPrice;
	}
	public void setNewOrderPrice(BigDecimal newOrderPrice) {
		this.newOrderPrice = newOrderPrice;
	}
	public BigDecimal getNewOrderQty() {
		return newOrderQty;
	}
	public void setNewOrderQty(BigDecimal newOrderQty) {
		this.newOrderQty = newOrderQty;
	}
}
