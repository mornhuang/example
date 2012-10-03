package com.itsz.web.rtq.obj;

import java.util.Date;

public class RTQAccount {
	private String prodId;
	private String rtqLoginId;
	private String rtqLoginPwd;
	private Date insertDate;
	private String initBy;
	private Date updateDate;
	private String updateBy;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getRtqLoginId() {
		return rtqLoginId;
	}
	public void setRtqLoginId(String rtqLoginId) {
		this.rtqLoginId = rtqLoginId;
	}
	public String getRtqLoginPwd() {
		return rtqLoginPwd;
	}
	public void setRtqLoginPwd(String rtqLoginPwd) {
		this.rtqLoginPwd = rtqLoginPwd;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getInitBy() {
		return initBy;
	}
	public void setInitBy(String initBy) {
		this.initBy = initBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
