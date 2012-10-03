package com.itsz.sht.dao.hibernate.model;
import java.util.Date;
public class ServicesAccessLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String svcAcesLogId;
	private String clntId;
	private String prodId;
	private String rtqPrdr;
	private String rtqUr;
	private String rtqStatus;
	private String rtqLognId;
	private String rtqLognPwd;
	private String remoteClntIp;
	private String acesUrl;
	private Date acesTime;
	private String acesChnnl;
	private String remarkss;
	private String actnType;
	private Date actnDate;
	private String actnBy;

	// Constructors

	/** default constructor */
	public ServicesAccessLog() {
	}

	/** minimal constructor */
	public ServicesAccessLog(String svcAcesLogId) {
		this.svcAcesLogId = svcAcesLogId;
	}

	/** full constructor */
	public ServicesAccessLog(String svcAcesLogId, String clntId, String prodId,
			String rtqPrdr, String rtqUr, String rtqStatus, String rtqLognId,
			String rtqLognPwd, String remoteClntIp, String acesUrl,
			Date acesTime, String acesChnnl, String remarkss, String actnType,
			Date actnDate, String actnBy) {
		this.svcAcesLogId = svcAcesLogId;
		this.clntId = clntId;
		this.prodId = prodId;
		this.rtqPrdr = rtqPrdr;
		this.rtqUr = rtqUr;
		this.rtqStatus = rtqStatus;
		this.rtqLognId = rtqLognId;
		this.rtqLognPwd = rtqLognPwd;
		this.remoteClntIp = remoteClntIp;
		this.acesUrl = acesUrl;
		this.acesTime = acesTime;
		this.acesChnnl = acesChnnl;
		this.remarkss = remarkss;
		this.actnType = actnType;
		this.actnDate = actnDate;
		this.actnBy = actnBy;
	}

	// Property accessors

	public String getSvcAcesLogId() {
		return this.svcAcesLogId;
	}

	public void setSvcAcesLogId(String svcAcesLogId) {
		this.svcAcesLogId = svcAcesLogId;
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

	public String getRtqPrdr() {
		return this.rtqPrdr;
	}

	public void setRtqPrdr(String rtqPrdr) {
		this.rtqPrdr = rtqPrdr;
	}

	public String getRtqUr() {
		return this.rtqUr;
	}

	public void setRtqUr(String rtqUr) {
		this.rtqUr = rtqUr;
	}

	public String getRtqStatus() {
		return this.rtqStatus;
	}

	public void setRtqStatus(String rtqStatus) {
		this.rtqStatus = rtqStatus;
	}

	public String getRtqLognId() {
		return this.rtqLognId;
	}

	public void setRtqLognId(String rtqLognId) {
		this.rtqLognId = rtqLognId;
	}

	public String getRtqLognPwd() {
		return this.rtqLognPwd;
	}

	public void setRtqLognPwd(String rtqLognPwd) {
		this.rtqLognPwd = rtqLognPwd;
	}

	public String getRemoteClntIp() {
		return this.remoteClntIp;
	}

	public void setRemoteClntIp(String remoteClntIp) {
		this.remoteClntIp = remoteClntIp;
	}

	public String getAcesUrl() {
		return this.acesUrl;
	}

	public void setAcesUrl(String acesUrl) {
		this.acesUrl = acesUrl;
	}

	public Date getAcesTime() {
		return this.acesTime;
	}

	public void setAcesTime(Date acesTime) {
		this.acesTime = acesTime;
	}

	public String getAcesChnnl() {
		return this.acesChnnl;
	}

	public void setAcesChnnl(String acesChnnl) {
		this.acesChnnl = acesChnnl;
	}

	public String getRemarkss() {
		return this.remarkss;
	}

	public void setRemarkss(String remarkss) {
		this.remarkss = remarkss;
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
