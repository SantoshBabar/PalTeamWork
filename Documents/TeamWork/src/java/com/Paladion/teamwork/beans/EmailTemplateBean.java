/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "EmailTemplate",catalog="teamwork")
public class EmailTemplateBean {
	
	@Id
	private int emailTemplateId;
	private String emailTemplateName;
	private String emailTemplateSubject;
	
	@Lob
	private String emailTemplateMessage;

	public int getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(int emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getEmailTemplateName() {
		return emailTemplateName;
	}

	public void setEmailTemplateName(String emailTemplateName) {
		this.emailTemplateName = emailTemplateName;
	}

	public String getEmailTemplateSubject() {
		return emailTemplateSubject;
	}

	public void setEmailTemplateSubject(String emailTemplateSubject) {
		this.emailTemplateSubject = emailTemplateSubject;
	}

	public String getEmailTemplateMessage() {
		return emailTemplateMessage;
	}

	public void setEmailTemplateMessage(String emailTemplateMessage) {
		this.emailTemplateMessage = emailTemplateMessage;
	}

	
}
