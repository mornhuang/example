package com.itsz.sht.dao.hibernate.model;
import java.util.Date;
public class AclFunction implements java.io.Serializable  {

	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String funcId;
	private String funcNme;
	private String funcTyp;
	private String urlLink;
	private String initBy;
	private Date initDate;
	private String updBy;
	private Date updDate;

	// Constructors

	/** default constructor */
	public AclFunction() {
	}

	/** minimal constructor */
	public AclFunction(String funcId) {
		this.funcId = funcId;
	}

	/** full constructor */
	public AclFunction(String funcId, String funcNme, String funcTyp,
			String urlLink, String initBy, Date initDate, String updBy,
			Date updDate) {
		this.funcId = funcId;
		this.funcNme = funcNme;
		this.funcTyp = funcTyp;
		this.urlLink = urlLink;
		this.initBy = initBy;
		this.initDate = initDate;
		this.updBy = updBy;
		this.updDate = updDate;
	}

	// Property accessors

	public String getFuncId() {
		return this.funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncNme() {
		return this.funcNme;
	}

	public void setFuncNme(String funcNme) {
		this.funcNme = funcNme;
	}

	public String getFuncTyp() {
		return this.funcTyp;
	}

	public void setFuncTyp(String funcTyp) {
		this.funcTyp = funcTyp;
	}

	public String getUrlLink() {
		return this.urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
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
