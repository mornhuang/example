package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class TradeInfo extends BaseDTO {
    private String accountId;
    private Long mcsOrderId;
    private Long tradeID;
    private String tradeSide;
    private String marketCode;
    private String instrCode;
    private String instrName;
    private BigDecimal executedPrice;
    private BigDecimal executedQty;
    private String businessDate;
    private String channelType;
    private BigDecimal amount;
    private String remark;
    private String status;
    private String currency;
    
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getMcsOrderId() {
		return mcsOrderId;
	}
	public void setMcsOrderId(Long mcsOrderId) {
		this.mcsOrderId = mcsOrderId;
	}
	public Long getTradeID() {
		return tradeID;
	}
	public void setTradeID(Long tradeID) {
		this.tradeID = tradeID;
	}
	public String getTradeSide() {
		return tradeSide;
	}
	public void setTradeSide(String tradeSide) {
		this.tradeSide = tradeSide;
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
	public String getInstrName() {
		return instrName;
	}
	public void setInstrName(String instrName) {
		this.instrName = instrName;
	}
	public BigDecimal getExecutedPrice() {
		return executedPrice;
	}
	public void setExecutedPrice(BigDecimal executedPrice) {
		this.executedPrice = executedPrice;
	}
	public BigDecimal getExecutedQty() {
		return executedQty;
	}
	public void setExecutedQty(BigDecimal executedQty) {
		this.executedQty = executedQty;
	}
	public String getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
