/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.util;

public class IncorrectSportException extends java.lang.Exception {
    /**
     * Creates a new instance of <code>IncorrectSportException</code> without detail message.
     */
    public IncorrectSportException() {
    }

    /**
     * Constructs an instance of <code>IncorrectSportException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IncorrectSportException(String msg) {
        super(msg);
    }
}
