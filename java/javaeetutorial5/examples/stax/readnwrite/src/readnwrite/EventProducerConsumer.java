/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package readnwrite;

import java.util.Calendar;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;


/*
 * Stax has writing APIs. <code>XMLEventWriter</code> extends from <code>XMLEventConsumer</code>
 * interface. So <code>XMLEventWriter</code> acts as a consumer which can consume events.
 * Event producer <code>XMLEventReader</code> and consumer <code>XMLEventWriter</code> mechanism
 * makes it possible to read XML from one stream sequentially and simultaneously write to other stream.
 *
 * This makes reading and writing very fast. This sample shows how StAX producer/consumer mechanism can be
 * used to read and write simultaneously. This sample also shows that stream can be modified
 * or new events can be added dynamically and then written to different stream using <code>XMLEventWriter</code>
 * APIs.
 *
 * EventProducerConsumer.java
 *
 *
 */

/**
 *
 * @author Neeraj Bajaj
 */
public class EventProducerConsumer {
    XMLEventFactory m_eventFactory = XMLEventFactory.newInstance();

    /** Creates a new instance of EventProducerConsumer */
    public EventProducerConsumer() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Specify XML File Name");
        }

        try {
            EventProducerConsumer ms = new EventProducerConsumer();

            XMLEventReader reader = XMLInputFactory.newInstance()
                                                   .createXMLEventReader(
                        new java.io.FileInputStream(args[0]));
            XMLEventWriter writer = XMLOutputFactory.newInstance()
                                                    .createXMLEventWriter(
                        System.out);

            while (reader.hasNext()) {
                XMLEvent event = (XMLEvent) reader.next();

                //write this event to Consumer (XMLOutputStream)
                if (event.getEventType() == event.CHARACTERS) {
                    //character events where text "Name1" is replaced with text output 
                    //of following function call Calendar.getInstance().getTime().toString()
                    writer.add(ms.getNewCharactersEvent(event.asCharacters()));
                } else {
                    writer.add(event);
                }
            }

            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** New Character event (with text containing current time) is created using XMLEventFactory in case the
     *  Characters event passed matches the criteria.
     *
     *  @param Characters Current character event.
     *  return Characters New Characters event.
     */
    Characters getNewCharactersEvent(Characters event) {
        if (event.getData()
                     .equalsIgnoreCase("Name1")) {
            return m_eventFactory.createCharacters(
                    Calendar.getInstance().getTime().toString());
        }
        //else return the same event
        else {
            return event;
        }
    }
}
