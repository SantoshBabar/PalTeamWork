/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.EmailBean;
import com.Paladion.teamwork.beans.EmailTemplateBean;
import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.services.EmailService;
import com.Paladion.teamwork.utils.EmailUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class EmailController {
	
	
@Autowired
@Qualifier(value="EmailService")
EmailService emailService;
	
	
@ModelAttribute("EmailBean")
 public EmailTemplateBean PopulateLoginBean() 
{
   return new EmailTemplateBean(); 
}
	
@RequestMapping(value="/Email",method=RequestMethod.GET)
public ModelAndView sendMail()
	   
{
	List EmailTemplateBean=emailService.listEmailTemplate();
	
	System.out.println(EmailTemplateBean);
	
	
	return new ModelAndView("Email", "emailList", EmailTemplateBean);
	
	}

@RequestMapping(value="/createEmailTemp",method=RequestMethod.POST)
public String createEmailTemplate(@ModelAttribute("EmailBean")EmailTemplateBean emailBean,HttpServletRequest req)
{
    emailService.createEmailTemplate(emailBean);
    return "Email";
	
}

@RequestMapping(value="/EmailUpdateTemp/{id}",method=RequestMethod.GET)
public String updateEmailTemplate(@PathVariable("id") int id)
{
	System.out.println("fgfguiy"+id);
//emailService.updateEmailTemplate();
return "Email";
}

@RequestMapping(value="/deleteEmailTemp/{id}",method=RequestMethod.GET)
public ModelAndView deleteEmailTemplate(@PathVariable("id") int id)
{
	
	System.out.println(id);
	System.out.println("delete email template");
//emailService.deleteEmailTemplate(emailBean);
return new ModelAndView( "Email","success","Email Template Deleted Successfully"  );
}





@RequestMapping(value="/listEmailTemp",method=RequestMethod.POST)
public ModelAndView listEmailTemplate()
{
emailService.listEmailTemplate();
return new ModelAndView( "Email");
}

	
}
