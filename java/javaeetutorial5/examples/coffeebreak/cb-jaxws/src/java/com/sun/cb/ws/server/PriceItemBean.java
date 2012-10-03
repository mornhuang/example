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


public class PriceItemBean implements Serializable {
    private BigDecimal pricePerPound;
    private String coffeeName;

    public PriceItemBean() {
    }

    public PriceItemBean(
        String coffeeName,
        BigDecimal pricePerPound) {
        this.coffeeName = coffeeName;
        this.pricePerPound = pricePerPound;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public BigDecimal getPricePerPound() {
        return pricePerPound;
    }

    public void setPricePerPound(BigDecimal pricePerPound) {
        this.pricePerPound = pricePerPound;
    }
}
