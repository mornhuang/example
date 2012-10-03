package com.itsz.sht.model;

import java.util.Date;

public class UserProductAllotmentDto implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String usrProdAlltId;
	private String clntId;
	private String prodId;
	private Date effDate;
	private Date expDate;
	private Date alltTime;
	private String alltStatus;
	private Long priceInHkd;

	// Constructors

	/** default constructor */
	public UserProductAllotmentDto() {
	}

	/** minimal constructor */
	public UserProductAllotmentDto(String usrProdAlltId) {
		this.usrProdAlltId = usrProdAlltId;
	}
	
	/** full constructor */
	public UserProductAllotmentDto(String usrProdAlltId, String clntId,
			String prodId, Date effDate, Date expDate, Date alltTime,
			String alltStatus, Long priceInHkd) {
		super();
		this.usrProdAlltId = usrProdAlltId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.effDate = effDate;
		this.expDate = expDate;
		this.alltTime = alltTime;
		this.alltStatus = alltStatus;
		this.priceInHkd = priceInHkd;
	}

	public String getUsrProdAlltId() {
		return usrProdAlltId;
	}

	public void setUsrProdAlltId(String usrProdAlltId) {
		this.usrProdAlltId = usrProdAlltId;
	}

	public String getClntId() {
		return clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getAlltTime() {
		return alltTime;
	}

	public void setAlltTime(Date alltTime) {
		this.alltTime = alltTime;
	}

	public String getAlltStatus() {
		return alltStatus;
	}

	public void setAlltStatus(String alltStatus) {
		this.alltStatus = alltStatus;
	}

	public Long getPriceInHkd() {
		return priceInHkd;
	}

	public void setPriceInHkd(Long priceInHkd) {
		this.priceInHkd = priceInHkd;
	}
	
}