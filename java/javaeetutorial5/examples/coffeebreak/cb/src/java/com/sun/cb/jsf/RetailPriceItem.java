/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.io.Serializable;
import java.math.BigDecimal;


public class RetailPriceItem implements Serializable {
    private BigDecimal retailPricePerPound;
    private BigDecimal wholesalePricePerPound;
    private String coffeeName;
    private String supplier;

    public RetailPriceItem() {
        this.coffeeName = null;
        this.wholesalePricePerPound = new BigDecimal("0.00");
        this.retailPricePerPound = new BigDecimal("0.00");
        this.supplier = null;
    }

    public RetailPriceItem(
        String coffeeName,
        BigDecimal wholesalePricePerPound,
        BigDecimal retailPricePerPound,
        String supplier) {
        this.coffeeName = coffeeName;
        this.wholesalePricePerPound = wholesalePricePerPound;
        this.retailPricePerPound = retailPricePerPound;
        this.supplier = supplier;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public BigDecimal getWholesalePricePerPound() {
        return wholesalePricePerPound;
    }

    public BigDecimal getRetailPricePerPound() {
        return retailPricePerPound;
    }

    public void setRetailPricePerPound(BigDecimal retailPricePerPound) {
        this.retailPricePerPound = retailPricePerPound;
    }

    public void setWholesalePricePerPound(BigDecimal wholesalePricePerPound) {
        this.wholesalePricePerPound = wholesalePricePerPound;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
