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
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
 
 
 @ModelAttribute("LoginM")
 public LoginBean PopulateLoginBean() 
{
   return new LoginBean(); // populates form for the first time if its null
}
 
@RequestMapping(value="/Login",method=RequestMethod.GET)
public String Login()
{
return "Login";
}
 
@RequestMapping(value="/Login",method=RequestMethod.POST)
public ModelAndView Login(@ModelAttribute("LoginM")LoginBean LB)
    {
        System.out.println("in login");
        ub=LS.Login(LB);
        if (ub!=null) {return new ModelAndView("redirect:/Welcome.do");}
        else {
           return new ModelAndView("Login","Lerror", "Login Failed");
        }
        }

@RequestMapping(value="/Welcome",method=RequestMethod.GET)
public ModelAndView Welcome()
{
return new ModelAndView("Welcome");
}
    }

   

