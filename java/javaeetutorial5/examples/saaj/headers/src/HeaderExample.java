/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPBody;
import javax.xml.namespace.QName;
import java.util.Iterator;


public class HeaderExample {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Argument required: " + "-Dsoap=<1.1|1.2>");
            System.exit(1);
        }

        String version = args[0];
        System.out.println("SOAP version is " + version);

        if (!(version.equals("1.1") || version.equals("1.2"))) {
            System.err.println("Value must be 1.1 or 1.2");
            System.exit(1);
        }

        try {
            // Create message factory
            MessageFactory messageFactory = null;

            if (version.equals("1.1")) {
                messageFactory = MessageFactory.newInstance();
            } else {
                messageFactory = MessageFactory.newInstance(
                            SOAPConstants.SOAP_1_2_PROTOCOL);
            }

            // Create a message
            SOAPMessage message = messageFactory.createMessage();

            // Get the SOAP header from the message and 
            //  add headers to it
            SOAPHeader header = message.getSOAPHeader();

            String nameSpace = "ns";
            String nameSpaceURI = "http://gizmos.com/NSURI";

            QName order = new QName(nameSpaceURI, "orderDesk", nameSpace);
            SOAPHeaderElement orderHeader = header.addHeaderElement(order);

            if (version.equals("1.1")) {
                orderHeader.setActor("http://gizmos.com/orders");
            } else {
                orderHeader.setRole("http://gizmos.com/orders");
            }

            QName shipping = new QName(nameSpaceURI, "shippingDesk", nameSpace);
            SOAPHeaderElement shippingHeader = header.addHeaderElement(
                        shipping);

            if (version.equals("1.1")) {
                shippingHeader.setActor("http://gizmos.com/shipping");
            } else {
                shippingHeader.setRole("http://gizmos.com/shipping");
            }

            QName confirmation = new QName(
                        nameSpaceURI,
                        "confirmationDesk",
                        nameSpace);
            SOAPHeaderElement confirmationHeader = header.addHeaderElement(
                        confirmation);

            if (version.equals("1.1")) {
                confirmationHeader.setActor("http://gizmos.com/confirmations");
            } else {
                confirmationHeader.setRole("http://gizmos.com/confirmations");
            }

            // Add mustUnderstand attribute to this header element
            confirmationHeader.setMustUnderstand(true);

            QName billing = new QName(nameSpaceURI, "billingDesk", nameSpace);
            SOAPHeaderElement billingHeader = header.addHeaderElement(billing);

            if (version.equals("1.1")) {
                billingHeader.setActor("http://gizmos.com/billing");
            } else {
                billingHeader.setRole("http://gizmos.com/billing");
            }

            // Add relay attribute to this header element, if SOAP 1.2
            if (version.equals("1.2")) {
                billingHeader.setRelay(true);
            }

            // Get the SOAP body from the message but leave
            // it empty
            SOAPBody body = message.getSOAPBody();

            message.saveChanges();

            // Display the message that would be sent
            System.out.println("\n----- Request Message ----\n");
            message.writeTo(System.out);
            System.out.println();

            // Look at the headers
            Iterator allHeaders = header.examineAllHeaderElements();

            while (allHeaders.hasNext()) {
                SOAPHeaderElement headerElement = (SOAPHeaderElement) allHeaders
                    .next();
                QName headerName = headerElement.getElementQName();
                System.out.println("\nHeader name is " + headerName.toString());

                if (version.equals("1.1")) {
                    System.out.println("Actor is " + headerElement.getActor());
                } else {
                    System.out.println("Role is " + headerElement.getRole());
                }

                System.out.println(
                        "mustUnderstand is "
                        + headerElement.getMustUnderstand());

                if (version.equals("1.2")) {
                    System.out.println("relay is " + headerElement.getRelay());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
