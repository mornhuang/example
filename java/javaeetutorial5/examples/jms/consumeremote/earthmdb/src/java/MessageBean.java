/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.ActivationConfigProperty;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.annotation.Resource;
import java.util.logging.Logger;


/**
 * The MessageBean class is a message-driven bean.  It implements
 * the javax.jms.MessageListener interface. It is defined as public
 * (but not final or abstract).
 */
@MessageDriven(mappedName = "jms/Queue")
public class MessageBean implements MessageListener {
    static final Logger logger = Logger.getLogger("MessageBean");
    @Resource
    private MessageDrivenContext mdc;

    public MessageBean() {
    }

    /**
     * onMessage method, declared as public (but not final or
     * static), with a return type of void, and with one argument
     * of type javax.jms.Message.
     *
     * Casts the incoming Message to a TextMessage and displays
     * the text.
     *
     * @param inMessage    the incoming message
     */
    public void onMessage(Message inMessage) {
        TextMessage msg = null;

        try {
            if (inMessage instanceof TextMessage) {
                msg = (TextMessage) inMessage;
                logger.info("MESSAGE BEAN: Message received: " + msg.getText());
            } else {
                logger.warning(
                        "Message of wrong type: "
                        + inMessage.getClass().getName());
            }
        } catch (JMSException e) {
            logger.severe(
                    "MessageBean.onMessage: JMSException: " + e.toString());
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            logger.severe("MessageBean.onMessage: Exception: " + te.toString());
        }
    }
}
