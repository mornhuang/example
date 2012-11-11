/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This application exception indicates
 *  that at least one customer is required
 *  for an account.
 */
@ApplicationException
public class CustomerRequiredException extends Exception {
    public CustomerRequiredException() {
    }

    public CustomerRequiredException(String msg) {
        super(msg);
    }
}
