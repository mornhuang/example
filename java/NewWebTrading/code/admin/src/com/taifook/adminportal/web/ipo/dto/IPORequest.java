
package com.taifook.adminportal.web.ipo.dto;

import java.util.Date;
import java.math.BigDecimal;

import java.io.Serializable;
import com.taifook.adminportal.web.ipo.form.IPOCancelForm;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;


public class IPORequest implements Serializable {

    private Long applyId;
    private String accountId;
    private Long ipoMasterId;
    private String stockCode;
    private int applyQuantity;
    private BigDecimal dsptAmount;
    private Date applyDate;


    private String remark;
    private String telephone;
    private String email;
    private String status;
    private String isAccept;

    private String password;
    private BigDecimal IPO_TFfee;
    private java.util.Date updateTime;

    private String applyDate_dsply;
    private String applyQuantity_dsply;
    private String dsptAmount_dsply;

    private IPOResult ipoResult;

    public IPORequest() {
    }

    public IPORequest(IPOCancelForm form) {
        this.accountId = form.getAccountId();
        this.password = form.getPassword();
    }


    public IPORequest(IPORequestForm reqForm) {
        if (reqForm != null) {
            this.ipoMasterId = ipoMasterId;
            this.applyQuantity = applyQuantity;
        }
    }


    public void reset() {

    }
    public String getAccountId() {
        return accountId;
    }

    public Long getApplyId() {
        return applyId;
    }
    public int getApplyQuantity() {
        return applyQuantity;
    }

    public BigDecimal getDsptAmount() {
        return dsptAmount;
    }
    public BigDecimal getIPO_TFfee() {
        return IPO_TFfee;
    }
    public Long getIpoMasterId() {
        return ipoMasterId;
    }
    public String getIsAccept() {
        return isAccept;
    }
    public String getPassword() {
        return password;
    }
    public String getRemark() {
        return remark;
    }
    public String getStatus() {
        return status;
    }
    public String getStockCode() {
        return stockCode;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    public void setApplyQuantity(int applyQuantity) {
        this.applyQuantity = applyQuantity;
    }
    public void setDsptAmount(BigDecimal dsptAmount) {
        this.dsptAmount = dsptAmount;
    }

    public void setIPO_TFfee(BigDecimal IPO_TFfee) {
        this.IPO_TFfee = IPO_TFfee;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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
    public Date getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    public java.util.Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getApplyDate_dsply() {
        return applyDate_dsply;
    }
    public void setApplyDate_dsply(String applyDate_dsply) {
        this.applyDate_dsply = applyDate_dsply;
    }
    public String getApplyQuantity_dsply() {
        return applyQuantity_dsply;
    }
    public void setApplyQuantity_dsply(String applyQuantity_dsply) {
        this.applyQuantity_dsply = applyQuantity_dsply;
    }
    public String getDsptAmount_dsply() {
        return dsptAmount_dsply;
    }
    public void setDsptAmount_dsply(String dsptAmount_dsply) {
        this.dsptAmount_dsply = dsptAmount_dsply;
    }
    public IPOResult getIpoResult() {
        return ipoResult;
    }
    public void setIpoResult(IPOResult ipoResult) {
        this.ipoResult = ipoResult;
    }

}
