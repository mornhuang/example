/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package cursor2event;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import com.sun.xml.stream.events.XMLEventAllocatorImpl;


/**
 * StAX has two approach to parse XML.
 * <li>
 *     <ul> Cursor : <code>XMLStreamReader</code> </ul>
 *     <ul> Event : <code>XMLEventReader</code> </ul>
 * </li>
 *
 * <code>XMLStreamReader</code> returns the integer constant corresponding to particular event and
 * application need to call relevant function to get information about that event. This is the most
 * efficient way to parse XML.
 *
 * <code>XMLEventReader</code> returns immutable and persistent event objects.
 * All the information related to particular event is encapsulated in the returned <code>XMLEvent</code>
 * object. In this approach user need not worry about how to get relevant information corresponding to
 * particular event,as in case of XMLStreamReader. This makes it pretty easy for the user to work with
 * event approach.
 *
 * The disadvantage of event approach is the extra overhead of creating every event objects which
 * consumes both time and memory. However, It is possible to get event information as an <code>XMLEvent<code/>
 * object even when using cursor approach ie. <code>XMLStreamReader</code> using <code>XMLEventAllocator</code>.
 *
 * This class shows how application can get information as an <code>XMLEvent</code> object when using cursor
 * approach.
 *
 *
 * @author Neeraj Bajaj, Sun Microsystems.
 *
 */
public class CursorApproachEventObject {
    static XMLEventAllocator allocator = null;

    private static void printUsage() {
        System.out.println(
                "Usage: java -classpath SwitchCursorToEvent <xmlfile>");
    }

    public static void main(String[] args) throws Exception {
        String filename = null;

        try {
            filename = args[0];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            printUsage();
            System.exit(0);
        } catch (Exception ex) {
            printUsage();
            ex.printStackTrace();
        }

        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            System.out.println("FACTORY: " + xmlif);
            xmlif.setEventAllocator(new XMLEventAllocatorImpl());
            allocator = xmlif.getEventAllocator();

            XMLStreamReader xmlr = xmlif.createXMLStreamReader(
                        filename,
                        new FileInputStream(filename));

            int eventType = xmlr.getEventType();

            while (xmlr.hasNext()) {
                eventType = xmlr.next();

                //Get all "Book" elements as XMLEvent object
                if ((eventType == XMLStreamConstants.START_ELEMENT)
                        && xmlr.getLocalName()
                                   .equals("Book")) {
                    //get immutable XMLEvent
                    StartElement event = getXMLEvent(xmlr)
                                             .asStartElement();
                    System.out.println("EVENT: " + event.toString());
                }
            }
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** Get the immutable XMLEvent from given XMLStreamReader using XMLEventAllocator */
    private static XMLEvent getXMLEvent(XMLStreamReader reader)
        throws XMLStreamException {
        return allocator.allocate(reader);
    }
}
