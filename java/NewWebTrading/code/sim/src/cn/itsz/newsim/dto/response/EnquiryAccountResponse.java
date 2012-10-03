package cn.itsz.newsim.dto.response;

import java.math.BigDecimal;
import java.util.Collection;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.CashHoldingSummar;

public class EnquiryAccountResponse extends ResultMessage {
	private Collection<CashHoldingSummar> cashHoldingCol;
    private String accountId;
    private BigDecimal buyOrderControlLimit;
	private BigDecimal totalPurchasingPower;
	private BigDecimal onHoldBalance;

	public BigDecimal getOnHoldBalance() {
		return onHoldBalance;
	}

	public void setOnHoldBalance(BigDecimal onHoldBalance) {
		this.onHoldBalance = onHoldBalance;
	}

	public BigDecimal getTotalPurchasingPower() {
		return totalPurchasingPower;
	}

	public void setTotalPurchasingPower(BigDecimal totalPurchasingPower) {
		this.totalPurchasingPower = totalPurchasingPower;
	}

	public Collection<CashHoldingSummar> getCashHoldingCol() {
		return cashHoldingCol;
	}

	public void setCashHoldingCol(Collection<CashHoldingSummar> cashHoldingCol) {
		this.cashHoldingCol = cashHoldingCol;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBuyOrderControlLimit() {
		return buyOrderControlLimit;
	}

	public void setBuyOrderControlLimit(BigDecimal buyOrderControlLimit) {
		this.buyOrderControlLimit = buyOrderControlLimit;
	}
}
