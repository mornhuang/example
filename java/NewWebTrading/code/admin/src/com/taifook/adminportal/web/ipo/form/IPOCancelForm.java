package com.taifook.adminportal.web.ipo.form;


import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * @struts.form name="IPOCancelForm"
 */

public class IPOCancelForm
    extends ActionForm {

    private String accountId;
    private long applyId;
    private String password;

    private String deadLine;
    private Long ipoMasterId;
    private String status;


    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public long getApplyId() {
        return applyId;
    }
    public void setApplyId(long applyId) {
        this.applyId = applyId;
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
    public void setIpoMasterId(Long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
