package com.inbravo.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author amit.dixit
 *
 */
public final class MailTest {

	public static void main(final String... args) {

		final String username = args[0];
		final String password = args[1];

		final Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "mail.impetus.co.in");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.impetus.co.in");
		props.put("mail.smtp.port", "587");

		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected final PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amit.dixit@impetus.co.in"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("arunkumar.m@impetus.co.in"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (final MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
