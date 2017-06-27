/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.EmailBean;
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
 * @author user
 */
public class EmailUtil {
	
	public boolean sendEmail(EmailBean ebean){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("teamwork.codereview@gmail.com","teamwork123");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("teamwork.codereview@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(ebean.getTo()));
			message.setSubject(ebean.getSubject());
			//message.setText("Dear Mail Crawler," +
					//"\n\n No spam to my email, please!");
			
			message.setText(ebean.getMessage());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return true;
	}
	
}
