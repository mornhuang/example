/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package confirmer.ejb;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
public class ConfirmerBean implements Confirmer {
    private static final String mailer = "JavaMailer";
    private static Logger logger = Logger.getLogger(
                "confirmer.ejb.ConfirmerBean");
    @Resource(name = "mail/myMailSession")
    private Session session;

    /** Creates a new instance of ConfirmerBean */
    public ConfirmerBean() {
    }

    public void sendNotice(String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient, false));
            message.setSubject("Test Message from ConfirmerBean");

            DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                        DateFormat.LONG,
                        DateFormat.SHORT);
            Date timeStamp = new Date();

            String messageText = "Thank you for your order." + '\n'
                + "We received your order on "
                + dateFormatter.format(timeStamp) + ".";
            message.setText(messageText);
            message.setHeader("X-Mailer", mailer);
            message.setSentDate(timeStamp);

            // Send message
            Transport.send(message);

            logger.info("Mail sent to " + recipient + ".");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            logger.info("Error in ConfirmerBean for " + recipient);
        }
    }
}
