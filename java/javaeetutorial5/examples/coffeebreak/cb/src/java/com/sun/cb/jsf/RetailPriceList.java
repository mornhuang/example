/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.jsf;

import com.sun.cb.ws.server.PriceItemBean;
import com.sun.cb.ws.server.PriceListBean;
import com.sun.cb.common.URLHelper;
import com.sun.cb.saaj.PriceListRequest;
import com.sun.cb.ws.client.PriceFetcher;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.*;


public class RetailPriceList implements Serializable {
    static final Logger logger = Logger.getLogger(
                "com.sun.cb.jsf.RetailPriceList");
    private List<RetailPriceItem> retailPriceItems;
    private List<String> suppliers;

    public RetailPriceList() {
        retailPriceItems = new ArrayList<RetailPriceItem>();
        suppliers = new ArrayList<String>();

        // Display organization information
        try {
            String supplier = URLHelper.getEndpointURL();

            // Get price list from service at supplier URI
            PriceListBean priceList = PriceFetcher.getPriceList(supplier);

            BigDecimal price = new BigDecimal("0.00");

            List<PriceItemBean> items = priceList.getPriceItems();
            Iterator<PriceItemBean> i = items.iterator();

            while (i.hasNext()) {
                PriceItemBean priceItem = i.next();
                price = priceItem.getPricePerPound()
                                 .multiply(new BigDecimal("1.35"))
                                 .setScale(2, BigDecimal.ROUND_HALF_UP);

                RetailPriceItem pi = new RetailPriceItem(
                            priceItem.getCoffeeName(),
                            priceItem.getPricePerPound(),
                            price,
                            supplier);
                retailPriceItems.add(pi);
            }

            suppliers.add(supplier);
        } catch (Exception e) {
            logger.severe("RetailPriceList: Exception: " + e.toString());
        }

        String SAAJPriceListURL = URLHelper.getSaajURL() + "/getPriceList";
        String SAAJOrderURL = URLHelper.getSaajURL() + "/orderCoffee";
        PriceListRequest plr = new PriceListRequest(SAAJPriceListURL);
        PriceListBean priceList = plr.getPriceList();
        ;

        List<PriceItemBean> priceItems = priceList.getPriceItems();
        Iterator<PriceItemBean> i = priceItems.iterator();

        while (i.hasNext()) {
            PriceItemBean pib = i.next();
            BigDecimal price = pib.getPricePerPound()
                                  .multiply(new BigDecimal("1.35"))
                                  .setScale(2, BigDecimal.ROUND_HALF_UP);
            RetailPriceItem rpi = new RetailPriceItem(
                        pib.getCoffeeName(),
                        pib.getPricePerPound(),
                        price,
                        SAAJOrderURL);
            retailPriceItems.add(rpi);
        }

        suppliers.add(SAAJOrderURL);
    }

    public List<RetailPriceItem> getItems() {
        return retailPriceItems;
    }

    public List<String> getSuppliers() {
        return suppliers;
    }

    public void setItems(List<RetailPriceItem> priceItems) {
        this.retailPriceItems = priceItems;
    }

    public void setSuppliers(List<String> suppliers) {
        this.suppliers = suppliers;
    }
}
