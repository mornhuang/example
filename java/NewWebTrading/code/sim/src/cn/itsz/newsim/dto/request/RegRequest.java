package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class RegRequest extends BaseDTO {
	private String id;
	private String loginId;
	private String passWord;
	private String email;
	private String addNo;
	private String telephone;
	private String username;
	private String client;
	private String clientNo;
	private double clientMoney;
	private String transactionProtection;
	private String allowTradeStatusFlag;
	private String lastUpdate;

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddNo() {
		return addNo;
	}

	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientNo() {
		return clientNo;
	}

	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}

	public double getClientMoney() {
		return clientMoney;
	}

	public void setClientMoney(double clientMoney) {
		this.clientMoney = clientMoney;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
