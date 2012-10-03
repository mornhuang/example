/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.util.*;


public class OrderConfirmations {
    List<OrderConfirmation> items = null;

    public OrderConfirmations() {
        items = new ArrayList<OrderConfirmation>();
    }

    public synchronized void add(OrderConfirmation oc) {
        items.add(oc);
    }

    public synchronized Collection getItems() {
        return items;
    }

    protected void finalize() throws Throwable {
        items.clear();
    }

    public synchronized void clear() {
        items.clear();
    }
}
