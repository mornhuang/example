/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package mypkg;

import java.text.DateFormat;
import java.util.*;


public class MyDate {
    Date today;
    DateFormat dateFormatter;

    public MyDate() {
        today = new Date();
    }

    public String getDate() {
        return dateFormatter.format(today);
    }

    public void setLocale(Locale l) {
        dateFormatter = DateFormat.getDateInstance(DateFormat.FULL, l);
    }
}
