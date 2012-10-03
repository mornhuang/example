package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class UserProductPayment implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String usrProdPayId;
	private String clntId;
	private String prodId;
	private String priceHkd;
	private String defDebtAcc;
	private String reqSys;
	private Date reqTime;
	private String paySatus;
	private String memoCode;
	private String debtRemarks;
	private String resMessage;
	private Date resTime;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public UserProductPayment() {
	}

	/** minimal constructor */
	public UserProductPayment(String usrProdPayId) {
		this.usrProdPayId = usrProdPayId;
	}

	/** full constructor */
	public UserProductPayment(String usrProdPayId, String clntId, String prodId,
			String priceHkd, String defDebtAcc, String reqSys, Date reqTime,
			String paySatus, String memoCode, String debtRemarks,
			String resMessage, Date resTime, String initBy, Date initDate,
			String updBy, Date updDate) {
		this.usrProdPayId = usrProdPayId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.priceHkd = priceHkd;
		this.defDebtAcc = defDebtAcc;
		this.reqSys = reqSys;
		this.reqTime = reqTime;
		this.paySatus = paySatus;
		this.memoCode = memoCode;
		this.debtRemarks = debtRemarks;
		this.resMessage = resMessage;
		this.resTime = resTime;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getUsrProdPayId() {
		return this.usrProdPayId;
	}

	public void setUsrProdPayId(String usrProdPayId) {
		this.usrProdPayId = usrProdPayId;
	}

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getPriceHkd() {
		return this.priceHkd;
	}

	public void setPriceHkd(String priceHkd) {
		this.priceHkd = priceHkd;
	}

	public String getDefDebtAcc() {
		return this.defDebtAcc;
	}

	public void setDefDebtAcc(String defDebtAcc) {
		this.defDebtAcc = defDebtAcc;
	}

	public String getReqSys() {
		return this.reqSys;
	}

	public void setReqSys(String reqSys) {
		this.reqSys = reqSys;
	}

	public Date getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	public String getPaySatus() {
		return this.paySatus;
	}

	public void setPaySatus(String paySatus) {
		this.paySatus = paySatus;
	}

	public String getMemoCode() {
		return this.memoCode;
	}

	public void setMemoCode(String memoCode) {
		this.memoCode = memoCode;
	}

	public String getDebtRemarks() {
		return this.debtRemarks;
	}

	public void setDebtRemarks(String debtRemarks) {
		this.debtRemarks = debtRemarks;
	}

	public String getResMessage() {
		return this.resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public Date getResTime() {
		return this.resTime;
	}

	public void setResTime(Date resTime) {
		this.resTime = resTime;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}