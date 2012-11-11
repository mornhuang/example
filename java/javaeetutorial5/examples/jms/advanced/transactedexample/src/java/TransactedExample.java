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
import javax.jms.MapMessage;
import javax.jms.JMSException;
import java.util.Hashtable;
import java.util.Random;
import javax.annotation.Resource;


public class TransactedExample {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/AQueue")
    private static Queue vendorOrderQueue;
    @Resource(mappedName = "jms/BQueue")
    private static Queue retailerConfirmQueue;
    @Resource(mappedName = "jms/CQueue")
    private static Queue vendorConfirmQueue;
    @Resource(mappedName = "jms/OTopic")
    private static Topic supplierOrderTopic;

    /**
     * Creates the Retailer and Vendor classes and the two
     * supplier classes, then starts the threads.
     *
     * @param quantity    the quantity specified on the command
     *                    line
     */
    public static void run_threads(int quantity) {
        Retailer r = new Retailer(quantity);
        Vendor v = new Vendor();
        GenericSupplier ms = new GenericSupplier("Monitor");
        GenericSupplier ss = new GenericSupplier("Hard Drive");

        ms.start();
        ss.start();

        // make sure both supplier connections have started
        ms.waitForTopicConsumer();
        ss.waitForTopicConsumer();

        r.start();
        v.start();

        try {
            ms.join();
            ss.join();
            r.join();
            v.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the order quantity from the command line, then
     * calls the run_threads method to execute the program
     * threads.
     *
     * @param args    the quantity of computers being ordered
     */
    public static void main(String[] args) {
        TransactedExample te = new TransactedExample();
        int quantity = 0;

        if (args.length != 1) {
            System.out.println("Program takes numerical argument.");
            System.err.println(
                    "Program assumes three "
                    + "queues named jms/AQueue, jms/BQueue, and jms/CQueue "
                    + "and one topic named jms/OTopic");
            System.exit(1);
        }

        quantity = (new Integer(args[0])).intValue();
        System.out.println("Quantity to be ordered is " + quantity);

        if (quantity > 0) {
            te.run_threads(quantity);
            System.exit(0);
        } else {
            System.out.println("Quantity must be positive and " + "nonzero");
            System.exit(1);
        }
    }

    /**
     * The GenericSupplier class receives an item order from the
     * vendor and sends a message accepting or refusing it.
     */
    public static class GenericSupplier extends Thread {
        final String PRODUCT_NAME;
        boolean ready = false;
        int quantity = 0;

        /**
         * Constructor.  Instantiates the supplier as the
         * supplier for the kind of item to be ordered.
         *
         * @param itemName    the name of the item being ordered
         */
        public GenericSupplier(String itemName) {
            PRODUCT_NAME = itemName;
        }

        /**
         * Timer method. Completes when ready is set to true, after
         * connection is started.
         */
        void waitForTopicConsumer() {
            while (!(ready)) {
            }
        }

        /**
         * Checks to see if there are enough items in inventory.
         * Rather than go to a database, it generates a random
         * number related to the order quantity, so that some of
         * the time there won't be enough in stock.
         *
         * @return    the number of items in inventory
         */
        public int checkInventory() {
            Random rgen = new Random();

            return (rgen.nextInt(quantity * 5));
        }

        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageConsumer receiver = null;
            Message inMessage = null;
            MapMessage orderMessage = null;
            MapMessage outMessage = null;

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(true, 0);
            } catch (Exception e) {
                System.err.println("Connection problem: " + e.toString());
                System.err.println(
                        "Program assumes three "
                        + "queues named jms/AQueue, jms/BQueue, and jms/CQueue "
                        + "and one topic named jms/OTopic");

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * Create receiver for order topic and start message
             * delivery.
             */
            try {
                receiver = session.createConsumer(supplierOrderTopic);
                connection.start();
            } catch (JMSException je) {
            }

            // Connection has started, set ready to true
            ready = true;

            /*
             * Keep checking supplier order topic for order
             * request until end-of-message-stream message is
             * received. Receive order and send an order
             * confirmation as one transaction.
             */
            while (true) {
                try {
                    inMessage = receiver.receive();

                    if (inMessage instanceof MapMessage) {
                        orderMessage = (MapMessage) inMessage;
                    } else {
                        /*
                         * Message is an end-of-message-stream
                         * message. Send a similar message to
                         * reply queue, commit transaction, then
                         * stop processing orders by breaking out
                         * of loop.
                         */
                        MessageProducer producer = session.createProducer(
                                    inMessage.getJMSReplyTo());
                        producer.send(session.createMessage());
                        session.commit();

                        break;
                    }

                    /*
                     * Extract quantity ordered from order
                     * message.
                     */
                    quantity = orderMessage.getInt("Quantity");
                    System.out.println(
                            PRODUCT_NAME + " Supplier: Vendor ordered "
                            + quantity + " " + PRODUCT_NAME + "(s)");

                    /*
                     * Create sender and message for reply queue.
                     * Set order number and item; check inventory
                     * and set quantity available.
                     * Send message to vendor and commit
                     * transaction.
                     */
                    MessageProducer producer = session.createProducer(
                                (Queue) orderMessage.getJMSReplyTo());
                    outMessage = session.createMapMessage();
                    outMessage.setInt(
                            "VendorOrderNumber",
                            orderMessage.getInt("VendorOrderNumber"));
                    outMessage.setString("Item", PRODUCT_NAME);

                    int numAvailable = checkInventory();

                    if (numAvailable >= quantity) {
                        outMessage.setInt("Quantity", quantity);
                    } else {
                        outMessage.setInt("Quantity", numAvailable);
                    }

                    producer.send(outMessage);
                    System.out.println(
                            PRODUCT_NAME + " Supplier: sent "
                            + outMessage.getInt("Quantity") + " "
                            + outMessage.getString("Item") + "(s)");
                    session.commit();
                    System.out.println(
                            "  " + PRODUCT_NAME
                            + " Supplier: committed transaction");
                } catch (Exception e) {
                    System.err.println(
                            PRODUCT_NAME + " Supplier: Exception occurred: "
                            + e.toString());
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    /**
     * The Order class represents a Retailer order placed with a
     * Vendor. It maintains a table of pending orders.
     */
    public static class Order {
        private static Hashtable<Integer, Order> pendingOrders = new Hashtable<Integer, Order>();
        private static int nextOrderNumber = 1;
        private static final int PENDING_STATUS = 1;
        private static final int CANCELLED_STATUS = 2;
        private static final int FULFILLED_STATUS = 3;

        // Original order from retailer
        public final MapMessage order;
        public final int orderNumber;

        // Reply from supplier
        public MapMessage monitor = null;

        // Reply from supplier
        public MapMessage storage = null;
        public int quantity;
        int status;

        /**
         * Constructor.  Sets order number; sets order and
         * quantity from incoming message. Sets status to
         * pending, and adds order to hash table of pending
         * orders.
         *
         * @param order    the message containing the order
         */
        public Order(MapMessage order) {
            this.orderNumber = getNextOrderNumber();
            this.order = order;

            try {
                this.quantity = order.getInt("Quantity");
            } catch (JMSException je) {
                System.err.println(
                        "Unexpected error. Message " + "missing Quantity");
                this.quantity = 0;
            }

            status = PENDING_STATUS;
            pendingOrders.put(
                new Integer(orderNumber),
                this);
        }

        /**
         * Returns the next order number and increments the
         * static variable that holds this value.
         *
         * @return    the next order number
         */
        private static int getNextOrderNumber() {
            int result = nextOrderNumber;
            nextOrderNumber++;

            return result;
        }

        /**
         * Returns the number of orders in the hash table.
         *
         * @return    the number of pending orders
         */
        public static int outstandingOrders() {
            return pendingOrders.size();
        }

        /**
         * Returns the order corresponding to a given order
         * number.
         *
         * @param orderNumber   the number of the requested order
         * @return              the requested order
         */
        public static Order getOrder(int orderNumber) {
            return (Order) pendingOrders.get(new Integer(orderNumber));
        }

        /**
         * Called by the onMessage method of the
         * VendorMessageListener class to process a reply from
         * a supplier to the Vendor.
         *
         * @param component    the message from the supplier
         * @return             the order with updated status
         *                     information
         */
        public Order processSubOrder(MapMessage component) {
            String itemName = null;

            // Determine which subcomponent this is.
            try {
                itemName = component.getString("Item");
            } catch (JMSException je) {
                System.err.println(
                        "Unexpected exception. " + "Message missing Item");
            }

            if (itemName.compareTo("Monitor") == 0) {
                monitor = component;
            } else if (itemName.compareTo("Hard Drive") == 0) {
                storage = component;
            }

            /*
             * If notification for all subcomponents has been
             * received, verify the quantities to compute if able
             * to fulfill order.
             */
            if ((monitor != null) && (storage != null)) {
                try {
                    if (quantity > monitor.getInt("Quantity")) {
                        status = CANCELLED_STATUS;
                    } else if (quantity > storage.getInt("Quantity")) {
                        status = CANCELLED_STATUS;
                    } else {
                        status = FULFILLED_STATUS;
                    }
                } catch (JMSException je) {
                    System.err.println(
                            "Unexpected exception: " + je.toString());
                    status = CANCELLED_STATUS;
                }

                /*
                 * Processing of order is complete, so remove it
                 * from pending-order list.
                 */
                pendingOrders.remove(new Integer(orderNumber));
            }

            return this;
        }

        /**
         * Determines if order status is pending.
         *
         * @return    true if order is pending, false if not
         */
        public boolean isPending() {
            return status == PENDING_STATUS;
        }

        /**
         * Determines if order status is cancelled.
         *
         * @return    true if order is cancelled, false if not
         */
        public boolean isCancelled() {
            return status == CANCELLED_STATUS;
        }

        /**
         * Determines if order status is fulfilled.
         *
         * @return    true if order is fulfilled, false if not
         */
        public boolean isFulfilled() {
            return status == FULFILLED_STATUS;
        }
    }

    /**
     * The Retailer class orders a number of computers by sending
     * a message to a vendor.  It then waits for the order to be
     * confirmed.
     *
     * In this example, the Retailer places two orders, one for
     * the quantity specified on the command line and one for
     * twice that number.
     *
     * This class does not use transactions.
     */
    public static class Retailer extends Thread {
        int quantity = 0;

        /**
         * Constructor.  Instantiates the retailer with the
         * quantity of computers being ordered.
         *
         * @param q    the quantity specified in the program
         *             arguments
         */
        public Retailer(int q) {
            quantity = q;
        }

        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            MessageProducer producer = null;
            MapMessage outMessage = null;
            MessageConsumer orderConfirmReceiver = null;
            MapMessage inMessage = null;

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(
                            false,
                            Session.AUTO_ACKNOWLEDGE);
            } catch (Exception e) {
                System.err.println("Connection problem: " + e.toString());
                System.err.println(
                        "Program assumes three "
                        + "queues named jms/AQueue, jms/BQueue, and jms/CQueue "
                        + "and one topic named jms/OTopic");

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            /*
             * Create non-transacted session and sender for
             * vendor order queue.
             * Create message to vendor, setting item and
             * quantity values.
             * Send message.
             * Create receiver for retailer confirmation queue.
             * Get message and report result.
             * Send an end-of-message-stream message so vendor
             * will stop processing orders.
             */
            try {
                producer = session.createProducer(vendorOrderQueue);
                outMessage = session.createMapMessage();
                outMessage.setString("Item", "Computer(s)");
                outMessage.setInt("Quantity", quantity);
                outMessage.setJMSReplyTo(retailerConfirmQueue);
                producer.send(outMessage);
                System.out.println(
                        "Retailer: ordered " + quantity + " computer(s)");

                orderConfirmReceiver = session.createConsumer(
                            retailerConfirmQueue);
                connection.start();
                inMessage = (MapMessage) orderConfirmReceiver.receive();

                if (inMessage.getBoolean("OrderAccepted") == true) {
                    System.out.println("Retailer: Order filled");
                } else {
                    System.out.println("Retailer: Order not " + "filled");
                }

                System.out.println("Retailer: placing another " + "order");
                outMessage.setInt("Quantity", quantity * 2);
                producer.send(outMessage);
                System.out.println(
                        "Retailer: ordered " + outMessage.getInt("Quantity")
                        + " computer(s)");
                inMessage = (MapMessage) orderConfirmReceiver.receive();

                if (inMessage.getBoolean("OrderAccepted") == true) {
                    System.out.println("Retailer: Order filled");
                } else {
                    System.out.println("Retailer: Order not " + "filled");
                }

                /*
                 * Send a non-text control message indicating end
                 * of messages.
                 */
                producer.send(session.createMessage());
            } catch (Exception e) {
                System.err.println(
                        "Retailer: Exception " + "occurred: " + e.toString());
                e.printStackTrace();
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
     * The Vendor class uses one transaction to receive the
     * computer order from the retailer and order the needed
     * number of monitors and disk drives from its suppliers.
     * At random intervals, it throws an exception to simulate a
     * database problem and cause a rollback.
     *
     * The class uses an asynchronous message listener to process
     * replies from suppliers. When all outstanding supplier
     * inquiries complete, it sends a message to the Retailer
     * accepting or refusing the order.
     */
    public static class Vendor extends Thread {
        Random rgen = new Random();
        int throwException = 1;

        /**
         * Runs the thread.
         */
        public void run() {
            Connection connection = null;
            Session session = null;
            Session asyncSession = null;
            MessageConsumer vendorOrderReceiver = null;
            MessageProducer supplierOrderProducer = null;
            MapMessage orderMessage = null;
            MessageConsumer vendorConfirmReceiver = null;
            VendorMessageListener listener = null;
            Message inMessage = null;
            MapMessage vendorOrderMessage = null;
            Message endOfMessageStream = null;
            Order order = null;
            int quantity = 0;

            try {
                connection = connectionFactory.createConnection();
                session = connection.createSession(true, 0);
                asyncSession = connection.createSession(true, 0);
            } catch (Exception e) {
                System.err.println("Connection problem: " + e.toString());
                System.err.println(
                        "Program assumes three "
                        + "queues named jms/AQueue, jms/BQueue, and jms/CQueue "
                        + "and one topic named jms/OTopic");

                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException ee) {
                    }
                }

                System.exit(1);
            }

            try {
                /*
                 * Create receiver for vendor order queue, sender
                 * for supplier order topic, and message to send
                 * to suppliers.
                 */
                vendorOrderReceiver = session.createConsumer(vendorOrderQueue);
                supplierOrderProducer = session.createProducer(
                            supplierOrderTopic);
                orderMessage = session.createMapMessage();

                /*
                 * Configure an asynchronous message listener to
                 * process supplier replies to inquiries for
                 * parts to fill order.  Start delivery.
                 */
                vendorConfirmReceiver = asyncSession.createConsumer(
                            vendorConfirmQueue);
                listener = new VendorMessageListener(asyncSession, 2);
                vendorConfirmReceiver.setMessageListener(listener);
                connection.start();

                /*
                 * Process orders in vendor order queue.
                 * Use one transaction to receive order from
                 * order queue and send message to suppliers'
                 * order topic to order components to fulfill
                 * the order placed with the vendor.
                 */
                while (true) {
                    try {
                        // Receive an order from a retailer.
                        inMessage = vendorOrderReceiver.receive();

                        if (inMessage instanceof MapMessage) {
                            vendorOrderMessage = (MapMessage) inMessage;
                        } else {
                            /*
                             * Message is an end-of-message-
                             * stream message from retailer.
                             * Send similar messages to
                             * suppliers, then break out of
                             * processing loop.
                             */
                            endOfMessageStream = session.createMessage();
                            endOfMessageStream.setJMSReplyTo(
                                    vendorConfirmQueue);
                            supplierOrderProducer.send(endOfMessageStream);
                            session.commit();

                            break;
                        }

                        /*
                         * A real application would check an
                         * inventory database and order only the
                         * quantities needed.  Throw an exception
                         * every few times to simulate a database
                         * concurrent-access exception and cause
                         * a rollback.
                         */
                        if (rgen.nextInt(3) == throwException) {
                            throw new JMSException(
                                    "Simulated "
                                    + "database concurrent access "
                                    + "exception");
                        }

                        /*
                         * Record retailer order as a pending
                         * order.
                         */
                        order = new Order(vendorOrderMessage);

                        /*
                         * Set order number and reply queue for
                         * outgoing message.
                         */
                        orderMessage.setInt(
                                "VendorOrderNumber",
                                order.orderNumber);
                        orderMessage.setJMSReplyTo(vendorConfirmQueue);
                        quantity = vendorOrderMessage.getInt("Quantity");
                        System.out.println(
                                "Vendor: Retailer " + "ordered " + quantity
                                + " " + vendorOrderMessage.getString("Item"));

                        // Send message to supplier topic.
                        // Item is not used by supplier.
                        orderMessage.setString("Item", "");
                        orderMessage.setInt("Quantity", quantity);
                        supplierOrderProducer.send(orderMessage);
                        System.out.println(
                                "Vendor: ordered " + quantity
                                + " monitor(s) and hard drive(s)");

                        // Commit session.
                        session.commit();
                        System.out.println(
                                "  Vendor: " + "committed transaction 1");
                    } catch (JMSException e) {
                        System.err.println(
                                "Vendor: " + "JMSException occurred: "
                                + e.toString());
                        e.printStackTrace();
                        session.rollback();
                        System.err.println(
                                "  Vendor: rolled " + "back transaction 1");
                    }
                }

                // Wait till suppliers get back with answers.
                listener.monitor.waitTillDone();
            } catch (JMSException e) {
                System.err.println(
                        "Vendor: Exception " + "occurred: " + e.toString());
                e.printStackTrace();
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
     * The VendorMessageListener class processes an order
     * confirmation message from a supplier to the vendor.
     *
     * It demonstrates the use of transactions within message
     * listeners.
     */
    public static class VendorMessageListener implements MessageListener {
        final SampleUtilities.DoneLatch monitor = new SampleUtilities.DoneLatch();
        int numSuppliers;
        private final Session session;

        /**
         * Constructor.  Instantiates the message listener with
         * the session of the consuming class (the vendor).
         *
         * @param s              the session of the consumer
         * @param numSuppliers   the number of suppliers
         */
        public VendorMessageListener(
            Session s,
            int numSuppliers) {
            this.session = s;
            this.numSuppliers = numSuppliers;
        }

        /**
         * Casts the message to a MapMessage and processes the
         * order. A message that is not a MapMessage is
         * interpreted as the end of the message stream, and the
         * message listener sets its monitor state to all done
         * processing messages.
         *
         * Each message received represents a fulfillment message
         * from a supplier.
         *
         * @param message    the incoming message
         */
        public void onMessage(Message message) {
            /*
             * If message is an end-of-message-stream message and
             * this is the last such message, set monitor status
             * to all done processing messages and commit
             * transaction.
             */
            if (!(message instanceof MapMessage)) {
                if (Order.outstandingOrders() == 0) {
                    numSuppliers--;

                    if (numSuppliers == 0) {
                        monitor.allDone();
                    }
                }

                try {
                    session.commit();
                } catch (JMSException je) {
                }

                return;
            }

            /*
             * Message is an order confirmation message from a
             * supplier.
             */
            int orderNumber = -1;

            try {
                MapMessage component = (MapMessage) message;

                /*
                 * Process the order confirmation message and
                 * commit the transaction.
                 */
                orderNumber = component.getInt("VendorOrderNumber");

                Order order = Order.getOrder(orderNumber)
                                   .processSubOrder(component);
                session.commit();

                /*
                 * If this message is the last supplier message,
                 * send message to Retailer and commit
                 * transaction.
                 */
                if (!order.isPending()) {
                    System.out.println(
                            "Vendor: Completed " + "processing for order "
                            + order.orderNumber);

                    Queue replyQueue = (Queue) order.order.getJMSReplyTo();
                    MessageProducer producer = session.createProducer(
                                replyQueue);
                    MapMessage retailerConfirmMessage = session.createMapMessage();

                    if (order.isFulfilled()) {
                        retailerConfirmMessage.setBoolean(
                                "OrderAccepted",
                                true);
                        System.out.println(
                                "Vendor: sent " + order.quantity
                                + " computer(s)");
                    } else if (order.isCancelled()) {
                        retailerConfirmMessage.setBoolean(
                                "OrderAccepted",
                                false);
                        System.out.println(
                                "Vendor: unable to " + "send " + order.quantity
                                + " computer(s)");
                    }

                    producer.send(retailerConfirmMessage);
                    session.commit();
                    System.out.println(
                            "  Vendor: committed " + "transaction 2");
                }
            } catch (JMSException je) {
                je.printStackTrace();

                try {
                    session.rollback();
                } catch (JMSException je2) {
                }
            } catch (Exception e) {
                e.printStackTrace();

                try {
                    session.rollback();
                } catch (JMSException je2) {
                }
            }
        }
    }
}
