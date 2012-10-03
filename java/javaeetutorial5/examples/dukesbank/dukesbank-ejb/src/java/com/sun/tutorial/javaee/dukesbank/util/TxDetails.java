/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;


/**
 * This class holds the details of a bank transaction entity.
 */
public class TxDetails implements java.io.Serializable {
    private BigDecimal amount;
    private BigDecimal balance;
    private Date timeStamp;
    private Long txId;
    private String accountId;
    private String description;

    public TxDetails(
        Long txId,
        Date timeStamp,
        BigDecimal amount,
        BigDecimal balance,
        String description) {
        this.txId = txId;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    public Long getTxId() {
        return txId;
    }

    public String getAccountId() {
        return accountId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getDescription() {
        return description;
    }
}
