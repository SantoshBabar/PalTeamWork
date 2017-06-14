/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import net.tanesha.recaptcha.ReCaptchaImpl;
//import net.tanesha.recaptcha.ReCaptchaResponse;
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
 
 
 UserBean ub=null;
 LoginBean lb=null;
 
 
 @ModelAttribute("LoginM")
 public LoginBean PopulateLoginBean() 
{
   return new LoginBean(); // populates form for the first time if its null
}
 
 
 
@RequestMapping(value="/Login",method=RequestMethod.GET)
public String Login()
{
    // change to login
return "Login";
}



@RequestMapping(value="/ForgotPassword",method=RequestMethod.GET)
public String forgot()
{
 
return "ForgotPassword";
}

@RequestMapping(value="/ResetPassword",method=RequestMethod.GET)
public String Reset()
{
 
return "ResetPassword";
}

@RequestMapping(value="/Forgot",method=RequestMethod.GET)
public String forgot1()
{
 
return "ForgotPassword";
}



//forgot password starts
@RequestMapping(value="/ForgotPassword",method=RequestMethod.POST)
public ModelAndView Forgot(@ModelAttribute("ForgotM")LoginBean LB,HttpServletRequest req )
    {
//        String remoteAddr = req.getRemoteAddr();
//		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//		reCaptcha.setPrivateKey("6LdILiQUAAAAAPJwovQaU6ezxtcIoa2FEFS70KgO");
//
//		String challenge = req.getParameter("recaptcha_challenge_field");
//		String uresponse = req.getParameter("recaptcha_response_field");
//		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
//
//		if (reCaptchaResponse.isValid()) {
//			String user = req.getParameter("user");
//			               
//		} else {
//			 return new ModelAndView("ForgotPassword","Lerror", "Captcha failed");
//		}
           System.out.println("forgotPassword");
           lb=LS.ForgotPassword(LB);
           if (lb!=null) {
            HttpSession LoginSess=req.getSession(true);
            LoginSess.setAttribute("Luser", ub);
            return new ModelAndView("ResetPassword");
           }
           else {
           return new ModelAndView("ForgotPassword","Lerror", "failed");
           }
}
//////////////////////////


@RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
public ModelAndView ResetPassword(@ModelAttribute("ForgotM")LoginBean LB,HttpServletRequest req )
    {
           System.out.println("ResetPassword");
           lb=LS.ResetPassword(LB);
           if (lb!=null) {
            HttpSession LoginSess=req.getSession(true);
            LoginSess.setAttribute("Luser", ub);
            return new ModelAndView("Login","Lerror", "password updated successfully");
           }
           else {
           return new ModelAndView("ResetPassword","Lerror", "incorrect OTP");
           }
}

//forgot password ends
 
@RequestMapping(value="/Login",method=RequestMethod.POST)
public ModelAndView Login(@ModelAttribute("LoginM")LoginBean LB,HttpServletRequest req )
    {
//        String remoteAddr = req.getRemoteAddr();
//		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//		reCaptcha.setPrivateKey("6LdILiQUAAAAAPJwovQaU6ezxtcIoa2FEFS70KgO");
//
//		String challenge = req
//				.getParameter("recaptcha_challenge_field");
//		String uresponse = req.getParameter("recaptcha_response_field");
//		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(
//				remoteAddr, challenge, uresponse);
//
//		if (reCaptchaResponse.isValid()) {
//			String user = req
//					.getParameter("user");
//			               
//		} else {
//			 return new ModelAndView("Login","Lerror", "Captcha failed");
//		}
           System.out.println("in login");
           lb=LS.Login(LB);
           if (lb!=null) {
                      HttpSession LoginSess=req.getSession(true);
                      LoginSess.setAttribute("Luser", lb);
           
	            return new ModelAndView("redirect:/Welcome.do");
	}
           else {
           return new ModelAndView("Login","Lerror", "Login Failed");
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
           LS.Logout(req.getSession());
           return "redirect:Login.do";
   }

}

   
