package com.itsz.sht.common.model.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * $Id: BOCTransfer.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:BOCTransfer.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class BOCTransfer implements Serializable {

    private static final long serialVersionUID = 3760758844789844254L;
    private String accountId;
    private BigDecimal amount;
    private String locale;
    private String timestamp;
    private String refCode;
    
    /**
     * @return
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @return
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param string
     */
    public void setAccountId(String string) {
        accountId = string;
    }

    /**
     * @param decimal
     */
    public void setAmount(BigDecimal decimal) {
        amount = decimal;
    }

    /**
     * @return
     */
    public String getRefCode() {
        return refCode;
    }

    /**
     * @param string
     */
    public void setRefCode(String string) {
        refCode = string;
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
    public String getTimestamp() {
        return timestamp;
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
    public void setTimestamp(String string) {
        timestamp = string;
    }

}
