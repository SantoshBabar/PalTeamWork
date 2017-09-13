/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.services.TaskService;
import com.Paladion.teamwork.utils.TaskValidator;
import com.Paladion.teamwork.utils.Validator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class TaskController {
	
    
@Autowired
@Qualifier(value="TaskValidator")
TaskValidator TV;
   


@InitBinder
protected void initBinder(WebDataBinder binder) {
      binder.addValidators(TV);
}    

    
@Autowired
@Qualifier(value="TaskService")
TaskService TS;
	
	
@ModelAttribute("TaskM")
public TaskBean populate()
{
	   return new TaskBean();
}

	
@RequestMapping(value="/CreateTask",method=RequestMethod.GET)
     public String CreateTask()
    {   
	    return "CreateTask";
    }
	


	


@RequestMapping(value="/CreateTask",method=RequestMethod.POST)
    public ModelAndView createTask(@ModelAttribute("TaskM")@Validated TaskBean TB,BindingResult result,HttpServletRequest req) 
    {
        if (result.hasErrors()) {
            //validates the user input, this is server side validation
            System.out.println("error!!!!!!!!");
            
         return new ModelAndView("CreateTask");
      }
	System.out.println("\n inside create Task method ");
	
           TS.addTask(TB); 	
	    System.out.println("Task Created with Taskid"+TB.getTaskid());
	 
	    return new ModelAndView( "CreateTask","Message","Task Created Successfully"  );
    }	
    
    
@RequestMapping(value="/GetAllTasks",method=RequestMethod.GET)
public ModelAndView GetAllTasks()
{
    ModelAndView result=new ModelAndView("DisplayTasks");
    List<TaskBean> TBList= TS.getAllTasks();
    result.addObject("AllTasks",TBList);
    return result;
}


@RequestMapping(value="/DeleteTask",method=RequestMethod.GET)
    public ModelAndView DeleteTask(@RequestParam int id) throws ParseException
    {
           if(id!=0)
           {
               boolean value= TS.deleteTask(id);
               ModelAndView result=new ModelAndView("DisplayTasks");
               List<TaskBean> TBList= TS.getAllTasks();
               result.addObject("AllTasks",TBList);
               result.addObject("Message","Task Deleted Succefully");
               return result; 
           }
           else{
                return new ModelAndView("Error");
            }
    }
    
    
}
