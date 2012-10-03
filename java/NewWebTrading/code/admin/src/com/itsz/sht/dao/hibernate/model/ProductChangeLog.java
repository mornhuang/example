package com.itsz.sht.dao.hibernate.model;
import java.util.Date;
public class ProductChangeLog  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    
	private String prodChgLogId;
	private String prodId;
	private String prodStatus;
	private Long quota;
	private Long alltQuota;
	private String svcMode;
	private String valtOfSvc;
	private Long acesUnit;
	private String billType;
	private String discType;
	private Long priceInHkd;
	private Date effDate;
	private Date exprDate;
	private String remarks;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;
	private String actnType;
	private Date actnDate;
	private String actnBy;

	// Constructors

	/** default constructor */
	public ProductChangeLog() {
	}

	/** minimal constructor */
	public ProductChangeLog(String prodChgLogId) {
		this.prodChgLogId = prodChgLogId;
	}

	/** full constructor */
	public ProductChangeLog(String prodChgLogId, String prodId, String prodStatus,
			Long quota, Long alltQuota, String svcMode, String valtOfSvc,
			Long acesUnit, String billType, String discType, Long priceInHkd,
			Date effDate, Date exprDate, String remarks, Date initDate,
			String initBy, Date updDate, String updBy, String actnType,
			Date actnDate, String actnBy) {
		this.prodChgLogId = prodChgLogId;
		this.prodId = prodId;
		this.prodStatus = prodStatus;
		this.quota = quota;
		this.alltQuota = alltQuota;
		this.svcMode = svcMode;
		this.valtOfSvc = valtOfSvc;
		this.acesUnit = acesUnit;
		this.billType = billType;
		this.discType = discType;
		this.priceInHkd = priceInHkd;
		this.effDate = effDate;
		this.exprDate = exprDate;
		this.remarks = remarks;
		this.initDate = initDate;
		this.initBy = initBy;
		this.updDate = updDate;
		this.updBy = updBy;
		this.actnType = actnType;
		this.actnDate = actnDate;
		this.actnBy = actnBy;
	}

	// Property accessors

	public String getProdChgLogId() {
		return this.prodChgLogId;
	}

	public void setProdChgLogId(String prodChgLogId) {
		this.prodChgLogId = prodChgLogId;
	}

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdStatus() {
		return this.prodStatus;
	}

	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}

	public Long getQuota() {
		return this.quota;
	}

	public void setQuota(Long quota) {
		this.quota = quota;
	}

	public Long getAlltQuota() {
		return this.alltQuota;
	}

	public void setAlltQuota(Long alltQuota) {
		this.alltQuota = alltQuota;
	}

	public String getSvcMode() {
		return this.svcMode;
	}

	public void setSvcMode(String svcMode) {
		this.svcMode = svcMode;
	}

	public String getValtOfSvc() {
		return this.valtOfSvc;
	}

	public void setValtOfSvc(String valtOfSvc) {
		this.valtOfSvc = valtOfSvc;
	}

	public Long getAcesUnit() {
		return this.acesUnit;
	}

	public void setAcesUnit(Long acesUnit) {
		this.acesUnit = acesUnit;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getDiscType() {
		return this.discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public Long getPriceInHkd() {
		return this.priceInHkd;
	}

	public void setPriceInHkd(Long priceInHkd) {
		this.priceInHkd = priceInHkd;
	}

	public Date getEffDate() {
		return this.effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExprDate() {
		return this.exprDate;
	}

	public void setExprDate(Date exprDate) {
		this.exprDate = exprDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getInitBy() {
		return this.initBy;
	}

	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}

	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public String getUpdBy() {
		return this.updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
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
