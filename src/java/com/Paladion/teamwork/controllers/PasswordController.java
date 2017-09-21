/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.services.PasswordService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PasswordController {
    
    UserDataBean lb=null;
    
    @Autowired
    @Qualifier(value="PasswordService")
    PasswordService PS;
    
    @ModelAttribute("LoginM")
    public UserDataBean PopulateLoginBean() {
        return new UserDataBean(); 
    }
	
    @RequestMapping(value="/ForgotPassword",method=RequestMethod.GET)
    public String forgotPassword()
    {
        return "ForgotPassword";
    }

    @RequestMapping(value="/ResetPassword",method=RequestMethod.GET)
    public String resetPassword()
    {
        return "ResetPassword";
    }
    
    @RequestMapping(value="/ChangePassword",method=RequestMethod.GET)
    public String changePassword()
    {
        return "ChangePassword";
    }

    //forgot password starts
    @RequestMapping(value="/ForgotPassword",method=RequestMethod.POST)
    public ModelAndView ForgotPassword(HttpServletRequest req )
    {
        String remoteAddr = req.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LdILiQUAAAAAPJwovQaU6ezxtcIoa2FEFS70KgO");
        String challenge = req.getParameter("recaptcha_challenge_field");
	String uresponse = req.getParameter("recaptcha_response_field");
	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
        if (!reCaptchaResponse.isValid()) {
             return new ModelAndView("ForgotPassword","Lerror", "Captcha failed");
	} 
        String emailId=req.getParameter("emailId");
        boolean state=PS.ForgotPassword(emailId);

        if (state==true) {
            ModelAndView result=new ModelAndView("ResetPassword");
            result.addObject("Message","OTP is sent to your registered mailId");
            result.addObject("email",emailId);
            return result;
        }
        else {
            return new ModelAndView("ResetPassword","Message","OTP is sent to your registered mailId");
        }
    }

    @RequestMapping(value="/ResetPassword",method=RequestMethod.POST)
    public ModelAndView ResetPassword(HttpServletRequest req )
    {
        
        String otp=req.getParameter("userOTP");
        String email=req.getParameter("emailId");
        String password=req.getParameter("newPassword");
        String conPassword=req.getParameter("conPassword");
        if(password.equals(conPassword)){
            if(PS.validatePassword(password)){
            boolean state=PS.ResetPassword(otp,email,password);
            ModelAndView result=new ModelAndView("Login");
            result.addObject("Message","Password updated succefully.");
            return result;
            }
            else{
            ModelAndView result1=new ModelAndView("ResetPassword");
            result1.addObject("Message","Not a strong password.");
            result1.addObject("email",email);
            return result1;
            }
        }
        else{
        ModelAndView result1=new ModelAndView("ResetPassword");
        result1.addObject("Message","New Password and Confirm Password do not match.");
        result1.addObject("email",email);
        return result1;
        }
    }
    
    
    @RequestMapping(value="/ChangePassword",method=RequestMethod.POST)
    public ModelAndView ChangePassword(HttpServletRequest req )
    {
        String currentPass=req.getParameter("currentPass");
        String newPass=req.getParameter("newPass");
        String confPass=req.getParameter("confPass");
        
        if(!PS.validatePassword(newPass))
        {
            if(!newPass.equals(confPass)){
            ModelAndView result1=new ModelAndView("ChangePassword");
            result1.addObject("Message","New Password and Confirm Password do not match.");
            return result1;
            }
                
            else{
            HttpSession sess=req.getSession(false);
            UserDataBean uBean=(UserDataBean)sess.getAttribute("Luser");
                
                if(uBean==null || !currentPass.equals(uBean.getPassword())){
                sess.invalidate();
                return new ModelAndView("Login","Message","Forgot your password? Use Forgot Password to reset your password.");
                }
                else{
                PS.UpdatePassword(newPass,uBean.getEmail());
                sess.invalidate();
                return new ModelAndView("Login","Message","Password updated successfully. Please Login with new Password.");
                }
            }
        }
        else{
            ModelAndView result1=new ModelAndView("ChangePassword");
            result1.addObject("Message","Not a strong password.");
            return result1;
        }
    }
}
