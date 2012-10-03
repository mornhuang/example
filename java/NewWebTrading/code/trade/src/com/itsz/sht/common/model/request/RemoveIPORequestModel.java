package com.itsz.sht.common.model.request;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: RemoveIPORequestModel.java,v 1.3 2010/12/16 10:00:50 xlliu Exp $
 * @Project:NewWebTrading
 * @File:RemoveIPORequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-4
 */
public class RemoveIPORequestModel extends AbstractRequestModel {
    private String accountId;
    private long applyId;
    private String loginId;
    private String password;

    private String deadLine;
    private Long ipoMasterId;
    private String status;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public long getApplyId() {
		return applyId;
	}
	public void setApplyId(long applyId) {
		this.applyId = applyId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public Long getIpoMasterId() {
		return ipoMasterId;
	}
	public void setIpoMasterId(long ipoMasterId) {
		this.ipoMasterId = ipoMasterId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
