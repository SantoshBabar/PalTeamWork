/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.utils.CommonUtil;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
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
public ModelAndView CreateTemplate(@ModelAttribute("TemplateM")TemplateBean TempB,HttpServletRequest req) 
{
        System.out.println("\n inside create Template method ");

        List <TaskBean> Tasklist = null;
        
        HttpSession TempSession=req.getSession(false);
        TempSession.setAttribute("Template", TempB);
        //TempS.addTemplate(TempB); 	
	System.out.println("Template Created with Template id  "+TempB.getTemplateid());
	    
	try
        {
            Tasklist =TempS.getAllTasksforTemplate();
	}
        catch(Exception ex){}
	    
	
        TempSession.setAttribute("TaskList", Tasklist);
              
	return new ModelAndView("Test","AllTasks", Tasklist);
}

@RequestMapping(value="/AddTaskTemplate",method=RequestMethod.POST)
public ModelAndView AddTaskToTemplate(HttpServletRequest req){
    System.out.println("Inside Add Task to template controller");
    HttpSession session=req.getSession();
    TemplateBean TempB=(TemplateBean)session.getAttribute("Template"); 
   
    CommonUtil CUtil=new CommonUtil();
    List<MapTemplateTaskBean> MTTB=null;
    TempS.addTemplate(TempB);
    MTTB = CUtil.Maptasktotemplate(req, session);
    
        if(null!=MTTB)
        {
            for(MapTemplateTaskBean MTT:MTTB)
            {
            if(!TempS.addTaskToTemplate(MTT))
            {
                return new ModelAndView("AddTasksToTemplate","Temperror", "Something went wrong during save" );
            }
            }
            return new ModelAndView("Welcome","TemplateSuccess","Template Created Successfully");	
        }
        else
        {
            return new ModelAndView("AddTasksToTemplate","Temperror", "Total weight is not 100% or something went wrong" );
        }
	
    }
    
}
