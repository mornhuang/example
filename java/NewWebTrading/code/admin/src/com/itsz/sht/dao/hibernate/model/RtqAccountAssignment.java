package com.itsz.sht.dao.hibernate.model;


import java.util.Date;
public class RtqAccountAssignment implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private RtqAccAsgnId id;
	private String rtqLognPwd;
	private Date initDate;
	private String initBy;
	private Date updDate;
	private String updBy;

	// Constructors

	/** default constructor */
	public RtqAccountAssignment() {
	}

	/** minimal constructor */
	public RtqAccountAssignment(RtqAccAsgnId id, String rtqLognPwd) {
		this.id = id;
		this.rtqLognPwd = rtqLognPwd;
	}

	/** full constructor */
	public RtqAccountAssignment(RtqAccAsgnId id, String rtqLognPwd, Date initDate,
			String initBy, Date updDate, String updBy) {
		this.id = id;
		this.rtqLognPwd = rtqLognPwd;
		this.initDate = initDate;
		this.initBy = initBy;
		this.updDate = updDate;
		this.updBy = updBy;
	}

	// Property accessors

	public RtqAccAsgnId getId() {
		return this.id;
	}

	public void setId(RtqAccAsgnId id) {
		this.id = id;
	}

	public String getRtqLognPwd() {
		return this.rtqLognPwd;
	}

	public void setRtqLognPwd(String rtqLognPwd) {
		this.rtqLognPwd = rtqLognPwd;
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

}
