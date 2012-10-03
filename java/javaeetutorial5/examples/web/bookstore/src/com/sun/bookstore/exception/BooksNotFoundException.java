/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore.exception;


/** This application exception indicates that books
 *  have not been found.
 */
public class BooksNotFoundException extends Exception {
    public BooksNotFoundException() {
    }

    public BooksNotFoundException(String msg) {
        super(msg);
    }
}
