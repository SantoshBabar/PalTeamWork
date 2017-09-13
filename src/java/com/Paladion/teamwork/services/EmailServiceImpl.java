/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.EmailDAO;
import com.Paladion.teamwork.DAO.LoginDAO;
import com.Paladion.teamwork.beans.EmailTemplateBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class EmailServiceImpl implements EmailService{
	
    @Autowired
    @Qualifier(value="EmailDAO")
    EmailDAO emailDAO;

	@Override
	public boolean createEmailTemplate(EmailTemplateBean emailTempBean) {
		return emailDAO.createEmailTemplate(emailTempBean);
	}

	@Override
	public boolean updateEmailTemplate(EmailTemplateBean emailTempBean) {
		return emailDAO.updateEmailTemplate(emailTempBean);
	}

	@Override
	public List<EmailTemplateBean> listEmailTemplate() {
		return emailDAO.listEmailTemplate();
	}

	@Override
	public boolean deleteEmailTemplate(EmailTemplateBean emailTempBean) {
		return emailDAO.deleteEmailTemplate(emailTempBean);
	}
	
	
	
}
