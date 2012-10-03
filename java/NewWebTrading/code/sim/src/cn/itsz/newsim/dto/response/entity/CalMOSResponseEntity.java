package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class CalMOSResponseEntity extends BaseDTO {
    private BigDecimal buyOrderControlLimit;
    private BigDecimal totalPurchasingPower;
    private BigDecimal zeroPriceVolatility;
    private BigDecimal maxSellableQuantity;
    private BigDecimal zeroPriceOrderPrice;
    private String tradingSessionStatus;
    private String channelAllowOvernightOrder;
	public BigDecimal getBuyOrderControlLimit() {
		return buyOrderControlLimit;
	}
	public void setBuyOrderControlLimit(BigDecimal buyOrderControlLimit) {
		this.buyOrderControlLimit = buyOrderControlLimit;
	}
	public BigDecimal getTotalPurchasingPower() {
		return totalPurchasingPower;
	}
	public void setTotalPurchasingPower(BigDecimal totalPurchasingPower) {
		this.totalPurchasingPower = totalPurchasingPower;
	}
	public BigDecimal getZeroPriceVolatility() {
		return zeroPriceVolatility;
	}
	public void setZeroPriceVolatility(BigDecimal zeroPriceVolatility) {
		this.zeroPriceVolatility = zeroPriceVolatility;
	}
	public BigDecimal getMaxSellableQuantity() {
		return maxSellableQuantity;
	}
	public void setMaxSellableQuantity(BigDecimal maxSellableQuantity) {
		this.maxSellableQuantity = maxSellableQuantity;
	}
	public BigDecimal getZeroPriceOrderPrice() {
		return zeroPriceOrderPrice;
	}
	public void setZeroPriceOrderPrice(BigDecimal zeroPriceOrderPrice) {
		this.zeroPriceOrderPrice = zeroPriceOrderPrice;
	}
	public String getTradingSessionStatus() {
		return tradingSessionStatus;
	}
	public void setTradingSessionStatus(String tradingSessionStatus) {
		this.tradingSessionStatus = tradingSessionStatus;
	}
	public String getChannelAllowOvernightOrder() {
		return channelAllowOvernightOrder;
	}
	public void setChannelAllowOvernightOrder(String channelAllowOvernightOrder) {
		this.channelAllowOvernightOrder = channelAllowOvernightOrder;
	}
}
