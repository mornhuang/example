/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;


public class AckEquivExample {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/DurableConnectionFactory")
    private static ConnectionFactory durableConnectionFactory;
    @Resource(mappedName = "jms/ControlQueue")
    private static Queue controlQueue;
    @Resource(mappedName = "jms/Queue")
    private static Queue queue;
    @Resource(mappedName = "jms/Topic")
    private static Topic topic;
    final String CONTROL_QUEUE = "jms/ControlQueue";
    final String conFacName = "jms/DurableConnectionFactory";
    final String queueName = "jms/Queue";
    final String topicName = "jms/Topic";

    /**
     * Instantiates the sender, receiver, subscriber, and
     * publisher classes and starts their threads.
     * Calls the join method to wait for the threads to die.
     */
    public void run_threads() {
        SynchSender synchSender = new SynchSender();
        SynchReceiver synchReceiver = new SynchReceiver();
        AsynchSubscriber asynchSubscriber = new AsynchSubscriber();
        MultiplePublisher multiplePublisher = new MultiplePublisher();

        synchSender.start();
        synchReceiver.start();

        try {
            synchSender.join();
            synchReceiver.join();
        } catch (InterruptedException e) {
        }

        try {
            asynchSubscriber.start();
            Thread.sleep(1000);
            multiplePublisher.start();
        } catch (InterruptedException e) {
        }

        try {
            asynchSubscriber.join();
            multiplePublisher.join();
        } catch (InterruptedException e) {
        }
    }

    /**
     * Calls the run_threads method to execute the program
     * threads.
     *
     * @param args    the topic used by the example
     */
    public static void main(String[] args) {
        AckEquivExample aee = new AckEquivExample();

        if (args.length != 0) {
            System.out.println("Program takes no arguments.");
            System.exit(1);
        }

        System.out.println("Queue name is " + aee.CONTROL_QUEUE);
        System.out.println("Queue name is " + aee.queueName);
        System.out.println("Topic name is " + aee.topicName);
        System.out.println("Connection factory name is " + aee.conFacName);

        aee.run_threads();
        System.exit(0);
    }

    /**
     * The AsynchSubscriber class creates a session in
     * AUTO_ACKNOWLEDGE mode and fetches several messages from a
     * topic asynchronously, using a message listener,
     * TextListener.
     *
     * Each message is acknowledged after the onMessage method
     * completes.
     */
    public class AsynchSubscriber extends Thread {
        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageConsumer subscriber = null;
            TextListener listener = null;

            try {
                connection = durableConnectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.AUTO_ACKNOWLEDGE);
                System.out.println(
                        "SUBSCRIBER: Created " + "auto-acknowledge session");
            } catch (Exception e) {
                System.err.println(
                        "Connection problem with subscriber: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * Create auto-acknowledge subscriber.
             * Register message listener (TextListener).
             * Start message delivery.
             * Send synchronize message to publisher, then wait
             *   till all messages have arrived.
             * Listener displays the messages obtained.
             */
            try {
                subscriber = session.createDurableSubscriber(topic, "AckSub");
                listener = new TextListener();
                subscriber.setMessageListener(listener);
                connection.start();

                // Let publisher know that subscriber is ready.
                try {
                    SampleUtilities.sendSynchronizeMessage(
                            "SUBSCRIBER: ",
                            connectionFactory,
                            controlQueue);
                } catch (Exception e) {
                    System.err.println(
                            "Connection or queue problem with subscriber: "
                            + e.toString());
                    e.printStackTrace();

                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (JMSException ee) {
                        }
                    }

                    System.exit(1);
                }

                /*
                 * Asynchronously process messages.
                 * Block until publisher issues a control message
                 * indicating end of publish stream.
                 */
                listener.monitor.waitTillDone();
                subscriber.close();
                session.unsubscribe("AckSub");
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

        /**
         * The TextListener class implements the MessageListener
         * interface by defining an onMessage method for the
         * AsynchSubscriber class.
         */
        private class TextListener implements MessageListener {
            final SampleUtilities.DoneLatch monitor = new SampleUtilities.DoneLatch();

            /**
             * Casts the message to a TextMessage and displays
             * its text. A non-text message is interpreted as the
             * end of the message stream, and the message
             * listener sets its monitor state to all done
             * processing messages.
             *
             * @param message    the incoming message
             */
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;

                    try {
                        System.out.println(
                                "SUBSCRIBER: " + "Processing message: "
                                + msg.getText());
                    } catch (JMSException e) {
                        System.err.println(
                                "Exception in " + "onMessage(): "
                                + e.toString());
                    }
                } else {
                    monitor.allDone();
                }
            }
        }
    }

    /**
     * The MultiplePublisher class creates a session in
     * AUTO_ACKNOWLEDGE mode and publishes three messages
     * to a topic.
     */
    public class MultiplePublisher extends Thread {
        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageProducer publisher = null;
            TextMessage message = null;
            final int NUMMSGS = 3;
            final String MSG_TEXT = "Here is an auto-acknowledge message";

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.AUTO_ACKNOWLEDGE);
                System.out.println(
                        "PUBLISHER: Created " + "auto-acknowledge session");
            } catch (Exception e) {
                System.err.println(
                        "Connection problem with publisher: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * After synchronizing with subscriber, create
             *   publisher.
             * Send 3 messages, varying text slightly.
             * Send end-of-messages message.
             */
            try {
                /*
                 * Synchronize with subscriber.  Wait for message
                 * indicating that subscriber is ready to receive
                 * messages.
                 */
                try {
                    SampleUtilities.receiveSynchronizeMessages(
                            "PUBLISHER: ",
                            connectionFactory,
                            controlQueue,
                            1);
                } catch (Exception e) {
                    System.err.println(
                            "Connection or queue problem with publisher: "
                            + e.toString());
                    e.printStackTrace();

                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (JMSException ee) {
                        }
                    }

                    System.exit(1);
                }

                publisher = session.createProducer(topic);
                message = session.createTextMessage();

                for (int i = 0; i < NUMMSGS; i++) {
                    message.setText(MSG_TEXT + " " + (i + 1));
                    System.out.println(
                            "PUBLISHER: Publishing " + "message: "
                            + message.getText());
                    publisher.send(message);
                }

                /*
                 * Send a non-text control message indicating
                 * end of messages.
                 */
                publisher.send(session.createMessage());
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

    /**
     * The SynchReceiver class creates a session in
     * CLIENT_ACKNOWLEDGE mode and receives the message sent by
     * the SynchSender class.
     */
    public class SynchReceiver extends Thread {
        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageConsumer receiver = null;
            TextMessage message = null;

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.CLIENT_ACKNOWLEDGE);
            } catch (Exception e) {
                System.err.println("Connection problem: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * Create client-acknowledge receiver.
             * Receive message and process it.
             * Acknowledge message.
             */
            try {
                System.out.println(
                        "  RECEIVER: Created " + "client-acknowledge session");
                receiver = session.createConsumer(queue);
                connection.start();
                message = (TextMessage) receiver.receive();
                System.out.println(
                        "  RECEIVER: Processing " + "message: "
                        + message.getText());
                System.out.println(
                        "  RECEIVER: Now I'll " + "acknowledge the message");
                message.acknowledge();
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

    /**
     * The SynchSender class creates a session in
     * CLIENT_ACKNOWLEDGE mode and sends a message.
     */
    public class SynchSender extends Thread {
        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageProducer producer = null;
            final String MSG_TEXT = "Here is a client-acknowledge message";
            TextMessage message = null;

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.CLIENT_ACKNOWLEDGE);
            } catch (Exception e) {
                System.err.println("Connection problem: " + e.toString());

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * Create client-acknowledge sender.
             * Create and send message.
             */
            try {
                System.out.println(
                        "  SENDER: Created " + "client-acknowledge session");
                producer = session.createProducer(queue);
                message = session.createTextMessage();
                message.setText(MSG_TEXT);
                System.out.println(
                        "  SENDER: Sending " + "message: " + message.getText());
                producer.send(message);
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
}
