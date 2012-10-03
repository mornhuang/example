package com.itsz.sht.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.naming.Context;

public abstract class MailSender {
	protected Session session;
	Context initCtx;
	Context envCtx;

	protected MailSender() {
	}

	private static final MailSender CONTAINER_SENDER = new ContainerSessionSender();

	public static MailSender getContainerSender() {
		return CONTAINER_SENDER;
	}

	public abstract void send(Mail mail) throws MessagingException;

	private static class ContainerSessionSender extends MailSender {
		private Session getSession() throws MessagingException {
			try {
				if (session == null) {
					
					Authenticator auth = new PopupAuthenticator();
					Properties mailProps = new Properties();
					mailProps.put("mail.smtp.host", AdminConfigUtil.getInstance().getEmailHost());
					mailProps.put("mail.smtp.auth", "false");
					session = Session.getDefaultInstance(mailProps, auth);
					session.setDebug(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new MessagingException("Failed to get mail session", e);
			}
			return session;
		}

		public void send(Mail mail) throws MessagingException {
			mail.send(getSession());
			// mail.send();
			// MimeMessage newMessage = new MimeMessage(getSession());
			// if (mail.getFrom() == null)
			// newMessage.setFrom();
			// else
			// newMessage.setFrom(mail.getFrom());
			// newMessage.setRecipients(Message.RecipientType.TO,
			// InternetAddress.parse(mail.getAddress()));
			// newMessage.setSubject(mail.getSubject(), "UTF-8");
			// newMessage.setSentDate(new Date());
			// newMessage.setText(mail.getContent(),"text/html;charset=UTF-8");
			// Transport.send(newMessage);
		}
	}

	class PopupAuthenticator extends Authenticator {
		private String username;
		private String password;

		// public PopupAuthenticator(String username,String pwd){
		// this.username=username;
		// this.password=pwd;
		// }
		public PopupAuthenticator() {

		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(this.username, this.password);
		}

	}
}
