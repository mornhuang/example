/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This application exception indicates that
 *  an account has insufficient funds to perform
 *  an operation.
 */
@ApplicationException
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
