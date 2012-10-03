
package com.taifook.adminportal.web.ipo.dto;


import java.io.Serializable;
import com.taifook.adminportal.web.ipo.form.IPOCancelForm;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;


public class IPOReqRes implements Serializable {

    private long applyId;
    private String accountId;

    private String remark;
    private String telephone;
    private String email;
    private String status;

    public IPOReqRes() {
    }

    public void reset() {

    }
    public String getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }
    public String getRemark() {
        return remark;
    }
    public String getStatus() {
        return status;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public long getApplyId() {
        return applyId;
    }
    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }


}
