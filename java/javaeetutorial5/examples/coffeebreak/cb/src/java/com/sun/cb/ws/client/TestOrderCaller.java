/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.ws.client;

import com.sun.cb.ws.server.AddressBean;
import com.sun.cb.ws.server.ConfirmationBean;
import com.sun.cb.ws.server.CustomerBean;
import com.sun.cb.common.DateHelper;
import com.sun.cb.ws.server.LineItemBean;
import com.sun.cb.ws.server.OrderBean;
import com.sun.cb.common.URLHelper;
import java.math.BigDecimal;


public class TestOrderCaller {
    public static void main(String[] args) {
        try {
            AddressBean address = new AddressBean();
            address.setStreet("455 Apple Way");
            address.setCity("Santa Clara");
            address.setState("CA");
            address.setZip("95123");

            CustomerBean customer = new CustomerBean();
            customer.setFirstName("Buzz");
            customer.setLastName("Murphy");
            customer.setPhoneNumber("247-5566");
            customer.setEmailAddress("buzz.murphy@clover.com");

            LineItemBean itemA = new LineItemBean();
            itemA.setCoffeeName("mocha");
            itemA.setPounds(new BigDecimal("1.0"));
            itemA.setPrice(new BigDecimal("9.50"));

            LineItemBean itemB = new LineItemBean();
            itemB.setCoffeeName("special blend");
            itemB.setPounds(new BigDecimal("5.0"));
            itemB.setPrice(new BigDecimal("8.00"));

            LineItemBean itemC = new LineItemBean();
            itemC.setCoffeeName("wakeup call");
            itemC.setPounds(new BigDecimal("0.5"));
            itemC.setPrice(new BigDecimal("10.00"));

            LineItemBean[] lineItems = { itemA, itemB, itemC };

            OrderBean order = new OrderBean();
            order.setAddress(address);
            order.setCustomer(customer);
            order.setId("123");
            order.getLineItems()
                 .add(itemA);
            order.getLineItems()
                 .add(itemB);
            order.getLineItems()
                 .add(itemC);
            order.setTotal(new BigDecimal("55.67"));

            OrderCaller oc = new OrderCaller(URLHelper.getEndpointURL());
            ConfirmationBean confirmation = oc.placeOrder(order);

            System.out.println(
                    confirmation.getOrderId() + " "
                    + DateHelper.format(
                            confirmation.getShippingDate().toGregorianCalendar(),
                            "MM/dd/yy"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
