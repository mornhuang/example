package cn.itsz.newsim.dto.response.entity;

import java.math.BigDecimal;

import cn.itsz.newsim.dto.BaseDTO;

public class LoginResponseEntity extends BaseDTO {
	private String loginId;
	private String tradeAccount;
	private String loginStatus;
	private String userName;
	private String password;
	private String transactionProtection;
	private String allowTradeStatusFlag;
	private BigDecimal totalPurchasingPower = new BigDecimal(0);
	private BigDecimal onHoldBalance = new BigDecimal(0);

	public BigDecimal getOnHoldBalance() {
		return onHoldBalance;
	}
	public void setOnHoldBalance(BigDecimal onHoldBalance) {
		this.onHoldBalance = onHoldBalance;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getTotalPurchasingPower() {
		return totalPurchasingPower;
	}
	public void setTotalPurchasingPower(BigDecimal totalPurchasingPower) {
		this.totalPurchasingPower = totalPurchasingPower;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getAllowTradeStatusFlag() {
		return allowTradeStatusFlag;
	}
	public void setAllowTradeStatusFlag(String allowTradeStatusFlag) {
		this.allowTradeStatusFlag = allowTradeStatusFlag;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
