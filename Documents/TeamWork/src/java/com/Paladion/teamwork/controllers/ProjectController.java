/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.DAO.ProjectDAO;
import com.Paladion.teamwork.DAO.ProjectDAOImpl;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.individualProjectStatusBean;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.Paladion.teamwork.utils.DatabaseUtils;
import com.Paladion.teamwork.utils.ManDaysCalculator;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@Qualifier(value="ProjectService")
ProjectService PS;
	
	
@ModelAttribute("ProjectM")
public ProjectBean populate()
{
	   return new ProjectBean();
}
	
@RequestMapping(value="/CreateProject",method=RequestMethod.GET)
public ModelAndView CreateProject()
{    
	DatabaseUtils dbUtil=new DatabaseUtils();
	List <TemplateBean> TemplateList;
	ModelAndView model=new ModelAndView("CreateProject");
	System.out.println("Inside Project controller for get method");
	try{
	         TemplateList=dbUtil.getAllTemplates();
		    
	         model.addObject("AllTemplates", TemplateList);
	    }catch(Exception ex){}
	return model;
}


@RequestMapping(value="/AddProject",method=RequestMethod.POST)
    public Object CreateNewProject(@ModelAttribute("ProjectM")ProjectBean PB,HttpServletRequest req,Model E) throws Exception
    {
        ModelAndView result = null;
       
        try
            {
	   ManDaysCalculator mdc= new ManDaysCalculator();
	   System.out.println("\n inside create Project POST method ");
           PB.setMandays(mdc.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
            
           PS.addProject(PB); 	
	    System.out.println("Project Created with Project id"+PB.getProjectid());
	    System.out.println("Man days :"+PB.getMandays());
            }
            catch(Exception ex){
             DatabaseUtils dbUtil=new DatabaseUtils();
             List <TemplateBean> TemplateList;
             TemplateList=dbUtil.getAllTemplates();
		
            result = new ModelAndView("CreateProject","Projectresp","Project Creation failed");
            result.addObject("AllTemplates", TemplateList);
             return result;
            }

//            result=new ModelAndView("AssignTaskToUsers","Projectresp","Project Created Successfully");
//	    //result.addObject("AllProjects", PS.getAllProjects());
//	    
//	  result.addObject("AllProjectTasks",PS.getAllWeights(PB.getTemplateid()));
//	    
//	    return result;
	    
	    List<individualProjectStatusBean> PSBList;
       //List<Object> PRDATA=PS.getProjectById(PB.getProjectid());
        CommonUtil CU=new CommonUtil();
   //   PSBList=  CU.devideDaysfortasks((ProjectBean)PRDATA.get(0), (List<MapTemplateTaskBean>) PRDATA.get(1));
      result=new ModelAndView("AssignTaskToUsers");
        result.addObject("ProjectData",PB);
	   result.addObject("AllProjectTasks",PS.getAllWeights(PB.getTemplateid()));
      // result.addObject("WeightData",PSBList);
       
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
    
    @RequestMapping(value="/showProgress",method=RequestMethod.GET)
    public ModelAndView showProjectProgress(@RequestParam int id) throws ParseException
    {
        List<individualProjectStatusBean> PSBList;
        List<Object> PRDATA=PS.getProjectById(id);
        CommonUtil CU=new CommonUtil();
      PSBList=  CU.devideDaysfortasks((ProjectBean)PRDATA.get(0), (List<MapTemplateTaskBean>) PRDATA.get(1));
        ModelAndView result=new ModelAndView("DisplayProjectProgress");
        result.addObject("ProjectData",PRDATA.get(0));
        result.addObject("WeightData",PSBList);
       
        return result;
    }
}
