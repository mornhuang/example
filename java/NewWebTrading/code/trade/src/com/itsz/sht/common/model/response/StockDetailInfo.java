package com.itsz.sht.common.model.response;

/**
 * $Id: StockDetailInfo.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal
 * @File:StockDetailInfo.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-17
 */
public class StockDetailInfo {
    private String instrCode;
    private String availableQuantity;
    private String discountRate;
    private String prevClosingPrice;
    private String previousClosingValue;
    private String discountedValue;  
    private String maxSellableQuantity;    
    
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getPrevClosingPrice() {
		return prevClosingPrice;
	}
	public void setPrevClosingPrice(String prevClosingPrice) {
		this.prevClosingPrice = prevClosingPrice;
	}
	public String getPreviousClosingValue() {
		return previousClosingValue;
	}
	public void setPreviousClosingValue(String previousClosingValue) {
		this.previousClosingValue = previousClosingValue;
	}
	public String getDiscountedValue() {
		return discountedValue;
	}
	public void setDiscountedValue(String discountedValue) {
		this.discountedValue = discountedValue;
	}
	public String getMaxSellableQuantity() {
		return maxSellableQuantity;
	}
	public void setMaxSellableQuantity(String maxSellableQuantity) {
		this.maxSellableQuantity = maxSellableQuantity;
	}
}
