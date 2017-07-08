/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.UserDataBean;

import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 *
 * @author Administrator
 */
@Controller
public class LoginController {
    
 public LoginController() 
 {   }
 
 @Autowired
 @Qualifier(value="LoginService")
 LoginService LS;
 
 @Autowired
@Qualifier(value="UserService")
 UserService userService;
 
 UserDataBean lb=null;
 
 
 @ModelAttribute("LoginM")
 public UserDataBean PopulateLoginBean() 
{
   return new UserDataBean(); // populates form for the first time if its null
}
 
 
 
@RequestMapping(value="/Login",method=RequestMethod.GET)
public String Login()
{
    // change to login
    return "Login";
}

 
@RequestMapping(value="/Login",method=RequestMethod.POST)
public ModelAndView Login(@ModelAttribute("LoginM")UserDataBean LB,HttpServletRequest req )
    {
        //Captcha code begins
        String remoteAddr = req.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	reCaptcha.setPrivateKey("6LdILiQUAAAAAPJwovQaU6ezxtcIoa2FEFS70KgO");

	String challenge = req.getParameter("recaptcha_challenge_field");
	String uresponse = req.getParameter("recaptcha_response_field");
	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
	remoteAddr, challenge, uresponse);

	if (reCaptchaResponse.isValid()) {
		String user = req.getParameter("user");
            } else {
			return new ModelAndView("Login","Lerror", "Captcha failed");
		}
         //Captha code ends  
        
   
    
        System.out.println("in login");
           lb=LS.Login(LB);
           if (lb!=null) {
                      HttpSession LoginSess=req.getSession(true);
                      LoginSess.setAttribute("Luser", lb);
                      LoginSess.setAttribute("AllUsers", userService.GetAllUser());
	            return new ModelAndView("redirect:/Welcome.do");
           }
           else {
           return new ModelAndView("Login","Message", "Login Failed");
           }
 }

@RequestMapping(value="/Welcome",method=RequestMethod.GET)
public ModelAndView Welcome()
{
   
           return new ModelAndView("Welcome");
}

@RequestMapping(value="/Logout",method=RequestMethod.GET)
public String Logout(HttpServletRequest req)
   {
           LS.Logout(req.getSession(false));
           return "redirect:Login.do";
   }

}

   
