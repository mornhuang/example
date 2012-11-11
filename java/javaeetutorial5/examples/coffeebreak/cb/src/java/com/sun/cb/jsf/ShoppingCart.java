/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.util.*;
import java.math.BigDecimal;


public class ShoppingCart {
    BigDecimal total = new BigDecimal("0.00");
    List<ShoppingCartItem> items = null;
    int numberOfItems = 0;

    public ShoppingCart(RetailPriceList rpl) {
        items = new ArrayList<ShoppingCartItem>();

        for (Iterator i = rpl.getItems()
                             .iterator(); i.hasNext();) {
            RetailPriceItem item = (RetailPriceItem) i.next();
            ShoppingCartItem sci = new ShoppingCartItem(
                        item,
                        new BigDecimal("0.0"),
                        new BigDecimal("0.00"));
            items.add(sci);
            numberOfItems++;
        }
    }

    public synchronized void add(ShoppingCartItem item) {
        items.add(item);
        total = total.add(item.getPrice())
                     .setScale(2);
        numberOfItems++;
    }

    public synchronized int getNumberOfItems() {
        return numberOfItems;
    }

    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }

    protected void finalize() throws Throwable {
        items.clear();
    }

    public void setTotal(BigDecimal newTotal) {
        this.total = newTotal;
    }

    public synchronized BigDecimal getTotal() {
        return total;
    }

    public synchronized void clear() {
        numberOfItems = 0;
        total = new BigDecimal("0.00");
        items.clear();
    }
}
