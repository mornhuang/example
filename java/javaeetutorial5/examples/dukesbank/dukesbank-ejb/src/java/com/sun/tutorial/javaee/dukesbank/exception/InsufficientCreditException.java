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
 *  the credit line of an account is not large
 *  enough to perform an operation.
 */
@ApplicationException
public class InsufficientCreditException extends Exception {
    public InsufficientCreditException() {
    }

    public InsufficientCreditException(String msg) {
        super(msg);
    }
}
