/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package event;

import java.io.FileInputStream;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import javax.xml.namespace.QName;


/**
 * EventParse sample is used to demonstrate the use
 * of Event api's.
 *
 * @author <a href="neeraj.bajaj@sun.com">Neeraj Bajaj</a> Sun Microsystems,inc.
 */
public class EventParse {
    private static void printUsage() {
        System.out.println("usage: java -classpath EventParse <xmlfile>");
    }

    public static void main(String[] args) throws Exception {
        String filename = null;

        try {
            filename = args[0];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            printUsage();
            System.exit(0);
        }

        //Get the factory instace first.
        XMLInputFactory factory = XMLInputFactory.newInstance();
        System.out.println("FACTORY: " + factory);

        //create the XMLEventReader, pass the filename for any relative resolution 
        XMLEventReader r = factory.createXMLEventReader(
                    filename,
                    new FileInputStream(filename));

        //iterate as long as there are more events on the input stream
        while (r.hasNext()) {
            XMLEvent e = r.nextEvent();
            System.out.println(e.toString());
        }
    }

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

        return "UNKNOWN_EVENT_TYPE " + "," + eventType;
    }
}
