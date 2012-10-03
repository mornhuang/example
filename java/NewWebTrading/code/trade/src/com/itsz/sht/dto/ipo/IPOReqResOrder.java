
package com.itsz.sht.dto.ipo;

import java.io.Serializable;
import java.math.BigDecimal;

public class IPOReqResOrder implements Serializable {

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


}
