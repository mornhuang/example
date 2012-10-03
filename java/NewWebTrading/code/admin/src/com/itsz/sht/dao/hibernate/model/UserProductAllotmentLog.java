package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class UserProductAllotmentLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String usrProdAlltLogId;
	private String usrProdAlltId;
	private String clntId;
	private String prodId;
	private Date effDate;
	private Date expDate;
	private Date alltTime;
	private String alltStatus;
	private String acesUnitTotal;
	private String acesUnitUsed;
	private String acesUnitExpr;
	private String payReqId;
	private String remarks;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	private String actnType;
	private String actnBy;
	private Date actnDate;

	// Constructors

	/** default constructor */
	public UserProductAllotmentLog() {
	}

	/** minimal constructor */
	public UserProductAllotmentLog(String usrProdAlltLogId) {
		this.usrProdAlltLogId = usrProdAlltLogId;
	}

	/** full constructor */
	public UserProductAllotmentLog(String usrProdAlltLogId, String usrProdAlltId,
			String clntId, String prodId, Date effDate, Date expDate,
			Date alltTime, String alltStatus, String acesUnitTotal,
			String acesUnitUsed, String acesUnitExpr, String payReqId,
			String remarks, String initBy, Date initDate, String updBy,
			Date updDate, String actnType, String actnBy, Date actnDate) {
		this.usrProdAlltLogId = usrProdAlltLogId;
		this.usrProdAlltId = usrProdAlltId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.effDate = effDate;
		this.expDate = expDate;
		this.alltTime = alltTime;
		this.alltStatus = alltStatus;
		this.acesUnitTotal = acesUnitTotal;
		this.acesUnitUsed = acesUnitUsed;
		this.acesUnitExpr = acesUnitExpr;
		this.payReqId = payReqId;
		this.remarks = remarks;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.actnType = actnType;
		this.actnBy = actnBy;
		this.actnDate = actnDate;
	}

	// Property accessors

	public String getUsrProdAlltLogId() {
		return this.usrProdAlltLogId;
	}

	public void setUsrProdAlltLogId(String usrProdAlltLogId) {
		this.usrProdAlltLogId = usrProdAlltLogId;
	}

	public String getUsrProdAlltId() {
		return this.usrProdAlltId;
	}

	public void setUsrProdAlltId(String usrProdAlltId) {
		this.usrProdAlltId = usrProdAlltId;
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

	public Date getEffDate() {
		return this.effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return this.expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getAlltTime() {
		return this.alltTime;
	}

	public void setAlltTime(Date alltTime) {
		this.alltTime = alltTime;
	}

	public String getAlltStatus() {
		return this.alltStatus;
	}

	public void setAlltStatus(String alltStatus) {
		this.alltStatus = alltStatus;
	}

	public String getAcesUnitTotal() {
		return this.acesUnitTotal;
	}

	public void setAcesUnitTotal(String acesUnitTotal) {
		this.acesUnitTotal = acesUnitTotal;
	}

	public String getAcesUnitUsed() {
		return this.acesUnitUsed;
	}

	public void setAcesUnitUsed(String acesUnitUsed) {
		this.acesUnitUsed = acesUnitUsed;
	}

	public String getAcesUnitExpr() {
		return this.acesUnitExpr;
	}

	public void setAcesUnitExpr(String acesUnitExpr) {
		this.acesUnitExpr = acesUnitExpr;
	}

	public String getPayReqId() {
		return this.payReqId;
	}

	public void setPayReqId(String payReqId) {
		this.payReqId = payReqId;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getActnType() {
		return this.actnType;
	}

	public void setActnType(String actnType) {
		this.actnType = actnType;
	}

	public String getActnBy() {
		return this.actnBy;
	}

	public void setActnBy(String actnBy) {
		this.actnBy = actnBy;
	}

	public Date getActnDate() {
		return this.actnDate;
	}

	public void setActnDate(Date actnDate) {
		this.actnDate = actnDate;
	}

}
