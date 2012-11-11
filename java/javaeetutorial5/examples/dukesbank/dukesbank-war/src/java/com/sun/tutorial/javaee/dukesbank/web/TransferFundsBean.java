/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import javax.faces.context.FacesContext;
import java.math.BigDecimal;
import com.sun.tutorial.javaee.dukesbank.exception.AccountNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientCreditException;
import com.sun.tutorial.javaee.dukesbank.exception.InsufficientFundsException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.util.AccountDetails;
import com.sun.tutorial.javaee.dukesbank.util.Debug;
import com.sun.tutorial.javaee.dukesbank.web.Util.Navigation;


public class TransferFundsBean {
    private AccountDetails fromAccount;
    private AccountDetails toAccount;
    private BigDecimal transferAmount;
    private CustomerBean customer;
    private Long customerId;
    private Long fromAccountId;
    private Long toAccountId;

    public boolean doTx() {
        try {
            customer.getTxController()
                    .transferFunds(
                    transferAmount,
                    "Transfer",
                    fromAccountId,
                    toAccountId);
        } catch (InvalidParameterException e) {
            // Not possible
        } catch (AccountNotFoundException e) {
            // Not possible
        } catch (InsufficientFundsException e) {
            Util.addErrorMessage(
                    FacesContext.getCurrentInstance(),
                    null,
                    "InsufficientFundsError");

            return false;
        } catch (InsufficientCreditException e) {
            Util.addErrorMessage(
                    FacesContext.getCurrentInstance(),
                    null,
                    "InsufficientCreditError");

            return false;
        }

        return true;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public AccountDetails getFromAccount() {
        if (fromAccountId != null) {
            customer.setActiveAccount(fromAccountId);
            fromAccount = customer.getAccountDetails();
        }

        return fromAccount;
    }

    public AccountDetails getToAccount() {
        if (toAccountId != null) {
            customer.setActiveAccount(toAccountId);
            toAccount = customer.getAccountDetails();
        }

        return toAccount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
        Debug.print("TFB: Setting transfer amount to: " + transferAmount);
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
        Debug.print("TFB: Setting from account id to: " + fromAccountId);
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
        Debug.print("TFB: Setting to account id to: " + toAccountId);
    }

    public void setFromAccount(AccountDetails fromAccount) {
        this.fromAccount = fromAccount;
        Debug.print("TFB: Setting from account to: " + fromAccount);
    }

    public void setToAccount(AccountDetails toAccount) {
        this.toAccount = toAccount;
        Debug.print("TFB: Setting to account to: " + toAccount);
    }

    public Object submit() {
        if (fromAccountId.equals(toAccountId)) {
            Util.addErrorMessage(
                    FacesContext.getCurrentInstance(),
                    null,
                    "SameAccounts");

            return Navigation.transferFunds;
        }

        if (doTx()) {
            return Navigation.transferAck;
        } else {
            return null;
        }
    }
}
