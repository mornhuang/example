/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.client;

import com.sun.cb.ws.server.PriceListBean;
import com.sun.cb.ws.server.Supplier;
import com.sun.cb.ws.server.SupplierService;
import javax.xml.ws.WebServiceRef;


public final class PriceFetcher {
    //    @WebServiceRef(wsdlLocation="http://localhost:8080/jaxws-coffee-supplier/SupplierService?WSDL")
    //    static SupplierService service;
    public static final PriceListBean getPriceList(String endpoint) {
        PriceListBean result = null;

        try {
            //            Supplier port = service.getSupplierPort();
            Supplier port = new SupplierService()
                .getSupplierPort();
            result = (PriceListBean) port.getPriceList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    } // getPriceList    
} // class
