/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import com.sun.cb.ws.server.ConfirmationBean;
import com.sun.cb.ws.server.OrderBean;


public class OrderConfirmation {
    private ConfirmationBean cb;
    private OrderBean ob;

    public OrderConfirmation(
        OrderBean ob,
        ConfirmationBean cb) {
        this.ob = ob;
        this.cb = cb;
    }

    public OrderBean getOrderBean() {
        return ob;
    }

    public ConfirmationBean getConfirmationBean() {
        return cb;
    }
}
