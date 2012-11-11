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
import javax.jms.Queue;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.MapMessage;
import javax.jms.JMSException;
import java.util.SortedSet;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Random;
import javax.annotation.Resource;


/**
 * The HumanResourceClient class is the client program for this
 * J2EE application. It publishes a message describing a new
 * hire business event that other departments can act upon. It
 * also listens for a message reporting the completion of the
 * other departments' actions and displays the results.
 */
public class HumanResourceClient {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/Topic")
    private static Topic pubTopic;
    static Object waitUntilDone = new Object();
    static SortedSet<Integer> outstandingRequests = Collections
        .synchronizedSortedSet(new TreeSet<Integer>());

    public static void main(String[] args) {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        MapMessage message = null;
        Queue replyQueue = null;
        MessageConsumer consumer = null;

        /*
         * Create connection.
         * Create session from connection; false means session
         *   is not transacted.
         * Create temporary queue and consumer, set message
         *   listener, and start connection.
         * Create producer and MapMessage.
         * Publish new hire business events.
         * Wait for all messages to be processed.
         * Finally, close connection.
         */
        try {
            Random rand = new Random();
            int nextHireID = rand.nextInt(100);

            String[] positions = {
                    "Programmer", "Senior Programmer", "Manager", "Director"
                };
            String[] firstNames = {
                    "Fred", "Robert", "Tom", "Steve", "Alfred", "Joe", "Jack",
                    "Harry", "Bill", "Gertrude", "Jenny", "Polly", "Ethel",
                    "Mary", "Betsy", "Carol", "Edna", "Gwen"
                };
            String[] lastNames = {
                    "Astaire", "Preston", "Tudor", "Stuart", "Drake", "Jones",
                    "Windsor", "Hapsburg", "Robinson", "Lawrence", "Wren",
                    "Parrott", "Waters", "Martin", "Blair", "Bourbon", "Merman",
                    "Verdon"
                };

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            replyQueue = session.createTemporaryQueue();
            consumer = session.createConsumer(replyQueue);
            consumer.setMessageListener(new HRListener());
            connection.start();

            producer = session.createProducer(pubTopic);

            message = session.createMapMessage();
            message.setJMSReplyTo(replyQueue);

            for (int i = 0; i < 5; i++) {
                int currentHireID = nextHireID++;
                message.setString(
                    "HireID",
                    String.valueOf(currentHireID));
                message.setString(
                        "Name",
                        firstNames[rand.nextInt(firstNames.length)] + " "
                        + lastNames[rand.nextInt(lastNames.length)]);
                message.setString(
                        "Position",
                        positions[rand.nextInt(positions.length)]);
                System.out.println(
                        "PUBLISHER: Setting hire " + "ID to "
                        + message.getString("HireID") + ", name "
                        + message.getString("Name") + ", position "
                        + message.getString("Position"));
                producer.send(message);
                outstandingRequests.add(new Integer(currentHireID));
            }

            System.out.println(
                    "Waiting for " + outstandingRequests.size() + " message(s)");

            synchronized (waitUntilDone) {
                waitUntilDone.wait();
            }
        } catch (Exception e) {
            System.err.println(
                    "HumanResourceClient: Exception: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.err.println(
                            "HumanResourceClient: " + "Close exception: "
                            + e.toString());
                }
            }

            System.exit(0);
        }
    }

    /**
     * The HRListener class implements the MessageListener
     * interface by defining an onMessage method.
     */
    static class HRListener implements MessageListener {
        /**
         * Displays the contents of a
         * MapMessage describing the results of processing the
         * new employee, then removes the employee ID from the
         * list of outstanding requests.
         *
         * @param message    the incoming message
         */
        public void onMessage(Message message) {
            MapMessage msg = (MapMessage) message;

            try {
                System.out.println("New hire event processed:");

                Integer id = Integer.valueOf(msg.getString("employeeId"));
                System.out.println("  Employee ID: " + id);
                System.out.println("  Name: " + msg.getString("employeeName"));
                System.out.println(
                        "  Equipment: " + msg.getString("equipmentList"));
                System.out.println(
                        "  Office number: " + msg.getString("officeNumber"));
                outstandingRequests.remove(id);
            } catch (JMSException je) {
                System.out.println(
                        "HRListener.onMessage(): Exception: " + je.toString());
            }

            if (outstandingRequests.size() == 0) {
                synchronized (waitUntilDone) {
                    waitUntilDone.notify();
                }
            } else {
                System.out.println(
                        "Waiting for " + outstandingRequests.size()
                        + " message(s)");
            }
        }
    }
}
