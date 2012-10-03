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
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.Date;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;
import javax.xml.namespace.QName;


public class ConfirmationServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(
                "com.sun.cb.saaj.ConfirmationServlet");
    static MessageFactory messageFactory = null;

    static {
        try {
            messageFactory = MessageFactory.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("init");
        super.init(servletConfig);
    }

    public void doPost(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost");

        try {
            // Get all the headers from the HTTP request
            MimeHeaders headers = getHeaders(req);

            // Get the body of the HTTP request
            InputStream is = req.getInputStream();

            // Now internalize the contents of a HTTP request
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
        logger.info("getHeaders");

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
        logger.info("putHeaders");

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

    // This is the application code for handling the message.
    public SOAPMessage onMessage(SOAPMessage message) {
        logger.info("onMessage");

        SOAPMessage confirmation = null;

        try {
            // Retrieve orderID from message received
            SOAPBody sentSB = message.getSOAPBody();
            Iterator sentIt = sentSB.getChildElements();
            SOAPBodyElement sentSBE = (SOAPBodyElement) sentIt.next();
            Iterator sentIt2 = sentSBE.getChildElements();
            SOAPElement sentSE = (SOAPElement) sentIt2.next();

            // Get the orderID text to put in confirmation
            String sentID = sentSE.getValue();

            // Create the confirmation message
            confirmation = messageFactory.createMessage();

            SOAPBody sb = confirmation.getSOAPBody();

            QName newBodyName = new QName(
                        "http://sonata.coffeebreak.com",
                        "confirmation",
                        "Confirm");
            SOAPBodyElement confirm = sb.addBodyElement(newBodyName);

            // Create the orderID element for confirmation
            QName newOrderIDName = new QName("orderId");
            SOAPElement newOrderNo = confirm.addChildElement(newOrderIDName);
            newOrderNo.addTextNode(sentID);

            // Create ship-date element
            QName shipDateName = new QName("ship-date");
            SOAPElement shipDate = confirm.addChildElement(shipDateName);

            // Create the shipping date
            Date today = new Date();
            long msPerDay = 1000 * 60 * 60 * 24;
            long msTarget = today.getTime();
            long msSum = msTarget + (msPerDay * 2);
            Date result = new Date();
            result.setTime(msSum);

            String sd = result.toString();
            shipDate.addTextNode(sd);

            confirmation.saveChanges();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return confirmation;
    }
}
