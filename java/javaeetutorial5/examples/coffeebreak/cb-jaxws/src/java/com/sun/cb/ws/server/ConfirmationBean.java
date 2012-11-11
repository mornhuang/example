/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import java.io.Serializable;
import java.util.*;


public class ConfirmationBean implements Serializable {
    private Calendar shippingDate;
    private String orderId;

    public ConfirmationBean() {
    }

    public ConfirmationBean(
        String orderId,
        Calendar shippingDate) {
        this.orderId = orderId;
        this.shippingDate = shippingDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Calendar getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Calendar shippingDate) {
        this.shippingDate = shippingDate;
    }
}
