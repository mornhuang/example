package com.itsz.sht.dao.hibernate.model;
import java.util.Date;
public class RtqAccountAssignmentLog implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private String rtqAccAsgnLogId;
	private String prodId;
	private String rtqLognId;
	private String rtqLognPwd;
	private String clntId;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;
	private String actnType;
	private Date actnDate;
	private String actnBy;

	// Constructors

	/** default constructor */
	public RtqAccountAssignmentLog() {
	}

	/** minimal constructor */
	public RtqAccountAssignmentLog(String rtqAccAsgnLogId) {
		this.rtqAccAsgnLogId = rtqAccAsgnLogId;
	}

	/** full constructor */
	public RtqAccountAssignmentLog(String rtqAccAsgnLogId, String prodId,
			String rtqLognId, String rtqLognPwd, String clntId, Date initDate,
			String initBy, Date updDate, String updBy, String actnType,
			Date actnDate, String actnBy) {
		this.rtqAccAsgnLogId = rtqAccAsgnLogId;
		this.prodId = prodId;
		this.rtqLognId = rtqLognId;
		this.rtqLognPwd = rtqLognPwd;
		this.clntId = clntId;
		this.initDate = initDate;
		this.initBy = initBy;
		this.updDate = updDate;
		this.updBy = updBy;
		this.actnType = actnType;
		this.actnDate = actnDate;
		this.actnBy = actnBy;
	}

	// Property accessors

	public String getRtqAccAsgnLogId() {
		return this.rtqAccAsgnLogId;
	}

	public void setRtqAccAsgnLogId(String rtqAccAsgnLogId) {
		this.rtqAccAsgnLogId = rtqAccAsgnLogId;
	}

	public String getProdId() {
		return this.prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
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

	public String getClntId() {
		return this.clntId;
	}

	public void setClntId(String clntId) {
		this.clntId = clntId;
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
