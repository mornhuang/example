/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This an application exception is thrown
 *  when an illegal account type is detected.
 */
@ApplicationException
public class IllegalAccountTypeException extends Exception {
    public IllegalAccountTypeException() {
    }

    public IllegalAccountTypeException(String msg) {
        super(msg);
    }
}
