/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cursor;

import java.io.FileInputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;


/**
 * CursorParse sample is used to demonstrate the use
 * of STAX cursor approach. In this approach application
 * instructs the parser to read the next event in the XML
 * input stream by calling <code>next()</code>.
 *
 * Note that <code>next()</code> just returns an integer constant
 * corresponding to underlying event where the parser is positioned.
 * Application needs to call relevant function to get more
 * information related to the underlying event.
 *
 * You can imagine this approach as a virtual cursor moving across
 * the XML input stream. There are various accessor methods which
 * can be called when that virtual cursor is at particular event.
 *
 *
 * @author <a href="neeraj.bajaj@sun.com">Neeraj Bajaj</a> Sun Microsystems,inc.
 *
 */
public class CursorParse {
    private static void printUsage() {
        System.out.println(
                "usage: java -classpath CursorParse -x <count> <xmlfile>");
    }

    public static void main(String[] args) throws Exception {
        int count = 0;
        String filename = null;

        try {
            filename = args[2];

            if (args[0].equals("-x")) {
                count = Integer.parseInt(args[1]);
            } else {
                printUsage();
            }
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            printUsage();
            System.exit(0);
        } catch (Exception ex) {
            printUsage();
            ex.printStackTrace();
        }

        XMLInputFactory xmlif = null;

        try {
            xmlif = XMLInputFactory.newInstance();
            xmlif.setProperty(
                    XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
                    Boolean.TRUE);
            xmlif.setProperty(
                    XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                    Boolean.FALSE);
            //set the IS_COALESCING property to true , if application desires to
            //get whole text data as one event.            
            xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("");
        System.out.println("FACTORY: " + xmlif);
        System.out.println("filename = " + filename);
        System.out.println("");

        long starttime = System.currentTimeMillis();

        try {
            for (int i = 0; i < count; i++) {
                //pass the file name.. all relative entity refernces will be resolved against this as
                //base URI.                                                
                XMLStreamReader xmlr = xmlif.createXMLStreamReader(
                            filename,
                            new FileInputStream(filename));

                //when XMLStreamReader is created, it is positioned at START_DOCUMENT event.
                int eventType = xmlr.getEventType();
                //printEventType(eventType);
                printStartDocument(xmlr);

                //check if there are more events in the input stream
                while (xmlr.hasNext()) {
                    eventType = xmlr.next();
                    //printEventType(eventType);

                    //these functions prints the information about the particular event by calling relevant function
                    printStartElement(xmlr);
                    printEndElement(xmlr);
                    printText(xmlr);
                    printPIData(xmlr);
                    printComment(xmlr);
                }
            }
        } catch (XMLStreamException ex) {
            System.out.println(ex.getMessage());

            if (ex.getNestedException() != null) {
                ex.getNestedException()
                  .printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long endtime = System.currentTimeMillis();
        System.out.println(" Parsing Time = " + (endtime - starttime));
    }

    /**
     * Returns the String representation of the given integer constant.
     *
     * @param eventType Type of event.
     * @return String representation of the event
     */
    public static final String getEventTypeString(int eventType) {
        switch (eventType) {
        case XMLEvent.START_ELEMENT:
            return "START_ELEMENT";

        case XMLEvent.END_ELEMENT:
            return "END_ELEMENT";

        case XMLEvent.PROCESSING_INSTRUCTION:
            return "PROCESSING_INSTRUCTION";

        case XMLEvent.CHARACTERS:
            return "CHARACTERS";

        case XMLEvent.COMMENT:
            return "COMMENT";

        case XMLEvent.START_DOCUMENT:
            return "START_DOCUMENT";

        case XMLEvent.END_DOCUMENT:
            return "END_DOCUMENT";

        case XMLEvent.ENTITY_REFERENCE:
            return "ENTITY_REFERENCE";

        case XMLEvent.ATTRIBUTE:
            return "ATTRIBUTE";

        case XMLEvent.DTD:
            return "DTD";

        case XMLEvent.CDATA:
            return "CDATA";

        case XMLEvent.SPACE:
            return "SPACE";
        }

        return "UNKNOWN_EVENT_TYPE , " + eventType;
    }

    private static void printEventType(int eventType) {
        System.out.println(
                "EVENT TYPE(" + eventType + ") = "
                + getEventTypeString(eventType));
    }

    private static void printStartDocument(XMLStreamReader xmlr) {
        if (xmlr.START_DOCUMENT == xmlr.getEventType()) {
            System.out.println(
                    "<?xml version=\"" + xmlr.getVersion() + "\""
                    + " encoding=\"" + xmlr.getCharacterEncodingScheme() + "\""
                    + "?>");
        }
    }

    private static void printComment(XMLStreamReader xmlr) {
        if (xmlr.getEventType() == xmlr.COMMENT) {
            System.out.print("<!--" + xmlr.getText() + "-->");
        }
    }

    private static void printText(XMLStreamReader xmlr) {
        if (xmlr.hasText()) {
            System.out.print(xmlr.getText());
        }
    }

    private static void printPIData(XMLStreamReader xmlr) {
        if (xmlr.getEventType() == XMLEvent.PROCESSING_INSTRUCTION) {
            System.out.print(
                    "<?" + xmlr.getPITarget() + " " + xmlr.getPIData() + "?>");
        }
    }

    private static void printStartElement(XMLStreamReader xmlr) {
        if (xmlr.isStartElement()) {
            System.out.print("<" + xmlr.getName().toString());
            printAttributes(xmlr);
            System.out.print(">");
        }
    }

    private static void printEndElement(XMLStreamReader xmlr) {
        if (xmlr.isEndElement()) {
            System.out.print("</" + xmlr.getName().toString() + ">");
        }
    }

    private static void printAttributes(XMLStreamReader xmlr) {
        int count = xmlr.getAttributeCount();

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
                System.out.print(xmlr.getAttributeName(i).toString());
                System.out.print("=");
                System.out.print("\"");
                System.out.print(xmlr.getAttributeValue(i));
                System.out.print("\"");
            }
        }

        count = xmlr.getNamespaceCount();

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
                System.out.print("xmlns");

                if (xmlr.getNamespacePrefix(i) != null) {
                    System.out.print(":" + xmlr.getNamespacePrefix(i));
                }

                System.out.print("=");
                System.out.print("\"");
                System.out.print(xmlr.getNamespaceURI(i));
                System.out.print("\"");
            }
        }
    }
}
