package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class CashHoldingSummar extends BaseDTO {
    private BigDecimal ledgerBalance;
    private BigDecimal onHoldBalance;
    private BigDecimal availableBalance;
    private BigDecimal netCashBalance;
    private BigDecimal stockMarketBalance;
    private BigDecimal pendingSettlementDay1;
    private BigDecimal pendingSettlementDay2;
    private BigDecimal clearingCheque1;
    private BigDecimal clearingCheque2;
    private BigDecimal discountedValue;
    private BigDecimal marginReq;
	public BigDecimal getLedgerBalance() {
		return ledgerBalance;
	}
	public void setLedgerBalance(BigDecimal ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}
	public BigDecimal getOnHoldBalance() {
		return onHoldBalance;
	}
	public void setOnHoldBalance(BigDecimal onHoldBalance) {
		this.onHoldBalance = onHoldBalance;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public BigDecimal getNetCashBalance() {
		return netCashBalance;
	}
	public void setNetCashBalance(BigDecimal netCashBalance) {
		this.netCashBalance = netCashBalance;
	}
	public BigDecimal getStockMarketBalance() {
		return stockMarketBalance;
	}
	public void setStockMarketBalance(BigDecimal stockMarketBalance) {
		this.stockMarketBalance = stockMarketBalance;
	}
	public BigDecimal getPendingSettlementDay1() {
		return pendingSettlementDay1;
	}
	public void setPendingSettlementDay1(BigDecimal pendingSettlementDay1) {
		this.pendingSettlementDay1 = pendingSettlementDay1;
	}
	public BigDecimal getPendingSettlementDay2() {
		return pendingSettlementDay2;
	}
	public void setPendingSettlementDay2(BigDecimal pendingSettlementDay2) {
		this.pendingSettlementDay2 = pendingSettlementDay2;
	}
	public BigDecimal getClearingCheque1() {
		return clearingCheque1;
	}
	public void setClearingCheque1(BigDecimal clearingCheque1) {
		this.clearingCheque1 = clearingCheque1;
	}
	public BigDecimal getClearingCheque2() {
		return clearingCheque2;
	}
	public void setClearingCheque2(BigDecimal clearingCheque2) {
		this.clearingCheque2 = clearingCheque2;
	}
	public BigDecimal getDiscountedValue() {
		return discountedValue;
	}
	public void setDiscountedValue(BigDecimal discountedValue) {
		this.discountedValue = discountedValue;
	}
	public BigDecimal getMarginReq() {
		return marginReq;
	}
	public void setMarginReq(BigDecimal marginReq) {
		this.marginReq = marginReq;
	}
}
