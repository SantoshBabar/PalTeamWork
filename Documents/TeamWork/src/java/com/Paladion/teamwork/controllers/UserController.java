/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.services.LoginService;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class UserController {
	
    @Autowired
 @Qualifier(value="LoginService")
 LoginService LS;
 
 
 UserBean ub=null;
 LoginBean lb=null;
	
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


@RequestMapping(value="/ViewAllUser",method=RequestMethod.POST)
public ModelAndView ViewAllUser(@ModelAttribute("LoginM")LoginBean loginBean,HttpServletRequest req )
    {
        System.out.println("ViewAllUser");
    
	   List<LoginBean> userList=userService.ViewAllUser();
	   ModelAndView result=new ModelAndView("ViewAllUser");
           result.addObject("AllUsers",userList);
	   return result;
        }

@RequestMapping(value="/DeleteUser",method=RequestMethod.GET)
    public ModelAndView DeleteUser(@RequestParam int id) throws ParseException
    {
        ModelAndView result=new ModelAndView("ViewAllUser");
           if(id!=0)
           {
               userService.DeleteUser(id);
               
               List<LoginBean> userList=userService.ViewAllUser();
	  
           result.addObject("AllUsers",userList);
               
                result.addObject("Success","User deleted successfully");
               
           }
           else{
               
            result=new ModelAndView("Welcome");
          
           }
           return result;
      
    }
    
    @RequestMapping(value="/UpdateUser",method=RequestMethod.GET)
public ModelAndView UpdateUser(@RequestParam int id)
{
    
    ModelAndView result=new ModelAndView("UpdateUser");
           if(id!=0)
           {
               userService.UpdateUser(id);
               
               List<LoginBean> userList=userService.ViewAllUser();
	  
           result.addObject("UpdateUser",userList);
               
                result.addObject("Success","User displayed successfully");
                result=new ModelAndView("UpdateUser");
                return result;
                
               
           }
           else{
               
            result=new ModelAndView("fail");
          
           }
           return result;
}
    
    
}
