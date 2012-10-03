package cn.itsz.newsim.view.form;


/**
 * $Id: CancelOrderForm.java,v 1.2 2011/03/05 14:12:32 zxfan Exp $
 * 
 * @Project:portal.head
 * @File:CancelOrderForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class CancelOrderForm extends BaseForm {

	private static final long serialVersionUID = 1157990541755411503L;
	private String password;
	private String orderId;
	private String accountId;
	private String token;
	private String transactionProtection;

	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
