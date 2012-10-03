package com.taifook.adminportal.web.ipo.dto;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import com.taifook.adminportal.web.ipo.form.IPOCancelForm;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;

public class IPOReqResOrder
    implements Serializable {

    private long applyId;
    private String ipoName;
    private String stockCode;
    private BigDecimal price;
    private int needQuantity;
    private int gainQuantity;
    private BigDecimal dsptAmount;

    private String accountId;

    private String telephone;
    private String email;
    private String applyStatus;
    private String resultStatus;
    private String misRefCode;
    private String rejectCode;
    private long ipoMasterId;
    private String applyTime;
    private Date applyDate;
    private String  isAccept;
    private String remark;

    private String dsptAmount_dsply;

    public IPOReqResOrder() {
    }

    public void reset() {

    }

    public String getAccountId() {
        return accountId;
    }

    public long getApplyId() {
        return applyId;
    }

    public BigDecimal getDsptAmount() {
        return dsptAmount;
    }

    public String getEmail() {
        return email;
    }

    public String getIpoName() {
        return ipoName;
    }

    public int getGainQuantity() {
        return gainQuantity;
    }

    public String getMisRefCode() {
        return misRefCode;
    }

    public int getNeedQuantity() {
        return needQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRejectCode() {
        return rejectCode;
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

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public void setDsptAmount(BigDecimal dsptAmount) {
        this.dsptAmount = dsptAmount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGainQuantity(int gainQuantity) {
        this.gainQuantity = gainQuantity;
    }

    public void setIpoName(String ipoName) {
        this.ipoName = ipoName;
    }

    public void setMisRefCode(String misRefCode) {
        this.misRefCode = misRefCode;
    }

    public void setNeedQuantity(int needQuantity) {
        this.needQuantity = needQuantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public long getIpoMasterId() {
        return ipoMasterId;
    }

    public void setIpoMasterId(long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getDsptAmount_dsply() {
        return dsptAmount_dsply;
    }

    public void setDsptAmount_dsply(String dsptAmount_dsply) {
        this.dsptAmount_dsply = dsptAmount_dsply;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public String getIsAccept() {
    return isAccept;
  }
  public void setIsAccept(String isAccept) {
    this.isAccept = isAccept;
  }

}
