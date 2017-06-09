/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.ProjectTransactionWrapper;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ProjectController {
	

@Autowired
@Qualifier(value="TemplateService")
TemplateService TS;

@Autowired
@Qualifier(value="CommonUtil")
CommonUtil CU;
    
@Autowired
@Qualifier(value="ProjectService")
ProjectService PS;

@Autowired
@Qualifier(value="UserService")
UserService US;
	
@ModelAttribute("ProjectM")
public ProjectBean populate()
{
	   return new ProjectBean();
}

	
@RequestMapping(value="/CreateProject",method=RequestMethod.GET)
public ModelAndView CreateProject()
{    
	
	List <TemplateBean> TemplateList;
        List <LoginBean> LeadList;
	ModelAndView model=new ModelAndView("CreateProject");
	System.out.println("Inside Project controller for get method");
	try{
	        TemplateList=TS.getAllTemplates();
                LeadList=US.getUsersByRole("lead");
		model.addObject("AllTemplates", TemplateList);
                model.addObject("AllLeads", LeadList);
	    }catch(Exception ex){}
	return model;
}


@RequestMapping(value="/AddProject",method=RequestMethod.POST)
    public Object CreateNewProject(@ModelAttribute("ProjectM")ProjectBean PB,HttpServletRequest req,Model E) throws Exception
    {
           ModelAndView result = null;
           try{
	            System.out.println("\n inside create Project POST method ");
                    PB.setMandays(CU.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
                    PS.addProject(PB); 	
	            System.out.println("Project Created with Project id"+PB.getProjectid());
	            System.out.println("Man days :"+PB.getMandays());
                }
           catch(Exception ex){
                      List <TemplateBean> TemplateList;
                      TemplateList=TS.getAllTemplates();
	              result = new ModelAndView("CreateProject","Projectresp","Project Creation failed");
                      result.addObject("AllTemplates", TemplateList);
                      return result;
            }
           
           ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(PB.getProjectid());
           List<MapTemplateTaskBean> MTTB=PS.getAllWeights(PRDATA.getTemplateid());
           PSBList=  CU.setTaskHours(PRDATA, MTTB);
           PTW.setProjectlist(PSBList);
           result=new ModelAndView("AssignTaskToUsers");
           result.addObject("AllEngineers",US.getUsersByRole("engineer"));
        
           result.addObject("ProjectW",PTW);
           return result;
	    
    }
    
    
    
    public String updateProject(ProjectBean pBean){return "";}
    public String deleteProject(String id){return "";}
    
    @RequestMapping(value="/showAllProject",method=RequestMethod.GET)
    public ModelAndView showAllProject()
    {
	ModelAndView result=new ModelAndView("DisplayProjects","Projectresp","Project Created Successfully");
	result.addObject("AllProjects", PS.getAllProjects());
	return  result;
    }
    
    
    @RequestMapping(value="/AssignTaskToEngineers", method=RequestMethod.POST)
    public ModelAndView AssignTaskToEngineer(@ModelAttribute("ProjectW")ProjectTransactionWrapper ProjectW,HttpServletRequest req) throws Exception
    {
        String projid=req.getParameter("projectid");
        int projectid=Integer.parseInt(projid);
        ProjectBean PRDATA=PS.getProjectById(projectid);
	List <ProjectTransactionBean> PTBList=ProjectW.getProjectlist();
        List <ProjectTransactionBean> PTBList1=new ArrayList<ProjectTransactionBean>();
        CommonUtil cu=new CommonUtil();
        PTBList1= cu.updateProjectTransaction(PTBList, PRDATA);
        PS.insertProjectTransaction(PTBList1);
        ModelAndView result=new ModelAndView("DisplayProjectProgress");
        result.addObject("TaskDetails",PTBList1);
        result.addObject("ProjectData",PRDATA);
        return result;
    }
    

    @RequestMapping(value="/showProgress",method=RequestMethod.GET)
    public ModelAndView showProjectProgress(@RequestParam int id) throws ParseException
    {
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(id);
           PSBList = PS.getProjectTransaction(id);
 
           ModelAndView result=new ModelAndView("DisplayProjectProgress");
           result.addObject("ProjectData",PRDATA);
           result.addObject("TaskDetails",PSBList);
           return result;
    }
}
