/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.EmailTemplateBean;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.utils.EmailUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class EmailController {
	
	
@ModelAttribute("EmailBean")
 public EmailTemplateBean PopulateLoginBean() 
{
   return new EmailTemplateBean(); 
}
	
@RequestMapping(value="/Email",method=RequestMethod.GET)
public String sendMail()
{
	return "Email";
}

@RequestMapping(value="/sendMail",method=RequestMethod.POST)
public ModelAndView sendMail(String st)
{
	EmailUtil emailUtil=new EmailUtil();
	
	boolean b=emailUtil.sendEmail("EmailTo","EmailSubject","EmailMessage");
	
	if(b==true){
		return new ModelAndView( "Email","success","Email Sent Successfully"  );
	}else{
		return new ModelAndView( "Email","success","Email Not Sent Successfully"  );
	}

}

@RequestMapping(value="/createEmailTemp",method=RequestMethod.POST)
public ModelAndView createEmailTemplate(@ModelAttribute("EmailBean")EmailTemplateBean emailBean,HttpServletRequest req)
{
return new ModelAndView( "Email","success","Email Template Created Successfully"  );
	
}

@RequestMapping(value="/updateEmailTemp",method=RequestMethod.POST)
public ModelAndView updateEmailTemplate()
{

return new ModelAndView( "Email","success","Email Template Updated Successfully"  );
}

@RequestMapping(value="/listEmailTemp",method=RequestMethod.POST)
public ModelAndView listEmailTemplate()
{

return new ModelAndView( "Email");
}


@RequestMapping(value="/deleteEmailTemp",method=RequestMethod.POST)
public ModelAndView deleteEmailTemplate()
{

return new ModelAndView( "Email","success","Email Template Deleted Successfully"  );
}



	
}
