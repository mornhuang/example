/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "BANK_TX")
@NamedQueries({
    @NamedQuery(name = "Tx.FindById",query = "SELECT a FROM Tx a WHERE a.id = :id")
    , @NamedQuery(name = "Tx.FindByTimeStamp", query = "SELECT a FROM Tx a WHERE a.timeStamp = :timeStamp")
    , @NamedQuery(name = "Tx.FindByAmount", query = "SELECT a FROM Tx a WHERE a.amount = :amount")
    , @NamedQuery(name = "Tx.FindByBalance", query = "SELECT a FROM Tx a WHERE a.balance = :balance")
    , @NamedQuery(name = "Tx.FindByDescription", query = "SELECT a FROM Tx a WHERE a.description = :description")
    , @NamedQuery(name = "Tx.FindTxsByDates", query = "SELECT t FROM Tx t JOIN t.account a WHERE a.id = :accountId "
    + "AND t.timeStamp BETWEEN :startDate AND :endDate")
})
public class Tx implements java.io.Serializable {
    @JoinColumn(name = "ACCOUNT_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "TIME_STAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @TableGenerator(name = "txIdGen", table = "BANK_SEQUENCE_GENERATOR", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "TX_ID", initialValue = 100, allocationSize = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "txIdGen")
    @Column(name = "TX_ID", nullable = false)
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;

    /** Creates a new instance of Tx */
    public Tx() {
    }

    public Tx(
        Account account,
        Date timeStamp,
        BigDecimal amount,
        BigDecimal balance,
        String description) {
        this.account = account;
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long txId) {
        this.id = txId;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account accountId) {
        this.account = accountId;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof Tx && id.equals(((Tx) object).getId());
    }

    public String toString() {
        //TODO change toString() implementation to return a better display name
        return "" + this.id;
    }
}
