package com.itsz.sht.common.model.response;

import java.math.BigDecimal;
import java.util.Date;

import com.itsz.sht.common.model.AbstractRequestModel;

/**
 * $Id: IPOAddRequestModel.java,v 1.5 2010/12/14 09:42:29 xlliu Exp $
 * @Project:NewWebTrading
 * @File:IPOAddRequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-4
 */
public class IPOAddRequestModel extends AbstractRequestModel {
    private Long applyId;
    private String accountId;
    private Long ipoMasterId;
    private String stockCode;
    private int applyQuantity;
    private BigDecimal dsptAmount;
    private Date applDate;


    private String remark;
    private String telephone;
    private String email;
    private String status;
    private String isAccept;

    private String loginId;
    private String password;
    private BigDecimal IPO_TFfee;

    private String deadLine;

    private String andEmail;
    private String andTelephone;    
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public long getIpoMasterId() {
		return ipoMasterId;
	}
	public void setIpoMasterId(long ipoMasterId) {
		this.ipoMasterId = ipoMasterId;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public int getApplyQuantity() {
		return applyQuantity;
	}
	public void setApplyQuantity(int applyQuantity) {
		this.applyQuantity = applyQuantity;
	}
	public BigDecimal getDsptAmount() {
		return dsptAmount;
	}
	public void setDsptAmount(BigDecimal dsptAmount) {
		this.dsptAmount = dsptAmount;
	}
	public Date getApplDate() {
		return applDate;
	}
	public void setApplDate(Date applDate) {
		this.applDate = applDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public BigDecimal getIPO_TFfee() {
		return IPO_TFfee;
	}
	public void setIPO_TFfee(BigDecimal iPOTFfee) {
		IPO_TFfee = iPOTFfee;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getAndEmail() {
		return andEmail;
	}
	public void setAndEmail(String andEmail) {
		this.andEmail = andEmail;
	}
	public String getAndTelephone() {
		return andTelephone;
	}
	public void setAndTelephone(String andTelephone) {
		this.andTelephone = andTelephone;
	}
}
