/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore.exception;


/** This application exception indicates that an order cannot be completed.
 */
public class OrderException extends Exception {
    public OrderException() {
    }

    public OrderException(String msg) {
        super(msg);
    }
}
