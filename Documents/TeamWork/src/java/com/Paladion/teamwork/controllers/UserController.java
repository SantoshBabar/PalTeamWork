/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import javax.servlet.http.HttpServletRequest;
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
public class UserController {
	
	
@Autowired
@Qualifier(value="UserService")
 UserService userService;
	
@ModelAttribute("LoginM")
 public LoginBean PopulateLoginBean() 
{
   return new LoginBean(); // populates form for the first time if its null
}
	
	@RequestMapping(value="/CreateUser",method=RequestMethod.GET)
     public String createUser()
    {   
//	    userService.getUsersByRole("engineer");
//	    userService.getUsersByRole("lead");
	    return "CreateUser";
    }
	
	@RequestMapping(value="/CreateUser",method=RequestMethod.POST)
public ModelAndView createUser(@ModelAttribute("LoginM")LoginBean loginBean,HttpServletRequest req )
    {
        System.out.println("in user controller create user post method");
    
	   userService.addUser(loginBean);
	   
	   return new ModelAndView("Welcome","TaskSuccess","User Created Successfully");
        }
}
