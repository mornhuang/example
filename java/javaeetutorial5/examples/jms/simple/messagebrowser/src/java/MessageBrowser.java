/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.QueueBrowser;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.Enumeration;


/**
 * The MessageBrowser class inspects a queue and displays the messages it
 * holds.
 */
public class MessageBrowser {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;

    /**
     * Main method.
     *
     * @param args     the queue used by the example
     */
    public static void main(String[] args) {
        Connection connection = null;

        /*
         * Create connection.
         * Create session from connection; false means session is
         * not transacted.
         * Create QueueBrowser.
         * Check for messages on queue.
         * Finally, close connection.
         */
        try {
            connection = connectionFactory.createConnection();

            Session session = connection.createSession(
                        false,
                        Session.AUTO_ACKNOWLEDGE);
            QueueBrowser browser = session.createBrowser(queue);
            Enumeration msgs = browser.getEnumeration();

            if (!msgs.hasMoreElements()) {
                System.out.println("No messages in queue");
            } else {
                while (msgs.hasMoreElements()) {
                    Message tempMsg = (Message) msgs.nextElement();
                    System.out.println("Message: " + tempMsg);
                }
            }
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }
}
