/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.request;

import com.sun.tutorial.javaee.dukesbank.exception.AccountNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.IllegalAccountTypeException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientCreditException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientFundsException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.exception.TxNotFoundException;
import com.sun.tutorial.javaee.dukesbank.util.TxDetails;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;


@Remote
public interface TxController {
    @RolesAllowed("bankCustomer")
    void deposit(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException;

    @PermitAll
    TxDetails getDetails(Long txId)
        throws TxNotFoundException, InvalidParameterException;

    @RolesAllowed("bankCustomer")
    List<TxDetails> getTxsOfAccount(
        Date startDate,
        Date endDate,
        Long accountId) throws InvalidParameterException;

    @RolesAllowed("bankCustomer")
    void makeCharge(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException, InsufficientCreditException;

    @RolesAllowed("bankCustomer")
    void makePayment(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException;

    @RolesAllowed("bankCustomer")
    void transferFunds(
        BigDecimal amount,
        String description,
        Long fromAccountId,
        Long toAccountId)
        throws InvalidParameterException, AccountNotFoundException,
            InsufficientFundsException, InsufficientCreditException;

    @RolesAllowed("bankCustomer")
    void withdraw(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException, InsufficientFundsException;
}
