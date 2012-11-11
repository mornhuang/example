/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package sb;

import java.util.Random;
import java.util.logging.Logger;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Remote;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Bean class for Publisher enterprise bean. Defines publishNews
 * business method as well as required methods for a stateless
 * session bean.
 */
@Stateless
@Remote({
    PublisherRemote.class
})
public class PublisherBean implements PublisherRemote {
    static final String[] messageTypes = {
            "Nation/World", "Metro/Region", "Business", "Sports", "Living/Arts",
            "Opinion"
        };
    static final Logger logger = Logger.getLogger("PublisherBean");
    Connection connection = null;
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource
    private SessionContext sc;
    @Resource(mappedName = "jms/Topic")
    private Topic topic;

    public PublisherBean() {
    }

    /**
     * Creates the connection.
     */
    @PostConstruct
    public void makeConnection() {
        try {
            connection = connectionFactory.createConnection();
        } catch (Throwable t) {
            // JMSException could be thrown
            logger.severe(
                    "PublisherBean.makeConnection:" + "Exception: "
                    + t.toString());
        }
    }

    /**
     * Chooses a message type by using the random number
     * generator found in java.util.  Called by publishNews().
     *
     * @return   the String representing the message type
     */
    private String chooseType() {
        int whichMsg;
        Random rgen = new Random();

        whichMsg = rgen.nextInt(messageTypes.length);

        return messageTypes[whichMsg];
    }

    /**
     * Creates session, publisher, and message.  Publishes
     * messages after setting their NewsType property and using
     * the property value as the message text. Messages are
     * received by MessageBean, a message-driven bean that uses a
     * message selector to retrieve messages whose NewsType
     * property has certain values.
     */
    public void publishNews() {
        Session session = null;
        MessageProducer publisher = null;
        TextMessage message = null;
        int numMsgs = messageTypes.length * 3;
        String messageType = null;

        try {
            session = connection.createSession(true, 0);
            publisher = session.createProducer(topic);
            message = session.createTextMessage();

            for (int i = 0; i < numMsgs; i++) {
                messageType = chooseType();
                message.setStringProperty("NewsType", messageType);
                message.setText("Item " + i + ": " + messageType);
                logger.info(
                        "PUBLISHER: Setting " + "message text to: "
                        + message.getText());
                publisher.send(message);
            }
        } catch (Throwable t) {
            // JMSException could be thrown
            logger.severe(
                    "PublisherBean.publishNews: " + "Exception: "
                    + t.toString());
            sc.setRollbackOnly();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                }
            }
        }
    }

    /**
     * Closes the connection.
     */
    @PreDestroy
    public void endConnection() throws RuntimeException {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
