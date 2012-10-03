/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.math.BigDecimal;


public class ShoppingCartItem {
    BigDecimal pounds;
    BigDecimal price;
    RetailPriceItem item;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(
        RetailPriceItem item,
        BigDecimal pounds,
        BigDecimal price) {
        this.item = item;
        this.pounds = pounds;
        this.price = price;
    }

    public void setItem(RetailPriceItem item) {
        this.item = item;
    }

    public void setPounds(BigDecimal newPounds) {
        this.pounds = newPounds;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RetailPriceItem getItem() {
        return item;
    }

    public BigDecimal getPounds() {
        return pounds;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
