/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cart.mutual.ejb;

public class BookException extends Exception {
    public BookException() {
    }

    public BookException(String msg) {
        super(msg);
    }
}
