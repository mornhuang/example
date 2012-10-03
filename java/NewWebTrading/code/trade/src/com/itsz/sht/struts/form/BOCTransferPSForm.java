package com.itsz.sht.struts.form;

import java.util.List;

public class BOCTransferPSForm extends ITSZForm {
    
    private static final long serialVersionUID = 1L;
    private String mch_custID;
    private String mch_payAmt;
    private String mch_merchID;
    private String mch_merchRef;
    private String mch_locale;
    private String mch_timeStamp;
    private String mch_returnURL;
    private String remarks;
    private boolean disableFromAccount;
    //for display only
    /** List of com.taifook.mtss.web.accountV1.dtoV1.BriefAccountInfo */
    private List fromAccounts;
    
    /**
     * @return
     */
    public String getMch_custID() {
        return mch_custID;
    }
    /**
     * @return
     */
    public String getMch_payAmt() {
        return mch_payAmt;
    }
    /**
     * @param string
     */
    public void setMch_custID(String string) {
        mch_custID = string;
    }
    /**
     * @struts.validator type="required" msgkey="errors.required" arg0="label.bocTransferForm.transferAmount"
     * @struts.validator type="float" msgkey="errors.float" arg0="label.bocTransferForm.transferAmount"
     * @param string
     */
    public void setMch_payAmt(String string) {
        mch_payAmt = string;
    }
    /**
     * @return
     */
    public List getFromAccounts() {
        return fromAccounts;
    }
    /**
     * @param list
     */
    public void setFromAccounts(List list) {
        fromAccounts = list;
    }
    /**
     * @return
     */
    public String getMch_locale() {
        return mch_locale;
    }
    /**
     * @return
     */
    public String getMch_merchID() {
        return mch_merchID;
    }
    /**
     * @return
     */
    public String getMch_merchRef() {
        return mch_merchRef;
    }
    /**
     * @return
     */
    public String getMch_returnURL() {
        return mch_returnURL;
    }
    /**
     * @return
     */
    public String getMch_timeStamp() {
        return mch_timeStamp;
    }
    /**
     * @param string
     */
    public void setMch_locale(String string) {
        mch_locale = string;
    }
    /**
     * @param string
     */
    public void setMch_merchID(String string) {
        mch_merchID = string;
    }
    /**
     * @param string
     */
    public void setMch_merchRef(String string) {
        mch_merchRef = string;
    }
    /**
     * @param string
     */
    public void setMch_returnURL(String string) {
        mch_returnURL = string;
    }
    /**
     * @param string
     */
    public void setMch_timeStamp(String string) {
        mch_timeStamp = string;
    }
    /**
     * @return
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * @param string
     */
    public void setRemarks(String string) {
        remarks = string;
    }
    /**
     * @return
     */
    public boolean isDisableFromAccount() {
        return disableFromAccount;
    }
    /**
     * @param b
     */
    public void setDisableFromAccount(boolean b) {
        disableFromAccount = b;
    }
}
