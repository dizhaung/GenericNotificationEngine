package com.bjk.flink.kafka;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class frames the Full EMail and sends the email.
 * 
 * @author Yogesh Gaikwad
 */
public class MailRunner {

	private static Logger logger = LoggerFactory
			.getLogger("EMAIL_ALERTER_STREAMING");

	public void postMail(String value) {
		System.out
				.println("Start !!!  Email send event is just starting for this email Id   !!!!!!!!  : "
						+ value);

		Properties props = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("C:/GenericNotificationEngine/FLINK/dataset/config.properties");

			// load a properties file
			props.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			String host = "smtp.gmail.com";
			final String user = props.getProperty("username");
			final String password = props.getProperty("password");

			String to = value;

			// Get the session object

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.properties.mail.smtp.socketFactory.fallback",
					"false");

			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});

			// Compose the message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				message.setSubject("Email Notification");
				message.setText("This is simple email notification ! Please respone once  you received it !! ");

				// send the message
				Transport.send(message);

				System.out.println("message sent successfully...");

			} catch (MessagingException e) {
				e.printStackTrace();
			}

			System.out
					.println("End !!!  Email Notification event is completed for this email Id   !!!!!!!!   "
							+ value);
		}
	}

}