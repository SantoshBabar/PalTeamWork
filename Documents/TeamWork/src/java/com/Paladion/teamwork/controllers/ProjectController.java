/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.DAO.ProjectDAO;
import com.Paladion.teamwork.DAO.ProjectDAOImpl;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.utils.DatabaseUtils;
import com.Paladion.teamwork.utils.ManDaysCalculator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView CreateNewProject(@ModelAttribute("ProjectM")ProjectBean PB,HttpServletRequest req)
    {
	   ManDaysCalculator mdc= new ManDaysCalculator();
	   System.out.println("\n inside create Project POST method ");
           PB.setMandays(mdc.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
            
            PS.addProject(PB); 	
	    System.out.println("Project Created with Project id"+PB.getProjectid());
	    System.out.println("Man days :"+PB.getMandays());
	    
            PS.getAllWeights(PB.getTemplateid());
	       
	    
	    return new ModelAndView( "DisplayProjectStatus","ProjectSuccess","Project Created Successfully"  );

	    
    }
    
    
    
    public String updateProject(ProjectBean pBean){return "";}
    public String deleteProject(String id){return "";}
    
    
    public List<ProjectBean> showAllProject(){
	    
	    ProjectDAO prDAO=new ProjectDAOImpl();
	    
	  return  prDAO.getAllProjects();
	    
	    
	    
    }
    
}
