package cn.itsz.newsim.view.form;

public class TransactionProtectionForm extends BaseForm {

	private static final long serialVersionUID = 1L;
    private String transactionProtection;
    private String password;
	public String getTransactionProtection() {
		return transactionProtection;
	}
	public void setTransactionProtection(String transactionProtection) {
		this.transactionProtection = transactionProtection;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
