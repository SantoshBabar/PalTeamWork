/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.services.TaskService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
public class TaskController {
	
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
	
@Transactional
@RequestMapping(value="/CreateTask",method=RequestMethod.POST)
    public void createTask(@ModelAttribute("TaskM")TaskBean TB,HttpServletRequest req) 
    {
	System.out.println("\n inside create Task method ");
	
           TS.addTask(TB); 	
	    System.out.println("Task Created with Taskid"+TB.getTaskid());
    }	
        
    @RequestMapping(value="/DeleteTask",method=RequestMethod.POST)
    public String deleteTask(String id){return "";}
    
    @Transactional
    public void getAllTasks(){
    List <TaskBean> list=new ArrayList<TaskBean>();
     System.out.println("Inside Task Controller");
	
    
    }
    
}
