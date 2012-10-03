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
import java.util.List;


/**
 * This class holds the details of a bank account entity.
 * It contains getters and setters for each variable.
 */
public class AccountDetails implements java.io.Serializable {
    private BigDecimal balance;
    private BigDecimal beginBalance;
    private BigDecimal creditLine;
    private Date beginBalanceTimeStamp;
    private List<Long> customerIds;
    private Long accountId;
    private String description;
    private String type;

    public AccountDetails(
        Long accountId,
        String type,
        String description,
        BigDecimal balance,
        BigDecimal creditLine,
        BigDecimal beginBalance,
        Date beginBalanceTimeStamp) {
        this.accountId = accountId;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.creditLine = creditLine;
        this.beginBalance = beginBalance;
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

    public AccountDetails(
        String type,
        String description,
        BigDecimal balance,
        BigDecimal creditLine,
        BigDecimal beginBalance,
        Date beginBalanceTimeStamp) {
        this.accountId = accountId;
        this.type = type;
        this.description = description;
        this.balance = balance;
        this.creditLine = creditLine;
        this.beginBalance = beginBalance;
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

    public AccountDetails(
        Long accountId,
        String type,
        String description,
        List<Long> customerIds,
        BigDecimal balance,
        BigDecimal creditLine,
        BigDecimal beginBalance,
        Date beginBalanceTimeStamp) {
        this.accountId = accountId;
        this.description = description;
        this.customerIds = customerIds;
        this.balance = balance;
        this.creditLine = creditLine;
        this.beginBalance = beginBalance;
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getCreditLine() {
        return creditLine;
    }

    public BigDecimal getBeginBalance() {
        return beginBalance;
    }

    public Date getBeginBalanceTimeStamp() {
        return beginBalanceTimeStamp;
    }

    public BigDecimal getRemainingCredit() {
        if (this.type.equals("Credit")) {
            return creditLine.subtract(balance);
        } else {
            return creditLine;
        }
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance = beginBalance;
    }

    public void setBeginBalanceTimeStamp(Date beginBalanceTimeStamp) {
        this.beginBalanceTimeStamp = beginBalanceTimeStamp;
    }
}
