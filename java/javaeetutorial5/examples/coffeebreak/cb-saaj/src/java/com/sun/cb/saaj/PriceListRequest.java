/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.saaj;

import com.sun.cb.common.DateHelper;
import com.sun.cb.ws.server.PriceItemBean;
import com.sun.cb.ws.server.PriceListBean;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import java.util.Vector;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.datatype.DatatypeFactory;


public class PriceListRequest {
    String url;

    public PriceListRequest(String url) {
        this.url = url;
    }

    public PriceListBean getPriceList() {
        PriceListBean plb = null;

        try {
            SOAPConnectionFactory scf = SOAPConnectionFactory
                .newInstance();
            SOAPConnection con = scf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage msg = mf.createMessage();

            // Access the SOAPBody object
            SOAPBody body = msg.getSOAPBody();

            // Create SOAPBodyElement request 
            QName bodyName = new QName(
                        "http://sonata.coffeebreak.com",
                        "request-prices",
                        "RequestPrices");
            SOAPBodyElement requestPrices = body.addBodyElement(bodyName);

            QName requestName = new QName("request");
            SOAPElement request = requestPrices.addChildElement(requestName);
            request.addTextNode("Send updated price list.");

            msg.saveChanges();

            // Create the endpoint and send the message
            URL endpoint = new URL(url);
            SOAPMessage response = con.call(msg, endpoint);
            con.close();

            // Get contents of response
            Vector<String> list = new Vector<String>();

            SOAPBody responseBody = response.getSOAPBody();
            Iterator it1 = responseBody.getChildElements();

            // Get price-list element
            while (it1.hasNext()) {
                SOAPBodyElement bodyEl = (SOAPBodyElement) it1.next();
                Iterator it2 = bodyEl.getChildElements();

                // Get coffee elements
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

            ArrayList<PriceItemBean> items = new ArrayList<PriceItemBean>();

            for (int i = 0; i < list.size(); i = i + 2) {
                PriceItemBean pib = new PriceItemBean();
                pib.setCoffeeName(list.elementAt(i).toString());
                pib.setPricePerPound(
                        new BigDecimal(list.elementAt(i + 1).toString()));
                items.add(pib);
                System.out.print(list.elementAt(i) + "        ");
                System.out.println(list.elementAt(i + 1));
            }

            Date today = new Date();
            Date endDate = DateHelper.addDays(today, 30);
            GregorianCalendar todayCal = new GregorianCalendar();
            todayCal.setTime(today);

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(endDate);
            plb = new PriceListBean();
            plb.setStartDate(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(
                            todayCal));

            List<PriceItemBean> priceItems = new ArrayList<PriceItemBean>();
            Iterator<PriceItemBean> i = items.iterator();

            while (i.hasNext()) {
                PriceItemBean pib = i.next();
                plb.getPriceItems()
                   .add(pib);
            }

            plb.setEndDate(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return plb;
    }
}
