/*
 *
 */
package com.taifook.adminportal.web.ipo.form;


import java.util.ArrayList;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.dto.IPORecord;

/**
 * @struts.form name="IPORequestForm"
 */

public class IPORequestForm extends ActionForm {


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

