/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import java.io.Serializable;
import java.lang.Number;
import java.util.logging.*;


/**
 * <p>JavaBean representing the data for an individual customer.</p>
 *
 */
public class CoffeeBean implements Serializable {
    static final Logger logger = Logger.getLogger(
                "server-jsf.com.sun.cb.CoffeeBean");
    private String coffeeName = null;
    private double pricePerPound = 0.0;
    private double total = 0.0;

    public CoffeeBean(
        String coffeeName,
        double pricePerPound) {
        logger.info("Created CoffeeBean");
        this.coffeeName = coffeeName;
        this.pricePerPound = pricePerPound;
    }

    public String getCoffeeName() {
        return (this.coffeeName);
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public double getPricePerPound() {
        return (this.pricePerPound);
    }

    public void setPricePerPound(double pricePerPound) {
        this.pricePerPound = pricePerPound;
    }

    public double getTotal() {
        return (this.total);
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
