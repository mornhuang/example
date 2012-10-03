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
import java.util.Vector;
import java.net.URL;
import java.util.Iterator;
import javax.xml.namespace.QName;


public class TestPriceListRequest {
    public static void main(String[] args) {
        try {
            SOAPConnectionFactory scf = SOAPConnectionFactory
                .newInstance();
            SOAPConnection con = scf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage msg = mf.createMessage();

            // Access the SOAPBody object
            SOAPBody body = msg.getSOAPBody();

            // create SOAPBodyElement request 
            QName bodyName = new QName(
                        "http://sonata.coffeebreak.com",
                        "request-prices",
                        "RequestPrices");
            SOAPBodyElement requestPrices = body.addBodyElement(bodyName);

            QName requestName = new QName("request");
            SOAPElement request = requestPrices.addChildElement(requestName);
            request.addTextNode("Send updated price list.");

            msg.saveChanges();

            // create the endpoint and send the message
            URL endpoint = new URL(URLHelper.getSaajURL() + "/getPriceList");
            SOAPMessage response = con.call(msg, endpoint);
            con.close();

            // get contents of response
            Vector<String> list = new Vector<String>();

            SOAPBody responseBody = response.getSOAPBody();
            Iterator it1 = responseBody.getChildElements();

            // get price-list element
            while (it1.hasNext()) {
                SOAPBodyElement bodyEl = (SOAPBodyElement) it1.next();
                Iterator it2 = bodyEl.getChildElements();

                // get coffee elements
                while (it2.hasNext()) {
                    SOAPElement child2 = (SOAPElement) it2.next();
                    Iterator it3 = child2.getChildElements();

                    // get coffee-name and price elements
                    while (it3.hasNext()) {
                        SOAPElement child3 = (SOAPElement) it3.next();
                        String value = child3.getValue();
                        list.addElement(value);
                    }
                }
            }

            for (int i = 0; i < list.size(); i = i + 2) {
                System.out.print(list.elementAt(i) + "        ");
                System.out.println(list.elementAt(i + 1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
