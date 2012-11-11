/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import com.sun.cb.common.DateHelper;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class Supplier {
    @WebMethod
    public ConfirmationBean placeOrder(OrderBean order) {
        Date tomorrow = DateHelper.addDays(
                new Date(),
                1);
        ConfirmationBean confirmation = new ConfirmationBean(
                    order.getId(),
                    DateHelper.dateToCalendar(tomorrow));

        return confirmation;
    }

    @WebMethod
    public PriceListBean getPriceList() {
        PriceListBean priceList = loadPrices();

        return priceList;
    }

    private PriceListBean loadPrices() {
        String propsName = "com.sun.cb.ws.server.SupplierPrices";
        Date today = new Date();
        Date endDate = DateHelper.addDays(today, 30);

        PriceItemBean[] priceItems = PriceLoader.loadItems(propsName);
        PriceListBean priceList = new PriceListBean(
                    DateHelper.dateToCalendar(today),
                    DateHelper.dateToCalendar(endDate),
                    priceItems);

        return priceList;
    }
}
