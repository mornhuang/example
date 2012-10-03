/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.web;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.sun.tutorial.javaee.dukesbank.exception.AccountNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.CustomerNotFoundException;
import com.sun.tutorial.javaee.dukesbank.exception.InvalidParameterException;
import com.sun.tutorial.javaee.dukesbank.request.AccountController;
import com.sun.tutorial.javaee.dukesbank.request.TxController;
import com.sun.tutorial.javaee.dukesbank.util.AccountDetails;
import com.sun.tutorial.javaee.dukesbank.util.Debug;
import com.sun.tutorial.javaee.dukesbank.web.Util.Navigation;


public class CustomerBean {
    @EJB
    private AccountController accountController;
    private Long account;
    private Long customerId;
    @EJB
    private TxController txController;

    public CustomerBean() {
        customerId = Long.parseLong(
                    FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
    }

    public TxController getTxController() {
        return txController;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setActiveAccount(Long account) {
        this.account = account;
    }

    public Long getActiveAccount() {
        return this.account;
    }

    public AccountDetails getAccountDetails() {
        AccountDetails ad = null;

        try {
            ad = accountController.getDetails(this.account);
        } catch (InvalidParameterException e) {
            Debug.print(e.getMessage());

            // Not possible
        } catch (AccountNotFoundException e) {
            Debug.print(e.getMessage());

            // Not possible
        }

        if (ad != null) {
            Debug.print(
                "account ID: ",
                ad.getAccountId());
        }

        return ad;
    }

    public List<AccountDetails> getAccounts() {
        List<AccountDetails> accounts = null;

        try {
            accounts = accountController.getAccountsOfCustomer(customerId);
        } catch (InvalidParameterException e) {
            Debug.print(e.getMessage());

            // Not possible
        } catch (CustomerNotFoundException e) {
            Debug.print(e.getMessage());

            // Not possible
        }

        return accounts;
    }

    public Object logout() {
        HttpSession session = (HttpSession) Util.getExternalContext()
                                                .getSession(true);
        session.invalidate();

        return Navigation.main;
    }
}
