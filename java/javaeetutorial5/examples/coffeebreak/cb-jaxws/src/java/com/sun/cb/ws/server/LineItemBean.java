/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import java.io.Serializable;
import java.math.BigDecimal;


public class LineItemBean implements Serializable {
    private BigDecimal pounds;
    private BigDecimal price;
    private String coffeeName;

    public LineItemBean() {
    }

    public LineItemBean(
        String coffeeName,
        BigDecimal pounds,
        BigDecimal price) {
        this.coffeeName = coffeeName;
        this.pounds = pounds;
        this.price = price;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public BigDecimal getPounds() {
        return pounds;
    }

    public void setPounds(BigDecimal pounds) {
        this.pounds = pounds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
