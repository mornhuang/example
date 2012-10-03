/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.saaj;

import com.sun.cb.ws.server.ConfirmationBean;
import com.sun.cb.ws.server.LineItemBean;
import com.sun.cb.ws.server.OrderBean;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import java.net.URL;
import java.util.Iterator;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.datatype.DatatypeFactory;


public class OrderRequest {
    String url;

    public OrderRequest(String url) {
        this.url = url;
    }

    public ConfirmationBean placeOrder(OrderBean orderBean) {
        ConfirmationBean cb = null;

        try {
            SOAPConnectionFactory scf = SOAPConnectionFactory
                .newInstance();
            SOAPConnection con = scf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage msg = mf.createMessage();

            // Access the SOAPBody object
            SOAPBody body = msg.getSOAPBody();

            // Create the appropriate elements and add them
            QName bodyName = new QName(
                        "http://sonata.coffeebreak.com",
                        "coffee-order",
                        "PO");
            SOAPBodyElement order = body.addBodyElement(bodyName);

            // orderID
            QName orderIDName = new QName("orderID");
            SOAPElement orderID = order.addChildElement(orderIDName);
            orderID.addTextNode(orderBean.getId());

            // customer
            QName childName = new QName("customer");
            SOAPElement customer = order.addChildElement(childName);

            childName = new QName("last-name");

            SOAPElement lastName = customer.addChildElement(childName);
            lastName.addTextNode(orderBean.getCustomer().getLastName());

            childName = new QName("first-name");

            SOAPElement firstName = customer.addChildElement(childName);
            firstName.addTextNode(orderBean.getCustomer().getFirstName());

            childName = new QName("phone-number");

            SOAPElement phoneNumber = customer.addChildElement(childName);
            phoneNumber.addTextNode(orderBean.getCustomer().getPhoneNumber());

            childName = new QName("email-address");

            SOAPElement emailAddress = customer.addChildElement(childName);
            emailAddress.addTextNode(orderBean.getCustomer().getEmailAddress());

            // address
            childName = new QName("address");

            SOAPElement address = order.addChildElement(childName);

            childName = new QName("street");

            SOAPElement street = address.addChildElement(childName);
            street.addTextNode(orderBean.getAddress().getStreet());

            childName = new QName("city");

            SOAPElement city = address.addChildElement(childName);
            city.addTextNode(orderBean.getAddress().getCity());

            childName = new QName("state");

            SOAPElement state = address.addChildElement(childName);
            state.addTextNode(orderBean.getAddress().getState());

            childName = new QName("zip");

            SOAPElement zip = address.addChildElement(childName);
            zip.addTextNode(orderBean.getAddress().getZip());

            List<LineItemBean> lineItems = orderBean.getLineItems();
            Iterator<LineItemBean> i = lineItems.iterator();

            while (i.hasNext()) {
                LineItemBean lib = i.next();
                childName = new QName("line-item");

                SOAPElement lineItem = order.addChildElement(childName);

                childName = new QName("coffeeName");

                SOAPElement coffeeName = lineItem.addChildElement(childName);
                coffeeName.addTextNode(lib.getCoffeeName());

                childName = new QName("pounds");

                SOAPElement pounds = lineItem.addChildElement(childName);
                pounds.addTextNode(lib.getPounds().toString());

                childName = new QName("price");

                SOAPElement price = lineItem.addChildElement(childName);
                price.addTextNode(lib.getPrice().toString());
            }

            // total
            childName = new QName("total");

            SOAPElement total = order.addChildElement(childName);
            total.addTextNode(orderBean.getTotal().toString());

            URL endpoint = new URL(url);
            SOAPMessage reply = con.call(msg, endpoint);
            con.close();

            // Extract content of reply
            // Extract order ID and ship date
            SOAPBody sBody = reply.getSOAPBody();
            Iterator bodyIt = sBody.getChildElements();
            SOAPBodyElement sbEl = (SOAPBodyElement) bodyIt.next();
            Iterator bodyIt2 = sbEl.getChildElements();

            // Get orderID
            SOAPElement ID = (SOAPElement) bodyIt2.next();
            String id = ID.getValue();

            // Get ship date
            SOAPElement sDate = (SOAPElement) bodyIt2.next();
            String shippingDate = sDate.getValue();
            SimpleDateFormat df = new SimpleDateFormat(
                        "EEE MMM dd HH:mm:ss z yyyy");
            Date date = df.parse(shippingDate);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            cb = new ConfirmationBean();
            cb.setOrderId(id);
            cb.setShippingDate(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cb;
    }
}
