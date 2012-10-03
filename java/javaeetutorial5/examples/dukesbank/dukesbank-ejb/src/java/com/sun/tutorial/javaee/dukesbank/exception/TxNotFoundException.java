/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.tutorial.javaee.dukesbank.exception;

import javax.ejb.ApplicationException;


/** This class application exception indicates that
 *  a Tx entity has not been found.
 */
@ApplicationException
public class TxNotFoundException extends Exception {
    public TxNotFoundException() {
    }

    public TxNotFoundException(Long msg) {
        super(msg.toString());
    }
}
