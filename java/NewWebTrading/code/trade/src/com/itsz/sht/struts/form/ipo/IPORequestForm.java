/*
 *
 */
package com.itsz.sht.struts.form.ipo;

import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.Globals;

import com.itsz.sht.dto.ipo.IPORecord;
import com.itsz.sht.struts.form.ITSZForm;

/**
 * @struts.form name="IPORequestForm"
 */

public class IPORequestForm extends ITSZForm {


    private String accountId;
    private boolean isAllIPO;
    private ArrayList ipoRecordList;
    private String ipoCode;
    private IPORecord ipoRecord;
    private long ipoMasterId;

    private String password;

    private String toURL;

    private String applyType;
    private String andIPOCode;
    private String andAccount;

    private String[] applyCodeStr;

    private int firstResult;
    private int currPage;


    public IPORequestForm() {
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors ers = new ActionErrors();
        ers = commonCheck(mapping, request);
        return ers;
    }
    protected ActionErrors commonCheck(ActionMapping mapping,
            HttpServletRequest request) {

        Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        MessageResources mr = (MessageResources) request.getAttribute(Globals.MESSAGES_KEY);
        mr.setReturnNull(true);

        ActionErrors errors = new ActionErrors();
       /*
        if (quantity == null || "".equals(quantity)) {
            String key = mr.getMessage(locale, "label.general.quantity");
            errors.add("quantity", new WebActionError("errors.required", new Object[]{key}));
        }
        */
        return errors;
    }
    public String getAccountId() {
        return accountId;
    }


    public String getPassword() {
        return password;
    }


    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAllIPO() {
        return isAllIPO;
    }
    public void setIsAllIPO(boolean isAllIPO) {
        this.isAllIPO = isAllIPO;
    }
    public ArrayList getIpoRecordList() {
        return ipoRecordList;
    }
    public void setIpoRecordList(ArrayList ipoRecordList) {
        this.ipoRecordList = ipoRecordList;
    }
    public IPORecord getIpoRecord() {
        return ipoRecord;
    }
    public void setIpoRecord(IPORecord ipoRecord) {
        this.ipoRecord = ipoRecord;
    }
    public String getIpoCode() {
        return ipoCode;
    }
    public void setIpoCode(String ipoCode) {
        this.ipoCode = ipoCode;
    }
    public long getIpoMasterId() {
        return ipoMasterId;
    }
    public void setIpoMasterId(long ipoMasterId) {
        this.ipoMasterId = ipoMasterId;
    }
    public String getToURL() {
        return toURL;
    }
    public void setToURL(String toURL) {
        this.toURL = toURL;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
    public String getAndIPOCode() {
        return andIPOCode;
    }
    public void setAndIPOCode(String andIPOCode) {
        this.andIPOCode = andIPOCode;
    }
    public String getAndAccount() {
        return andAccount;
    }
    public void setAndAccount(String andAccount) {
        this.andAccount = andAccount;
    }
    public String[] getApplyCodeStr() {
        return applyCodeStr;
    }
    public void setApplyCodeStr(String[] applyCodeStr) {
        this.applyCodeStr = applyCodeStr;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }
    public int getCurrPage() {
        return currPage;
    }
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
}

