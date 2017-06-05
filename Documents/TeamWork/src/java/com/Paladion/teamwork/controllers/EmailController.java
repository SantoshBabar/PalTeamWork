/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.utils.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class EmailController {
	
	@RequestMapping(value="/sendMail",method=RequestMethod.GET)
public ModelAndView sendMail()
{
	EmailUtil emailUtil=new EmailUtil();
	
	boolean b=emailUtil.sendEmail("EmailTo","EmailSubject","EmailMessage");
	
	if(b==true){
		return new ModelAndView( "Mail","Success","Email Sent Successfully"  );
	}else{
		return new ModelAndView( "Mail","Success","Email Not Sent Successfully"  );
	}

}
	
}
