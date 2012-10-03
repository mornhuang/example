package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class OrderDTO extends BaseDTO {
	private String orderId;
	private String accountId;
	private String marketCode;
	private String instrCode;
	private String instrName;
	private String orderSide;
	private String orderState;
	private String orderRemark;
	private BigDecimal initialQuantity;
	private BigDecimal filledQty;
	private BigDecimal outstandingQuantity;
	private BigDecimal orderPrice;
	private BigDecimal tradeAvgPrice;
	private BigDecimal changedQty;
	private String orderType;
	private String loginId;
	private String transactionAmount;
	private BigDecimal holdAmount;
	private String rejectMessage;
	private String channelType;
	private String currency;
	
	public OrderDTO() {
	}
	
	public OrderDTO(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDTO other = (OrderDTO) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

	public BigDecimal getHoldAmount() {
		return holdAmount;
	}

	public void setHoldAmount(BigDecimal holdAmount) {
		this.holdAmount = holdAmount;
	}

	public BigDecimal getChangedQty() {
		return changedQty;
	}

	public void setChangedQty(BigDecimal changedQty) {
		this.changedQty = changedQty;
	}

	public String getInstrName() {
		return instrName;
	}

	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}

	public BigDecimal getTradeAvgPrice() {
		return tradeAvgPrice;
	}
	public void setTradeAvgPrice(BigDecimal tradeAvgPrice) {
		this.tradeAvgPrice = tradeAvgPrice;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getOrderSide() {
		return orderSide;
	}
	public void setOrderSide(String orderSide) {
		this.orderSide = orderSide;
	}
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public BigDecimal getInitialQuantity() {
		return initialQuantity;
	}
	public void setInitialQuantity(BigDecimal initialQuantity) {
		this.initialQuantity = initialQuantity;
	}
	public BigDecimal getFilledQty() {
		return filledQty;
	}
	public void setFilledQty(BigDecimal filledQty) {
		this.filledQty = filledQty;
	}
	public BigDecimal getOutstandingQuantity() {
		return outstandingQuantity;
	}
	public void setOutstandingQuantity(BigDecimal outstandingQuantity) {
		this.outstandingQuantity = outstandingQuantity;
	}
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getRejectMessage() {
		return rejectMessage;
	}
	public void setRejectMessage(String rejectMessage) {
		this.rejectMessage = rejectMessage;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
