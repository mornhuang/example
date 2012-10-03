package cn.itsz.newsim.dto;

public class UserProfileModel {
	private Long id;
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
	
	
	public String getAddNo() {
		return addNo;
	}
	public String getAllowTradeStatusFlag() {
		return allowTradeStatusFlag;
	}
	public String getClient() {
		return client;
	}
	public double getClientMoney() {
		return clientMoney;
	}

	public String getClientNo() {
		return clientNo;
	}
	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public String getUsername() {
		return username;
	}
	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}
	public void setAllowTradeStatusFlag(String allowTradeStatusFlag) {
		this.allowTradeStatusFlag = allowTradeStatusFlag;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public void setClientMoney(double clientMoney) {
		this.clientMoney = clientMoney;
	}
	public void setClientNo(String clientNo) {
		this.clientNo = clientNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
