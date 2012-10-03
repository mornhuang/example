package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class ShareHoldingInfo extends BaseDTO {
    private String instrCode;
    private String instrName;
    private String currency;
    private BigDecimal maxSellableQuantity;
    private BigDecimal previousClosingPrice;
    private BigDecimal previousClosingValue;

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getInstrName() {
		return instrName;
	}
	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}
	public BigDecimal getMaxSellableQuantity() {
		return maxSellableQuantity;
	}
	public void setMaxSellableQuantity(BigDecimal maxSellableQuantity) {
		this.maxSellableQuantity = maxSellableQuantity;
	}
	public BigDecimal getPreviousClosingPrice() {
		return previousClosingPrice;
	}
	public void setPreviousClosingPrice(BigDecimal previousClosingPrice) {
		this.previousClosingPrice = previousClosingPrice;
	}
	public BigDecimal getPreviousClosingValue() {
		return previousClosingValue;
	}
	public void setPreviousClosingValue(BigDecimal previousClosingValue) {
		this.previousClosingValue = previousClosingValue;
	}
}
