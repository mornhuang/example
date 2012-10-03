/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.util;

import java.text.NumberFormat;
import java.util.*;


public class Currency {
    private Locale locale;
    private double amount;

    public Currency() {
        locale = null;

        amount = 0.0;
    }

    public synchronized void setLocale(Locale l) {
        locale = l;
    }

    public synchronized void setAmount(double a) {
        amount = a;
    }

    public synchronized String getFormat() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

        return nf.format(amount);
    }
}
