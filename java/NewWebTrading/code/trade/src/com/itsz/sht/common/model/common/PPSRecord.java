package com.itsz.sht.common.model.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * $Id: PPSRecord.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal
 * @File:PPSRecord.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class PPSRecord implements Serializable {

    private static final long serialVersionUID = -565133124424247109L;
    private String txRef;
    private String txDate;
    private BigDecimal txAmt;

    /**
     * @return
     */
    public BigDecimal getTxAmt() {
        return txAmt;
    }
    /**
     * @return
     */
    public String getTxDate() {
        return txDate;
    }
    /**
     * @return
     */
    public String getTxRef() {
        return txRef;
    }
    /**
     * @param decimal
     */
    public void setTxAmt(BigDecimal decimal) {
        txAmt = decimal;
    }
    /**
     * @param string
     */
    public void setTxDate(String string) {
        txDate = string;
    }
    /**
     * @param string
     */
    public void setTxRef(String string) {
        txRef = string;
    }
}

