/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.utils.DatabaseUtils;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;
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
            
	DatabaseUtils dbUtil=new DatabaseUtils();
           List <TaskBean> Tasklist;
           ModelAndView model=new ModelAndView("AddTasksToTemplate");
            
           TempS.addTemplate(TempB); 	
	System.out.println("Template Created with Template id  "+TempB.getTemplateid());
	    
	try{
	         Tasklist =dbUtil.getAllTasks();
	         model.addObject("AllTasks", Tasklist);
	    }catch(Exception ex){}
	    
	      HttpSession TempSession=req.getSession(true);
                 TempSession.setAttribute("Template", TempB);
	      return model;
}

@RequestMapping(value="/AddTaskTemplate",method=RequestMethod.POST)
public void AddTaskToTemplate(HttpServletRequest req){
	System.out.println("Inside Add Task to template controller");
	int i,j=0;
	
	String[] weight=new String[30];
	String[] taskid=req.getParameterValues("task");
	for(i=0;i<taskid.length;i++)
	{
		String tid=taskid[i];
	           weight[i]=(String)req.getParameter(tid);
	}
	System.out.println(Arrays.toString(taskid));
	
	System.out.println(Arrays.toString(weight));	
}
    
}
