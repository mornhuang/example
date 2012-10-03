package com.itsz.sht.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.apache.commons.lang.StringUtils;

public class SimpleHtmlMail extends Mail {

    /**
     * Text part of the message.  This will be used as alternative text if
     * the email client does not support HTML messages.
     */
//    private String text;

    /** Html part of the message */
    private String html;

    /** Embeded images */
    private List inlineImages = new ArrayList();

    /**
     * Set the text content.
     *
     * @param text A String.
     * @return An HtmlEmail.
     */
//    public SimpleHtmlMail setTextMsg(String text)
//    {
//        this.text = text;
//        return this;
//    }

    /**
     * Set the HTML content.
     *
     * @param html A String.
     * @return An HtmlEmail.
     */
    public SimpleHtmlMail setHtmlMsg(String html)
    {
        this.html = html;
        return this;
    }

    /**
     * Set the message.
     *
     * <p>This method overrides the MultiPartEmail setMsg() method in
     * order to send an HTML message instead of a full text message in
     * the mail body. The message is formatted in HTML for the HTML
     * part of the message, it is let as is in the alternate text
     * part.
     *
     * @param msg A String.
     * @return An Email.
     */
    public Mail setMsg(String msg)
    {
//        setTextMsg(msg);

        setHtmlMsg(new StringBuffer()
                .append("<html><body><pre>")
                .append(msg)
                .append("</pre></body></html>")
                .toString());

        return this;
    }

     /**
     * Does the work of actually sending the email.
     *
     * @exception MessagingException if there was an error.
     */
    public void send(Session session) throws MessagingException
    {


        if (StringUtils.isNotEmpty(html))
        {
          setContent(html, TEXT_HTML);
        }else{
            throw new MessagingException("Content is empty!");
        }

        super.send(session);
    }

    public void validate() throws MessagingException {
        setContent(html, TEXT_HTML);
        super.validate();
    }

    /**
     * Validates that the supplied string is neither <code>null</code>
     * nor the empty string.
     *
     * @param foo The text to check.
     * @return Whether valid.
     * @deprecated use StringUtils.isNotEmpty instead
     */
    public static final boolean isValid(String foo)
    {
        return StringUtils.isNotEmpty(foo);
    }
}