package cn.itsz.newsim.dto.response;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.CalOrderFeeResponseEntity;

public class OrderFeeResponse extends ResultMessage {
	private CalOrderFeeResponseEntity calOrderFeeResponse;
	private String orderSide;
	private String conditionType;
	private String orderType;
	private BigDecimal orderPrice;
	private BigDecimal orderQuantity;
	private BigDecimal triggerPrice;
	private String instrCode;
	private String currency;
	
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public CalOrderFeeResponseEntity getCalOrderFeeResponse() {
		return calOrderFeeResponse;
	}

	public void setCalOrderFeeResponse(CalOrderFeeResponseEntity calOrderFeeResponse) {
		this.calOrderFeeResponse = calOrderFeeResponse;
	}

	public String getOrderSide() {
		return orderSide;
	}

	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public BigDecimal getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(BigDecimal triggerPrice) {
		this.triggerPrice = triggerPrice;
	}

	public String getInstrCode() {
		return instrCode;
	}

	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
}
