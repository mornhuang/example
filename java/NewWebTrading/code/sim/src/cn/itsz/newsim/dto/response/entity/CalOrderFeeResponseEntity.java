package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class CalOrderFeeResponseEntity extends BaseDTO {
    private BigDecimal cashCanBeUsed;
    private BigDecimal transactionAmount;
    private BigDecimal netAmount;
    private BigDecimal tradingFee;
    private BigDecimal levyCharge;
    private BigDecimal ccassCharge;
    private BigDecimal commonCharges;
    private BigDecimal stampCharge;
	public BigDecimal getCashCanBeUsed() {
		return cashCanBeUsed;
	}
	public void setCashCanBeUsed(BigDecimal cashCanBeUsed) {
		this.cashCanBeUsed = cashCanBeUsed;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public BigDecimal getTradingFee() {
		return tradingFee;
	}
	public void setTradingFee(BigDecimal tradingFee) {
		this.tradingFee = tradingFee;
	}
	public BigDecimal getLevyCharge() {
		return levyCharge;
	}
	public void setLevyCharge(BigDecimal levyCharge) {
		this.levyCharge = levyCharge;
	}
	public BigDecimal getCcassCharge() {
		return ccassCharge;
	}
	public void setCcassCharge(BigDecimal ccassCharge) {
		this.ccassCharge = ccassCharge;
	}
	public BigDecimal getCommonCharges() {
		return commonCharges;
	}
	public void setCommonCharges(BigDecimal commonCharges) {
		this.commonCharges = commonCharges;
	}
	public BigDecimal getStampCharge() {
		return stampCharge;
	}
	public void setStampCharge(BigDecimal stampCharge) {
		this.stampCharge = stampCharge;
	}
}
