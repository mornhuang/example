
package com.taifook.adminportal.web.ipo.form;

import java.util.Date;
import java.math.BigDecimal;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * @struts.form name="IPORequestAddForm"
 */

public class IPORequestAddForm extends ActionForm {


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

    private String password;
    private BigDecimal IPO_TFfee;

    private String deadLine;

    private String andEmail;
    private String andTelephone;


    public String getAccountId() {
        return accountId;
    }
    public Date getApplDate() {
        return applDate;
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
    public String getStockCode() {
        return stockCode;
    }
    public String getStatus() {
        return status;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setIsAccept(String isAccept) {
        this.isAccept = isAccept;
    }
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }

    public void setIPO_TFfee(BigDecimal IPO_TFfee) {
        this.IPO_TFfee = IPO_TFfee;
    }
    public void setDsptAmount(BigDecimal dsptAmount) {
        this.dsptAmount = dsptAmount;
    }
    public void setApplyQuantity(int applyQuantity) {
        this.applyQuantity = applyQuantity;
    }
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }
    public void setApplDate(Date applDate) {
        this.applDate = applDate;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getAndTelephone() {
        return andTelephone;
    }
    public void setAndEmail(String andEmail) {
        this.andEmail = andEmail;
    }
    public void setAndTelephone(String andTelephone) {
        this.andTelephone = andTelephone;
    }
}

