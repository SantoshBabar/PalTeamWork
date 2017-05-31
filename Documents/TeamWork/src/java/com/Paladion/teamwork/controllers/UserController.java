/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.CreateUserBean;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Satyam.k
 */

@Controller
public class UserController {
	
	
	@Autowired
 @Qualifier(value="UserService")
 UserService userService;
	
	
	@ModelAttribute("CreateM")
public CreateUserBean generate()
{
	   return new CreateUserBean();
}
	
	@RequestMapping(value="/CreateUser",method=RequestMethod.GET)
     public String CreateUser()
    {   
	    return "CreateUser";
    }
	
	
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST)
	public String createUser(@ModelAttribute("CreateM") CreateUserBean userBean,HttpServletRequest req){
		
		
		System.out.println("createUser");
        userService.createUser(userBean);
       
            		
		System.out.println("createUser() post method");
		return "SUCCESS";
		
	}
	
}
