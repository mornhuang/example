package com.itsz.sht.struts.form;

public class CheckFundDepositForm extends ITSZForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String depositDate;      //存款日期
	private String depositsTimeH;	   //存款时间
	private String depositsTimeM;	   //存款时间
	private String accountNumber;  //账户号码
	private String depositMeansB;    //存款途径
	private String depositMeansA;    //存款途径
	private String bankNumber;      //存入银行号码
	private String amount;			//金额
	private String amountN;			//金额
	private String receiptNo;			//收据号码
	private String depositDeadline;  //存款截止时间
	
	public String getDepositDate() {
		return depositDate;
	}
	public void setDepositDate(String depositDate) {
		this.depositDate = depositDate;
	}
	public String getDepositsTimeH() {
		return depositsTimeH;
	}
	public void setDepositsTimeH(String depositsTimeH) {
		this.depositsTimeH = depositsTimeH;
	}
	public String getDepositsTimeM() {
		return depositsTimeM;
	}
	public void setDepositsTimeM(String depositsTimeM) {
		this.depositsTimeM = depositsTimeM;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDepositMeansB() {
		return depositMeansB;
	}
	public void setDepositMeansB(String depositMeansB) {
		this.depositMeansB = depositMeansB;
	}
	public String getDepositMeansA() {
		return depositMeansA;
	}
	public void setDepositMeansA(String depositMeansA) {
		this.depositMeansA = depositMeansA;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountN() {
		return amountN;
	}
	public void setAmountN(String amountN) {
		this.amountN = amountN;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getDepositDeadline() {
		return depositDeadline;
	}
	public void setDepositDeadline(String depositDeadline) {
		this.depositDeadline = depositDeadline;
	}
	
	
	
	
}
