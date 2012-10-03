/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This application exception indicates that a
 *  customer-account relationship already
 *  exists.  In other words, the customer
 *  has already been assigned to the account.
 */
@ApplicationException
public class CustomerInAccountException extends Exception {
    public CustomerInAccountException() {
    }

    public CustomerInAccountException(String msg) {
        super(msg);
    }
}
