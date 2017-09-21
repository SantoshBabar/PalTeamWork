/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TaskTemplateWrapper;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.services.TemplateService;
import java.text.ParseException;
import java.util.ArrayList;
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

@ModelAttribute("TaskW")
public TaskTemplateWrapper populatetask()
{
	   return new TaskTemplateWrapper();
}
	
@RequestMapping(value="/CreateTaskTemplate",method=RequestMethod.GET)
public ModelAndView Template()
  {
      List <TaskBean> Tasklist = null;
      try
        {
            Tasklist =TempS.getAllTasksforTemplate();
	}
        catch(Exception ex){}
      return new ModelAndView("CreateTaskTemplate","AllTasks", Tasklist);
   
  }

@RequestMapping(value="/CreateTaskTemplate",method=RequestMethod.POST)
public ModelAndView CreateTemplate(@ModelAttribute("TemplateM")TemplateBean TempB,HttpServletRequest req) 
{
        System.out.println("\n inside create Template method ");

        List <TaskBean> Tasklist = null;
        
        HttpSession TempSession=req.getSession(false);
        TempSession.setAttribute("Template", TempB);
	System.out.println("Template Created with Template id  "+TempB.getTemplateid());
	    
	try
        {
            Tasklist =TempS.getAllTasksforTemplate();
	}
        catch(Exception ex){}
	    
        List<MapTemplateTaskBean> MTTBList = new ArrayList<MapTemplateTaskBean>();
	
       String []taskid= new String[20];
       TaskTemplateWrapper ttw=new TaskTemplateWrapper();
               taskid=req.getParameterValues("taskid");
               for(int i=0;i<taskid.length;i++){
               System.out.println(taskid[i]);
               MapTemplateTaskBean mttb=new MapTemplateTaskBean();
               int taskId=Integer.parseInt(taskid[i]);
               mttb.setTaskid(taskId);
               String taskname=getTaskName(taskId,Tasklist);
               mttb.setTaskname(taskname);
               MTTBList.add(mttb);
               }
               ttw.setMttblist(MTTBList);
	return new ModelAndView("AddTasksToTemplatev1","taskwrapper", ttw);
}

public String getTaskName(int taskid, List<TaskBean> Tasklist){
     String name=null;
    for(TaskBean tb:Tasklist)
    {
    if (taskid==tb.getTaskid()){
        name=tb.getTaskname();
       }
    }
    return name;
}

@RequestMapping(value="/AddTaskTemplate",method=RequestMethod.POST)
public ModelAndView AddTaskToTemplate(@ModelAttribute("TaskW")TaskTemplateWrapper TaskW, HttpServletRequest req)
{
    System.out.println("Inside Add Task to template controller");
    HttpSession session=req.getSession();
    TemplateBean TempB=(TemplateBean)session.getAttribute("Template"); 
    
    List <MapTemplateTaskBean> MttbList = new ArrayList<MapTemplateTaskBean>();
    MttbList=TaskW.getMttblist();
   
    int weight=0;
    for(MapTemplateTaskBean mttb: MttbList){
        weight=weight+mttb.getWeight();
       }
    
    if(weight!=100 || MttbList==null){
          return new ModelAndView("AddTasksToTemplate","Message", "Total weight is not 100% or something went wrong" );
    }
    else
    {
        TempS.addTemplate(TempB);
        for(MapTemplateTaskBean MTT:MttbList)
           { MTT.setTemplateid(TempB.getTemplateid());
            if(!TempS.addTaskToTemplate(MTT))
                {
                    return new ModelAndView("AddTasksToTemplate","Message", "Something went wrong during save" );
                }
            }	
    }
    List <TaskBean> Tasklist = null;
    Tasklist =TempS.getAllTasksforTemplate();
    ModelAndView result=new ModelAndView("CreateTaskTemplate");
    result.addObject("AllTasks",Tasklist);
    result.addObject("Message","Template Created Successfully");
    return result;
 }

@RequestMapping(value="/GetAllTaskTemplates",method=RequestMethod.GET)
public ModelAndView GetAllTaskTemplates()
{
    ModelAndView result=new ModelAndView("DisplayTemplates");
    List<TemplateBean> TBList= TempS.getAllTemplates();
    result.addObject("AllTemplates",TBList);
    return result;
}


@RequestMapping(value="/DeleteTemplate",method=RequestMethod.GET)
    public ModelAndView DeleteTemplate(@RequestParam int id) throws ParseException
    {
           if(id!=0)
           {
               boolean value= TempS.deleteTemplate(id);
               ModelAndView result=new ModelAndView("DisplayTemplates");
               List<TemplateBean> TBList= TempS.getAllTemplates();
               result.addObject("AllTemplates",TBList);
               result.addObject("Message","Template Deleted Succefully");
               return result; 
           }
           else{
                return new ModelAndView("Error");
            }
    }
    

@RequestMapping(value="/GetTemplateDetails",method=RequestMethod.GET)
public ModelAndView GetTemplateDetails(@RequestParam int id)
{
           ModelAndView result=new ModelAndView("UpdateTemplateDetails");
           if(id!=0)
            {
               List <MapTemplateTaskBean> MTTB=TempS.getAllWeights(id);
                result.addObject("TemplateDetails",MTTB);
                return result;
            }
           else{
                result=new ModelAndView("fail");
            }
           return result;
}
//
//@RequestMapping(value="/UpdateTemplateDetails",method=RequestMethod.GET)
//public ModelAndView UpdateTemplateDetails(@ModelAttribute("TemplateTask")MapTemplateTaskBeanBean TempB)
//{
//           ModelAndView result=new ModelAndView("UpdateTemplateDetails");
//           if(id!=0)
//            {
//               List <MapTemplateTaskBean> MTTB=TempS.getAllWeights(id);
//                result.addObject("TemplateDetails",MTTB);
//                return result;
//            }
//           else{
//                result=new ModelAndView("fail");
//            }
//           return result;
//}
//


}
