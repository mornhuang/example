/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import com.sun.cb.ws.server.PriceItemBean;
import java.io.Serializable;
import java.util.*;


public class PriceListBean implements Serializable {
    private Calendar endDate;
    private Calendar startDate;
    private PriceItemBean[] priceItems;

    public PriceListBean() {
    }

    public PriceListBean(
        Calendar startDate,
        Calendar endDate,
        PriceItemBean[] priceItems) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceItems = priceItems;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar date) {
        this.startDate = date;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar date) {
        this.endDate = date;
    }

    public PriceItemBean[] getPriceItems() {
        return priceItems;
    }

    public void setPriceItems(PriceItemBean[] priceItems) {
        this.priceItems = priceItems;
    }
}
