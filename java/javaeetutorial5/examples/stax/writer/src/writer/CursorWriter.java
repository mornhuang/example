/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package writer;

import java.io.FileWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;
import javax.xml.namespace.QName;


/**
 * CursorWriter sample is used to demonstrate the use of STAX Writer api's.
 *
 * @author k.venugopal@sun.com
 */
public class CursorWriter {
    private static String filename = null;

    private static void printUsage() {
        System.out.println(
                "java -classpath <.....> CursorWriter -f <outputFileName>");
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            String fileName = null;

            try {
                if (args[0].equals("-f")) {
                    fileName = args[1];
                } else {
                    printUsage();

                    return;
                }
            } catch (Exception ex) {
                printUsage();

                return;
            }

            XMLOutputFactory xof = XMLOutputFactory.newInstance();
            XMLStreamWriter xtw = null;
            xtw = xof.createXMLStreamWriter(new FileWriter(fileName));
            xtw.writeComment(
                    "all elements here are explicitly in the HTML namespace");
            xtw.writeStartDocument("utf-8", "1.0");
            xtw.setPrefix("html", "http://www.w3.org/TR/REC-html40");
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "html");
            xtw.writeNamespace("html", "http://www.w3.org/TR/REC-html40");
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "head");
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "title");
            xtw.writeCharacters("Frobnostication");
            xtw.writeEndElement();
            xtw.writeEndElement();
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "body");
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "p");
            xtw.writeCharacters("Moved to");
            xtw.writeStartElement("http://www.w3.org/TR/REC-html40", "a");
            xtw.writeAttribute("href", "http://frob.com");
            xtw.writeCharacters("here");
            xtw.writeEndElement();
            xtw.writeEndElement();
            xtw.writeEndElement();
            xtw.writeEndElement();
            xtw.writeEndDocument();
            xtw.flush();
            xtw.close();
        } catch (Exception ex) {
            System.err.println(
                    "Exception occurred while running writer samples");
        }

        System.out.println("Done");
    }
}
