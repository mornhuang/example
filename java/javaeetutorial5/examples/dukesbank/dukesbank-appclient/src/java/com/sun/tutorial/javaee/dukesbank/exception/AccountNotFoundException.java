/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This application exception indicates that an Account
 *  entity has not been found.
 */
@ApplicationException
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(Long msg) {
        super(msg.toString());
    }
}
