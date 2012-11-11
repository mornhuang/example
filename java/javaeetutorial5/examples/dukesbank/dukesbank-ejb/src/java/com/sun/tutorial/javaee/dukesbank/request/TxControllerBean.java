/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.request;

import com.sun.tutorial.javaee.dukesbank.entity.Account;
import com.sun.tutorial.javaee.dukesbank.entity.Tx;
import com.sun.tutorial.javaee.dukesbank.exception.AccountNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.IllegalAccountTypeException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientCreditException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientFundsException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.exception.TxNotFoundException;
import com.sun.tutorial.javaee.dukesbank.util.Debug;
import com.sun.tutorial.javaee.dukesbank.util.DomainUtil;
import com.sun.tutorial.javaee.dukesbank.util.TxDetails;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@DeclareRoles("bankCustomer")
@Stateful
public class TxControllerBean implements TxController {
    private BigDecimal bigZero = new BigDecimal("0.00");
    @PersistenceContext
    private EntityManager em;

    @RolesAllowed("bankCustomer")
    public List<TxDetails> getTxsOfAccount(
        Date startDate,
        Date endDate,
        Long accountId) throws InvalidParameterException {
        Debug.print("TxControllerBean  getTxsOfAccount");

        List<Tx> txs;

        if (startDate == null) {
            throw new InvalidParameterException("null startDate");
        }

        if (endDate == null) {
            throw new InvalidParameterException("null endDate");
        }

        if (accountId == null) {
            throw new InvalidParameterException("null accountId");
        }

        try {
            txs = (List<Tx>) em.createNamedQuery("Tx.FindTxsByDates")
                               .setParameter("startDate", startDate)
                               .setParameter("endDate", endDate)
                               .setParameter("accountId", accountId)
                               .getResultList();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }

        return copyTxsToDetails(txs);
    } // getTxsOfAccount

    @PermitAll
    public TxDetails getDetails(Long txId)
        throws TxNotFoundException, InvalidParameterException {
        Debug.print("TxControllerBean getDetails");

        TxDetails details;

        if (txId == null) {
            throw new InvalidParameterException("null txId");
        }

        try {
            Tx tx = em.find(Tx.class, txId);
            details = new TxDetails(
                        tx.getId(),
                        tx.getTimeStamp(),
                        tx.getAmount(),
                        tx.getBalance(),
                        tx.getDescription());
        } catch (Exception ex) {
            throw new TxNotFoundException(txId);
        }

        return details;
    } // getDetails

    @RolesAllowed("bankCustomer")
    public void withdraw(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException, InsufficientFundsException {
        Debug.print("TxControllerBean withdraw");

        Account account = checkAccountArgs(amount, description, accountId);

        String type = account.getType();

        if (DomainUtil.isCreditAccount(type)) {
            throw new IllegalAccountTypeException(type);
        }

        BigDecimal newBalance = account.getBalance()
                                       .subtract(amount);

        if (newBalance.compareTo(bigZero) == -1) {
            throw new InsufficientFundsException();
        }

        executeTx(
            amount.negate(),
            description,
            newBalance,
            account);
    }

    @RolesAllowed("bankCustomer")
    public void deposit(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException {
        Debug.print("TxControllerBean deposit");

        Account account = checkAccountArgs(amount, description, accountId);

        String type = account.getType();

        if (DomainUtil.isCreditAccount(type)) {
            throw new IllegalAccountTypeException(type);
        }

        BigDecimal newBalance = account.getBalance()
                                       .add(amount);
        executeTx(amount, description, newBalance, account);
    }

    @RolesAllowed("bankCustomer")
    public void makeCharge(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException, InsufficientCreditException {
        Debug.print("TxControllerBean charge");

        Account account = checkAccountArgs(amount, description, accountId);

        String type = account.getType();

        if (DomainUtil.isCreditAccount(type) == false) {
            throw new IllegalAccountTypeException(type);
        }

        BigDecimal newBalance = account.getBalance()
                                       .add(amount);

        if (newBalance.compareTo(account.getCreditLine()) == 1) {
            throw new InsufficientCreditException();
        }

        executeTx(amount, description, newBalance, account);
    }

    @RolesAllowed("bankCustomer")
    public void makePayment(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException,
            IllegalAccountTypeException {
        Debug.print("TxControllerBean makePayment");

        Account account = checkAccountArgs(amount, description, accountId);

        String type = account.getType();

        if (DomainUtil.isCreditAccount(type) == false) {
            throw new IllegalAccountTypeException(type);
        }

        BigDecimal newBalance = account.getBalance()
                                       .subtract(amount);
        executeTx(amount, description, newBalance, account);
    }

    @RolesAllowed("bankCustomer")
    public void transferFunds(
        BigDecimal amount,
        String description,
        Long fromAccountId,
        Long toAccountId)
        throws InvalidParameterException, AccountNotFoundException,
            InsufficientFundsException, InsufficientCreditException {
        Account fromAccount = checkAccountArgs(
                    amount,
                    description,
                    fromAccountId);
        Account toAccount = checkAccountArgs(amount, description, toAccountId);

        String fromType = fromAccount.getType();
        BigDecimal fromBalance = fromAccount.getBalance();

        if (DomainUtil.isCreditAccount(fromType)) {
            BigDecimal fromNewBalance = fromBalance.add(amount);

            if (fromNewBalance.compareTo(fromAccount.getCreditLine()) == 1) {
                throw new InsufficientCreditException();
            }

            executeTx(amount, description, fromNewBalance, fromAccount);
        } else {
            BigDecimal fromNewBalance = fromBalance.subtract(amount);

            if (fromNewBalance.compareTo(bigZero) == -1) {
                throw new InsufficientFundsException();
            }

            executeTx(
                    amount.negate(),
                    description,
                    fromNewBalance,
                    fromAccount);
        }

        String toType = toAccount.getType();
        BigDecimal toBalance = toAccount.getBalance();

        if (DomainUtil.isCreditAccount(toType)) {
            BigDecimal toNewBalance = toBalance.subtract(amount);
            executeTx(
                amount.negate(),
                description,
                toNewBalance,
                toAccount);
        } else {
            BigDecimal toNewBalance = toBalance.add(amount);
            executeTx(amount, description, toNewBalance, toAccount);
        }
    } // transferFunds

    // private methods
    private void executeTx(
        BigDecimal amount,
        String description,
        BigDecimal newBalance,
        Account account) {
        Debug.print("TxControllerBean executeTx");

        Tx tx = null;

        // Set the new balance and create a new transaction       
        try {
            account.setBalance(newBalance);
            tx = new Tx(
                        account,
                        new java.util.Date(),
                        amount,
                        newBalance,
                        description);
            em.persist(tx);
        } catch (Exception ex) {
            throw new EJBException("executeTx: " + ex.getMessage());
        }
    }

    private Account checkAccountArgs(
        BigDecimal amount,
        String description,
        Long accountId)
        throws InvalidParameterException, AccountNotFoundException {
        Account account = null;

        if (description == null) {
            throw new InvalidParameterException("null description");
        }

        if (accountId == null) {
            throw new InvalidParameterException("null accountId");
        }

        if (amount.compareTo(bigZero) != 1) {
            throw new InvalidParameterException("amount <= 0");
        }

        try {
            account = em.find(Account.class, accountId);
        } catch (Exception ex) {
            throw new AccountNotFoundException(accountId);
        }

        return account;
    } // checkAccountArgs

    private List<TxDetails> copyTxsToDetails(List<Tx> txs) {
        List<TxDetails> detailsList = new ArrayList<TxDetails>();
        Iterator<Tx> i = txs.iterator();

        try {
            while (i.hasNext()) {
                Tx tx = (Tx) i.next();
                TxDetails txDetails = new TxDetails(
                            tx.getId(),
                            tx.getTimeStamp(),
                            tx.getAmount(),
                            tx.getBalance(),
                            tx.getDescription());
                detailsList.add(txDetails);
            }
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }

        return detailsList;
    }
}
