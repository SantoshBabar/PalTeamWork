/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.services.TemplateService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class TemplateController {
	
@Autowired
@Qualifier(value="TemplateService")
TemplateService TempS;
	
	
@ModelAttribute("TemplateM")
public TemplateBean populate()
{
	   return new TemplateBean();
}
	
@RequestMapping(value="/CreateTaskTemplate",method=RequestMethod.GET)
public String Template()
{
return "CreateTaskTemplate";
}

@RequestMapping(value="/CreateTaskTemplate",method=RequestMethod.POST)
public String CreateTemplate(@ModelAttribute("TemplateM")TemplateBean TempB,HttpServletRequest req)
{
System.out.println("\n inside create Template method ");
	
           TempS.addTemplate(TempB); 	
	    System.out.println("Template Created with Template id"+TempB.getTemplateid());
	    
	      HttpSession TempSession=req.getSession(true);
            TempSession.setAttribute("Template", TempB);
	    return "AddTasksToTemplate";
}
    
}
