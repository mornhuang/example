/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.cb.saaj;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.*;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;


public class PriceListServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(
                "com.sun.cb.saaj.PriceListServlet");
    static MessageFactory messageFactory = null;

    static {
        try {
            messageFactory = MessageFactory.newInstance();
        } catch (Exception ex) {
            logger.severe("Exception: " + ex.toString());
        }
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

    public void doPost(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get all the headers from the HTTP request
            MimeHeaders headers = getHeaders(req);

            // Get the body of the HTTP request
            InputStream is = req.getInputStream();

            // Now internalize the contents of the HTTP request
            // and create a SOAPMessage
            SOAPMessage msg = messageFactory.createMessage(headers, is);

            SOAPMessage reply = null;
            reply = onMessage(msg);

            if (reply != null) {
                /*
                 * Need to call saveChanges because we're
                 * going to use the MimeHeaders to set HTTP
                 * response information. These MimeHeaders
                 * are generated as part of the save.
                 */
                if (reply.saveRequired()) {
                    reply.saveChanges();
                }

                resp.setStatus(HttpServletResponse.SC_OK);
                putHeaders(
                    reply.getMimeHeaders(),
                    resp);

                // Write out the message on the response stream
                logger.info("Reply message:");

                OutputStream os = resp.getOutputStream();
                reply.writeTo(os);
                os.flush();
            } else {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (Exception ex) {
            throw new ServletException("SAAJ POST failed: " + ex.getMessage());
        }
    }

    static MimeHeaders getHeaders(HttpServletRequest req) {
        Enumeration headerNames = req.getHeaderNames();
        MimeHeaders headers = new MimeHeaders();

        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = req.getHeader(headerName);

            StringTokenizer values = new StringTokenizer(headerValue, ",");

            while (values.hasMoreTokens()) {
                headers.addHeader(
                    headerName,
                    values.nextToken().trim());
            }
        }

        return headers;
    }

    static void putHeaders(
        MimeHeaders headers,
        HttpServletResponse res) {
        Iterator it = headers.getAllHeaders();

        while (it.hasNext()) {
            MimeHeader header = (MimeHeader) it.next();

            String[] values = headers.getHeader(header.getName());

            if (values.length == 1) {
                res.setHeader(
                    header.getName(),
                    header.getValue());
            } else {
                StringBuffer concat = new StringBuffer();
                int i = 0;

                while (i < values.length) {
                    if (i != 0) {
                        concat.append(',');
                    }

                    concat.append(values[i++]);
                }

                res.setHeader(
                    header.getName(),
                    concat.toString());
            }
        }
    }

    // This is the application code for responding to the message.
    public SOAPMessage onMessage(SOAPMessage msg) {
        SOAPMessage message = null;

        try {
            // create price list message
            message = messageFactory.createMessage();

            // Access the SOAPBody object
            SOAPBody body = message.getSOAPBody();

            // Create the appropriate elements and add them
            QName bodyName = new QName(
                        "http://sonata.coffeebreak.com",
                        "price-list",
                        "PriceList");
            SOAPBodyElement list = body.addBodyElement(bodyName);

            // coffee
            QName coffeeN = new QName("coffee");
            SOAPElement coffee = list.addChildElement(coffeeN);

            QName coffeeNm1 = new QName("coffee-name");
            SOAPElement coffeeName = coffee.addChildElement(coffeeNm1);
            coffeeName.addTextNode("Arabica");

            QName priceName1 = new QName("price");
            SOAPElement price1 = coffee.addChildElement(priceName1);
            price1.addTextNode("4.50");

            QName coffeeNm2 = new QName("coffee-name");
            SOAPElement coffeeName2 = coffee.addChildElement(coffeeNm2);
            coffeeName2.addTextNode("Espresso");

            QName priceName2 = new QName("price");
            SOAPElement price2 = coffee.addChildElement(priceName2);
            price2.addTextNode("5.00");

            QName coffeeNm3 = new QName("coffee-name");
            SOAPElement coffeeName3 = coffee.addChildElement(coffeeNm3);
            coffeeName3.addTextNode("Dorada");

            QName priceName3 = new QName("price");
            SOAPElement price3 = coffee.addChildElement(priceName3);
            price3.addTextNode("6.00");

            QName coffeeNm4 = new QName("coffee-name");
            SOAPElement coffeeName4 = coffee.addChildElement(coffeeNm4);
            coffeeName4.addTextNode("House Blend");

            QName priceName4 = new QName("price");
            SOAPElement price4 = coffee.addChildElement(priceName4);
            price4.addTextNode("5.00");

            message.saveChanges();
        } catch (Exception e) {
            logger.severe("onMessage: Exception: " + e.toString());
        }

        return message;
    }
}
