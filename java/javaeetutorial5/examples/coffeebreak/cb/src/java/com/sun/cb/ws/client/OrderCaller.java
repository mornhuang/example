/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.client;

import com.sun.cb.ws.server.ConfirmationBean;
import com.sun.cb.ws.server.OrderBean;
import com.sun.cb.ws.server.Supplier;
import com.sun.cb.ws.server.SupplierService;
import javax.xml.ws.WebServiceRef;


public class OrderCaller {
    //    @WebServiceRef(wsdlLocation="http://localhost:8080/jaxws-coffee-supplier/SupplierService?WSDL")
    //    private SupplierService service;    
    public OrderCaller(String endpoint) {
    }

    public ConfirmationBean placeOrder(OrderBean order) {
        ConfirmationBean result = null;

        try {
            //            Supplier supplier = service.getSupplierPort();
            Supplier supplier = new SupplierService()
                .getSupplierPort();
            result = supplier.placeOrder(order);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
// class
