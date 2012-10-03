/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TemporaryTopic;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Collections;


/**
 * The MultiAppServerClient class is the client program for
 * this Java EE application.  It sends a message to two
 * different JMS providers and waits for a reply.
 */
public class MultiAppServerClient {
    @Resource(mappedName = "jms/ConnectionFactory")
    public static ConnectionFactory cf1; // App Server 1
    @Resource(mappedName = "jms/JupiterConnectionFactory")
    public static ConnectionFactory cf2; // App Server 2
    @Resource(mappedName = "jms/Topic")
    public static Topic pTopic;
    static Object waitUntilDone = new Object();
    static SortedSet<String> outstandingRequests1 = Collections
        .synchronizedSortedSet(new TreeSet<String>());
    static SortedSet<String> outstandingRequests2 = Collections
        .synchronizedSortedSet(new TreeSet<String>());

    public static void main(String[] args) {
        Connection con1 = null;
        Connection con2 = null;
        Session pubSession1 = null;
        Session pubSession2 = null;
        MessageProducer producer1 = null;
        MessageProducer producer2 = null;
        TemporaryTopic replyTopic1 = null;
        TemporaryTopic replyTopic2 = null;
        Session subSession1 = null;
        Session subSession2 = null;
        MessageConsumer consumer1 = null;
        MessageConsumer consumer2 = null;
        TextMessage message = null;

        try {
            // Create two connections.
            con1 = cf1.createConnection();
            con2 = cf2.createConnection();

            // Create sessions for producers.
            pubSession1 = con1.createSession(false, Session.AUTO_ACKNOWLEDGE);
            pubSession2 = con2.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create temporary topics for replies.
            replyTopic1 = pubSession1.createTemporaryTopic();
            replyTopic2 = pubSession2.createTemporaryTopic();

            // Create sessions for consumers.
            subSession1 = con1.createSession(false, Session.AUTO_ACKNOWLEDGE);
            subSession2 = con2.createSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * Create consumers, set message listeners, and
             * start connections.
             */
            consumer1 = subSession1.createConsumer(replyTopic1);
            consumer2 = subSession2.createConsumer(replyTopic2);
            consumer1.setMessageListener(
                    new ReplyListener(outstandingRequests1));
            consumer2.setMessageListener(
                    new ReplyListener(outstandingRequests2));
            con1.start();
            con2.start();

            // Create producers.
            producer1 = pubSession1.createProducer(pTopic);
            producer2 = pubSession2.createProducer(pTopic);

            /*
             * Create and send two sets of messages, one set to
             * each app server, at 1.5-second intervals.  For
             * each message, set the JMSReplyTo message header to
             * a reply topic, and set an id property.  Add the
             * message ID to the list of outstanding requests for
             * the message listener.
             */
            message = pubSession1.createTextMessage();

            int id = 1;

            for (int i = 0; i < 5; i++) {
                message.setJMSReplyTo(replyTopic1);
                message.setIntProperty("id", id);
                message.setText("text: id=" + id + " to local app server");
                producer1.send(message);
                System.out.println("Sent message: " + message.getText());
                outstandingRequests1.add(message.getJMSMessageID());
                id++;
                Thread.sleep(1500);
                message.setJMSReplyTo(replyTopic2);
                message.setIntProperty("id", id);
                message.setText("text: id=" + id + " to remote app server");

                try {
                    producer2.send(message);
                    System.out.println("Sent message: " + message.getText());
                    outstandingRequests2.add(message.getJMSMessageID());
                } catch (Exception e) {
                    System.err.println(
                            "Exception: Caught "
                            + "failed send to connection factory 2");
                    e.printStackTrace();
                }

                id++;
                Thread.sleep(1500);
            }

            /*
             * Wait for replies.
             */
            System.out.println(
                    "Waiting for " + outstandingRequests1.size()
                    + " message(s) from local app server");
            System.out.println(
                    "Waiting for " + outstandingRequests2.size()
                    + " message(s) from remote app server");

            while ((outstandingRequests1.size() > 0)
                    || (outstandingRequests2.size() > 0)) {
                synchronized (waitUntilDone) {
                    waitUntilDone.wait();
                }
            }

            System.out.println("Finished");
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("Closing connection 1");

            if (con1 != null) {
                try {
                    con1.close();
                } catch (Exception e) {
                    System.err.println(
                            "Error closing connection 1: " + e.toString());
                }
            }

            System.out.println("Closing connection 2");

            if (con2 != null) {
                try {
                    con2.close();
                } catch (Exception e) {
                    System.err.println(
                            "Error closing connection 2: " + e.toString());
                }
            }

            System.exit(0);
        }
    }

    /**
     * The ReplyListener class is instantiated with a set of
     * outstanding requests.
     */
    static class ReplyListener implements MessageListener {
        SortedSet outstandingRequests = null;

        /**
         * Constructor for ReplyListener class.
         *
         * @param outstandingRequests   set of outstanding
         *                              requests
         */
        ReplyListener(SortedSet outstandingRequests) {
            this.outstandingRequests = outstandingRequests;
        }

        /**
         * onMessage method, which displays the contents of the
         * id property and text and uses the JMSCorrelationID to
         * remove from the list of outstanding requests the
         * message to which this message is a reply.  If this is
         * the last message, it notifies the client.
         *
         * @param message     the incoming message
         */
        public void onMessage(Message message) {
            TextMessage tmsg = (TextMessage) message;
            String txt = null;
            int id = 0;
            String correlationID = null;

            try {
                id = tmsg.getIntProperty("id");
                txt = tmsg.getText();
                correlationID = tmsg.getJMSCorrelationID();
            } catch (JMSException e) {
                System.err.println(
                        "ReplyListener.onMessage: " + "JMSException: "
                        + e.toString());
            }

            System.out.println(
                    "ReplyListener: Received message: id=" + id + ", text="
                    + txt);
            outstandingRequests.remove(correlationID);

            if (outstandingRequests.size() == 0) {
                synchronized (waitUntilDone) {
                    waitUntilDone.notify();
                }
            } else {
                System.out.println(
                        "ReplyListener: Waiting for "
                        + outstandingRequests.size() + " message(s)");
            }
        }
    }
}
