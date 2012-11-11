/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.server;

import com.sun.cb.ws.server.PriceItemBean;
import java.math.BigDecimal;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public final class PriceLoader {
    public static final PriceItemBean[] loadItems(String propsName) {
        ResourceBundle priceBundle = ResourceBundle.getBundle(propsName);

        Enumeration bundleKeys = priceBundle.getKeys();
        List<String> keyList = new ArrayList<String>();

        while (bundleKeys.hasMoreElements()) {
            String key = (String) bundleKeys.nextElement();
            String value = priceBundle.getString(key);
            keyList.add(value);
        }

        PriceItemBean[] items = (PriceItemBean[]) Array.newInstance(
                    PriceItemBean.class,
                    keyList.size());
        int k = 0;

        for (Iterator it = keyList.iterator(); it.hasNext();) {
            String s = (String) it.next();
            int commaIndex = s.indexOf(",");
            String name = s.substring(0, commaIndex)
                           .trim();
            String price = s.substring(
                    commaIndex + 1,
                    s.length())
                            .trim();
            items[k] = new PriceItemBean(
                    name,
                    new BigDecimal(price));
            k++;
        }

        return items;
    }
}
