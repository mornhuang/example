/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.client;

import com.sun.cb.common.DateHelper;
import com.sun.cb.ws.server.PriceItemBean;
import com.sun.cb.ws.server.PriceListBean;
import com.sun.cb.common.URLHelper;
import java.util.Iterator;
import java.util.List;


public class TestPriceFetcher {
    public static void main(String[] args) {
        try {
            PriceListBean priceList = PriceFetcher.getPriceList(
                        URLHelper.getEndpointURL());
            System.out.println(URLHelper.getEndpointURL());
            System.out.println(
                    DateHelper.format(
                            priceList.getStartDate().toGregorianCalendar(),
                            "MM/dd/yy") + " "
                    + DateHelper.format(
                            priceList.getEndDate().toGregorianCalendar(),
                            "MM/dd/yy"));

            List<PriceItemBean> items = priceList.getPriceItems();
            Iterator<PriceItemBean> i = items.iterator();

            while (i.hasNext()) {
                PriceItemBean pib = i.next();
                System.out.println(
                        pib.getCoffeeName() + " " + pib.getPricePerPound());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
