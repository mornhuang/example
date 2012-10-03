package com.itsz.sht.dao.hibernate.model;

import java.util.Date;

public class UserProductLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String usrProdLogId;
	private String clntId;
	private String prodId;
	private String allwRenl;
	private Date exprDate;
	private String status;
	private String acesUnitTotal;
	private String acesUnitUsed;
	private String acesUnitExpr;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;
	private String actnType;
	private Date actnDate;
	private String actnBy;

	// Constructors

	/** default constructor */
	public UserProductLog() {
	}

	/** minimal constructor */
	public UserProductLog(String usrProdLogId) {
		this.usrProdLogId = usrProdLogId;
	}

	/** full constructor */
	public UserProductLog(String usrProdLogId, String clntId, String prodId,
			String allwRenl, Date exprDate, String status,
			String acesUnitTotal, String acesUnitUsed, String acesUnitExpr,
			String initBy, Date initDate, String updBy, Date updDate,
			String actnType, Date actnDate, String actnBy) {
		this.usrProdLogId = usrProdLogId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.allwRenl = allwRenl;
		this.exprDate = exprDate;
		this.status = status;
		this.acesUnitTotal = acesUnitTotal;
		this.acesUnitUsed = acesUnitUsed;
		this.acesUnitExpr = acesUnitExpr;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.actnType = actnType;
		this.actnDate = actnDate;
		this.actnBy = actnBy;
	}

	// Property accessors

	public String getUsrProdLogId() {
		return this.usrProdLogId;
	}

	public void setUsrProdLogId(String usrProdLogId) {
		this.usrProdLogId = usrProdLogId;
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

	public String getAllwRenl() {
		return this.allwRenl;
	}

	public void setAllwRenl(String allwRenl) {
		this.allwRenl = allwRenl;
	}

	public Date getExprDate() {
		return this.exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getActnDate() {
		return this.actnDate;
	}

	public void setActnDate(Date actnDate) {
		this.actnDate = actnDate;
	}

	public String getActnBy() {
		return this.actnBy;
	}

	public void setActnBy(String actnBy) {
		this.actnBy = actnBy;
	}
}