package com.itsz.sht.struts.form;



import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class RtqApplicationForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String rtqPrdr;
	private String rtqUrl;
	private String rtqStatus;
	private Date initDate;
	private String initBy;
	private Date updDate;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getRtqPrdr() {
		return rtqPrdr;
	}
	public void setRtqPrdr(String rtqPrdr) {
		this.rtqPrdr = rtqPrdr;
	}
	public String getRtqUrl() {
		return rtqUrl;
	}
	public void setRtqUrl(String rtqUrl) {
		this.rtqUrl = rtqUrl;
	}
	public String getRtqStatus() {
		return rtqStatus;
	}
	public void setRtqStatus(String rtqStatus) {
		this.rtqStatus = rtqStatus;
	}
	public Date getInitDate() {
		return initDate;
	}
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	private String updBy;
}
