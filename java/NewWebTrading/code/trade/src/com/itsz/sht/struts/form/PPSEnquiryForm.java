package com.itsz.sht.struts.form;

import java.util.List;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * $Id: PPSEnquiryForm.java,v 1.2 2011/03/22 02:01:51 pbxie Exp $
 * @Project:portal
 * @File:PPSEnquiryForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class PPSEnquiryForm extends ITSZForm {

    private static final long serialVersionUID = 1L;
    private String lang;
    private String locale;
    private String opCode;
    
    /** List of com.taifook.mtss.web.transferV1.dtoV1.PPSRecord */
    private List ppsRecords;

    /**
     * @return
     */
    public List getPpsRecords() {
        return ppsRecords;
    }

    /**
     * @param list
     */
    public void setPpsRecords(List list) {
        ppsRecords = list;
    }

    /**
     * @return
     */
    public String getLang() {
        return lang;
    }

    /**
     * @return
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @return
     */
    public String getOpCode() {
        return opCode;
    }

    /**
     * @param string
     */
    public void setLang(String string) {
        lang = string;
    }

    /**
     * @param string
     */
    public void setLocale(String string) {
        locale = string;
    }

    /**
     * @param string
     */
    public void setOpCode(String string) {
        opCode = string;
    }

}
