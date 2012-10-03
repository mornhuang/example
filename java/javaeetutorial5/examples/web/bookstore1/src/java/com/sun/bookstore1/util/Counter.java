/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.util;

public class Counter {
    private int counter;

    public Counter() {
        counter = 0;
    }

    public synchronized int getCounter() {
        return counter;
    }

    public synchronized int setCounter(int c) {
        counter = c;

        return counter;
    }

    public synchronized int incCounter() {
        return (++counter);
    }
}
