/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.UserDataBean;

import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.Paladion.teamwork.utils.SystemInfo;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.apache.commons.lang.RandomStringUtils;
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
@Qualifier(value="CommonUtil")
 CommonUtil CU;
 
 @Autowired
@Qualifier(value="UserService")
 UserService userService;
 
 @Autowired
@Qualifier(value="ProjectService")
 ProjectService PS;
 
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
               System.out.println("==================LOGS start========================");
               System.out.println("use log4j to log these details for security reason");
               System.out.println("system name:"+SystemInfo.getSystemName());
               System.out.println("IP address: "+SystemInfo.getIPAddress());
               System.out.println("MAC address: "+SystemInfo.getMAC());
               System.out.println("==================LOGS end========================");
               
               
                      HttpSession LoginSess=req.getSession(true);
                      LoginSess.setAttribute("Luser", lb);
                      
                      String token = RandomStringUtils.random(30, 0, 0, true, true, null, new SecureRandom());
                      LoginSess.setAttribute("AntiCsrfToken",token);
                      if(!lb.getRole().equalsIgnoreCase("engineer")){
                      LoginSess.setAttribute("AllUsers", userService.GetAllUser());
                      }
	            return new ModelAndView("redirect:/Welcome.do");
           }
           else {
           return new ModelAndView("Login","Lerror", "Incorrect Username or Password");
           }
 }

@RequestMapping(value="/Welcome",method=RequestMethod.GET)
public ModelAndView Welcome(HttpServletRequest req)
{
            ModelAndView result=new ModelAndView("Welcome");
            int [] counts = new int[3];
            counts = PS.getProjectsCount(req);
            result.addObject("All_proj",counts[0]);
            result.addObject("New_proj",counts[3]);
            result.addObject("Progress_proj",counts[2]);
            result.addObject("Completed_proj",counts[1]);
            return result;
}

@RequestMapping(value="/Logout",method=RequestMethod.GET)
public String Logout(HttpServletRequest req)
   {
           LS.Logout(req.getSession(false));
           return "redirect:Login.do";
   }

@RequestMapping(value="/index",method=RequestMethod.GET)
public String index()
{
    // change to login
    return "index";
}

}
