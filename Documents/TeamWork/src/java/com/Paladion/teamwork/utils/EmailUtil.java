/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.EmailBean;
import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.services.AdminService;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;


import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 *
 * @author user
 */
public class EmailUtil {
	
@Autowired()
@Qualifier(value = "AdminService")
AdminService Aservice;
public void setAservice(AdminService Aservice) {
        this.Aservice = Aservice;
    }
@ModelAttribute("AdminModel")
    public SystemBean populate() 
    {
        return new SystemBean();
    }
    
	public boolean sendEmail(EmailBean ebean){
            SystemBean syssetting=Aservice.getSystemSettings();
            System.out.println("Server:"+syssetting.getMailserver());
            
            
            
		Properties props = new Properties();
		props.put("mail.0smtp.host", syssetting.getMailserver());
		props.put("mail.smtp.socketFactory.port", syssetting.getPort());
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", syssetting.getPort());

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(syssetting.getMailuser(),syssetting.getMailpass());
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(syssetting.getMailuser()));
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
