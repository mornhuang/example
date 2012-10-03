
package com.itsz.sht.dto.ipo;

import java.io.Serializable;
import java.math.BigDecimal;

public class IPOResult implements Serializable {

    private String ipoCode;
    private Long applyId;
    private String accountId;
    private int applyQuantity;
    private BigDecimal dsptAmount;
    private String prgStatus;
    private String misRefCode;
    private String rejectCode;

    private String password;

    private String applyQuantity_dsply;
    private String dsptAmount_dsply;


//    public IPOResult(IPOCancelForm form) {
//        this.accountId = form.getAccountId();
//        this.password = form.getPassword();
//    }
//
//    public IPOResult() {
//
//    }
//
//    public IPOResult(IPORequestForm reqForm) {
//        if (reqForm != null) {
//            this.ipoCode = ipoCode;
//            this.applyQuantity = applyQuantity;
//        }
//    }

    public void reset() {

    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setApplyQuantity(int applyQuantity) {
        this.applyQuantity = applyQuantity;
    }
    public void setDsptAmount(BigDecimal dsptAmount) {
        this.dsptAmount = dsptAmount;
    }
    public void setIpoCode(String ipoCode) {
        this.ipoCode = ipoCode;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public String getIpoCode() {
        return ipoCode;
    }
    public BigDecimal getDsptAmount() {
        return dsptAmount;
    }
    public int getApplyQuantity() {
        return applyQuantity;
    }
    public Long getApplyId() {
        return applyId;
    }
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    public void setMisRefCode(String misRefCode) {
        this.misRefCode = misRefCode;
    }
    public String getMisRefCode() {
        return misRefCode;
    }
    public String getPrgStatus() {
        return prgStatus;
    }
    public void setPrgStatus(String prgStatus) {
        this.prgStatus = prgStatus;
    }
    public String getRejectCode() {
        return rejectCode;
    }
    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }
    public String getApplyQuantity_dsply() {
        return applyQuantity_dsply;
    }
    public void setApplyQuantity_dsply(String applyQuantity_dsply) {
        this.applyQuantity_dsply = applyQuantity_dsply;
    }
    public void setDsptAmount_dsply(String dsptAmount_dsply) {
        this.dsptAmount_dsply = dsptAmount_dsply;
    }
    public String getDsptAmount_dsply() {
        return dsptAmount_dsply;
    }

}

