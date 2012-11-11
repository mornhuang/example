/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.saaj;

import com.sun.cb.common.URLHelper;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import java.net.URL;
import java.util.Iterator;
import javax.xml.namespace.QName;


public class TestOrderRequest {
    public static void main(String[] args) {
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
            orderID.addTextNode("1234");

            // customer
            QName childName = new QName("customer");
            SOAPElement customer = order.addChildElement(childName);

            childName = new QName("last-name");

            SOAPElement lastName = customer.addChildElement(childName);
            lastName.addTextNode("Pental");

            childName = new QName("first-name");

            SOAPElement firstName = customer.addChildElement(childName);
            firstName.addTextNode("Ragni");

            childName = new QName("phone-number");

            SOAPElement phoneNumber = customer.addChildElement(childName);
            phoneNumber.addTextNode("908 983-6789");

            childName = new QName("email-address");

            SOAPElement emailAddress = customer.addChildElement(childName);
            emailAddress.addTextNode("ragnip@aol.com");

            // address
            childName = new QName("address");

            SOAPElement address = order.addChildElement(childName);

            childName = new QName("street");

            SOAPElement street = address.addChildElement(childName);
            street.addTextNode("9876 Central Way");

            childName = new QName("city");

            SOAPElement city = address.addChildElement(childName);
            city.addTextNode("Rainbow");

            childName = new QName("state");

            SOAPElement state = address.addChildElement(childName);
            state.addTextNode("CA");

            childName = new QName("zip");

            SOAPElement zip = address.addChildElement(childName);
            zip.addTextNode("99999");

            // line-item 1
            childName = new QName("line-item");

            SOAPElement lineItem = order.addChildElement(childName);

            childName = new QName("coffeeName");

            SOAPElement coffeeName = lineItem.addChildElement(childName);
            coffeeName.addTextNode("arabica");

            childName = new QName("pounds");

            SOAPElement pounds = lineItem.addChildElement(childName);
            pounds.addTextNode("2");

            childName = new QName("price");

            SOAPElement price = lineItem.addChildElement(childName);
            price.addTextNode("10.95");

            // line-item 2
            childName = new QName("coffee-name");

            SOAPElement coffeeName2 = lineItem.addChildElement(childName);
            coffeeName2.addTextNode("espresso");

            childName = new QName("pounds");

            SOAPElement pounds2 = lineItem.addChildElement(childName);
            pounds2.addTextNode("3");

            childName = new QName("price");

            SOAPElement price2 = lineItem.addChildElement(childName);
            price2.addTextNode("10.95");

            // total
            childName = new QName("total");

            SOAPElement total = order.addChildElement(childName);
            total.addTextNode("21.90");

            URL endpoint = new URL(URLHelper.getSaajURL() + "/orderCoffee");
            SOAPMessage reply = con.call(msg, endpoint);
            con.close();

            // extract content of reply
            // Extracting order ID and ship date
            SOAPBody sBody = reply.getSOAPBody();
            Iterator bodyIt = sBody.getChildElements();
            SOAPBodyElement sbEl = (SOAPBodyElement) bodyIt.next();
            Iterator bodyIt2 = sbEl.getChildElements();

            // get orderID
            SOAPElement ID = (SOAPElement) bodyIt2.next();
            String id = ID.getValue();

            // get ship date
            SOAPElement sDate = (SOAPElement) bodyIt2.next();
            String shippingDate = sDate.getValue();

            System.out.println("");
            System.out.println("");
            System.out.println("Confirmation for order #" + id);
            System.out.print("Your order will be shipped on ");
            System.out.println(shippingDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
