/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.ProjectTransactionWrapper;
import com.Paladion.teamwork.beans.fileuploadBean;
import com.Paladion.teamwork.services.AdminService;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.sun.scenario.Settings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.out;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

@Autowired()
@Qualifier(value = "AdminService")
AdminService Aservice;

	
@ModelAttribute("ProjectM")
public ProjectBean populate()
{
	   return new ProjectBean();
}
@ModelAttribute("filebean")
public fileuploadBean populate1()
{
	   return new fileuploadBean();
}

	
    @RequestMapping(value="/CreateProject",method=RequestMethod.GET)
    public ModelAndView CreateProject(HttpServletRequest req)
    {   
        HttpSession sess=req.getSession(false);
        List <TemplateBean> TemplateList;
        List <UserDataBean> LeadList;
	ModelAndView model=new ModelAndView("CreateProject");
	System.out.println("Inside Project controller for get method");
	try{
	    TemplateList=TS.getAllTemplates();
            LeadList=CU.getUsersByRole("lead",sess);
            model.addObject("AllTemplates", TemplateList);
            model.addObject("AllLeads", LeadList);
	}
        catch(Exception ex){}
	return model;
    }

    //Schedule a project
    @RequestMapping(value="/ScheduleProject",method=RequestMethod.POST)
    public Object CreateNewProject(@ModelAttribute("ProjectM")ProjectBean PB,HttpServletRequest req,Model E) throws Exception
    {
        HttpSession sess= req.getSession(false);
        ModelAndView result = null;
        try{
	    System.out.println("\n inside create Project POST method ");
            PB.setMandays(CU.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
            PB.setStatus("New");
            PB.setLead(CU.getUsernameFromSession(PB.getLeadid(), sess));
            PS.addProject(PB);
            //send mail to lead                    
            CU.sendSchedulingMailToLead(PB, req.getSession(false));
	    System.out.println("Project Created with Project id"+PB.getProjectid());
	    System.out.println("Man days :"+PB.getMandays());
            UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
            if(sessuser.getRole().equalsIgnoreCase("scheduling")){
                return new ModelAndView("Welcome","Message","Project Created Successfully");
            }
        }
        catch(Exception ex){
            List <TemplateBean> TemplateList;
            TemplateList=TS.getAllTemplates();
	    result = new ModelAndView("CreateProject","Message","Project Creation failed");
            result.addObject("AllTemplates", TemplateList);
            return result;
        }
           
        ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
        List<ProjectTransactionBean> PSBList;
        ProjectBean PRDATA=PS.getProjectById(PB.getProjectid());
        List<MapTemplateTaskBean> MTTB=TS.getAllWeights(PRDATA.getTemplateid());
        PSBList=  CU.setTaskHours(PRDATA, MTTB);
        PTW.setProjectlist(PSBList);
        result=new ModelAndView("AssignTaskToUsers");
        List<UserDataBean> Alleng=CU.getUsersByRole("engineer", sess);
        result.addObject("AllEngineers",Alleng);
        
        result.addObject("ProjectW",PTW);
        return result;
    }
    
    public String updateProject(ProjectBean pBean){return "";}
    public String deleteProject(String id){return "";}
    
    @RequestMapping(value="/showAllProject",method=RequestMethod.GET)
    public ModelAndView showAllProject(HttpServletRequest req)
    {
        HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
	ModelAndView result=new ModelAndView("DisplayProjects");
        List<ProjectBean> PBList=(List<ProjectBean>)PS.getAllProjects(sessuser.getUserid(), sessuser.getRole());
        result.addObject("AllProjects",PBList );
        this.getAllProjectsDetails(req);
	return  result;
    }
      
    @RequestMapping(value="/AssignTaskToEngineers", method=RequestMethod.GET)
    public ModelAndView AssignTask()
    {
        ModelAndView result= new ModelAndView("AssignTaskToUsers");
        result.addObject("AllEngineers",null);
        result.addObject("ProjectW",null);
        result.addObject("Message","Please Select a Project From the Project List");
        return result;
    }
    
    //To assign engineers to the tasks in the project
    @RequestMapping(value="/AssignTaskToEngineers", method=RequestMethod.POST)
    public ModelAndView AssignTaskToEngineer(@ModelAttribute("ProjectW")ProjectTransactionWrapper ProjectW,HttpServletRequest req) throws Exception
    {
        String projid=req.getParameter("projectid");
        int projectid=Integer.parseInt(projid);
        ProjectBean PRDATA=PS.getProjectById(projectid);
	List <ProjectTransactionBean> PTBList=ProjectW.getProjectlist();
        List <ProjectTransactionBean> PTBList1=new ArrayList<ProjectTransactionBean>();
        
        PTBList1= CU.updateProjectTransaction(PTBList, PRDATA,req.getSession(false));
        PS.insertProjectTransaction(PTBList1);
        //Uncomment below line to send scheduling mail to lead
        CU.sendSchedulingMailToEngineers(PTBList1,req.getSession(false),PRDATA.getProjectname());
        ModelAndView result=new ModelAndView("DisplayProjectProgress");
        result.addObject("TaskDetails",PTBList1);
        result.addObject("ProjectData",PRDATA);
        return result;
    }
    
    //To display individual project progress
    @RequestMapping(value="/showProgress",method=RequestMethod.GET)
    public ModelAndView showProjectProgress(@RequestParam int id,HttpServletRequest req) throws ParseException
    {
           ModelAndView result;
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(id);
           PSBList = PS.getProjectTransaction(id);
           
           //If engineers not assigned, redirect to assign engineers to tasks.
           if(PSBList.isEmpty()){
           HttpSession sess=req.getSession(false);
           ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
           List<MapTemplateTaskBean> MTTB=TS.getAllWeights(PRDATA.getTemplateid());
           PSBList=  CU.setTaskHours(PRDATA, MTTB);
           PTW.setProjectlist(PSBList);
           result=new ModelAndView("AssignTaskToUsers");
           List<UserDataBean> Alleng=CU.getUsersByRole("engineer", sess);
           result.addObject("AllEngineers",Alleng);
           result.addObject("ProjectW",PTW);
           return result;
           }
           //return project progress
           else{
           result=new ModelAndView("DisplayProjectProgress");
           result.addObject("ProjectData",PRDATA);
           result.addObject("TaskDetails",PSBList);
           return result;
           }
    }
    
    //Update status of the individual task in the project
    @RequestMapping(value="/updateTaskStatus",method=RequestMethod.GET)
    public ModelAndView updateTaskStatus(@RequestParam int pid,@RequestParam int tid, @RequestParam String status) throws ParseException
    {
        boolean value= PS.updateTaskStatus(tid,status);
        if(value==true){
           List<ProjectTransactionBean> PSBList;
           ProjectBean PRDATA=PS.getProjectById(pid);
           PSBList = PS.getProjectTransaction(pid);
 
           ModelAndView result=new ModelAndView("DisplayProjectProgress");
           result.addObject("ProjectData",PRDATA);
           result.addObject("TaskDetails",PSBList);
           return result;
        }
        else{
            ModelAndView result=new ModelAndView("Customerror");
            result.addObject("Message","Something Went Wrong");
            return result;
        }
    }
    
    //Update status of the individual project
    @RequestMapping(value="/updateProjectStatus",method=RequestMethod.GET)
    public ModelAndView updateProjectStatus(@RequestParam int pid,@RequestParam String status,HttpServletRequest req) throws ParseException
    {
        boolean value=false;
        HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
        String role=sessuser.getRole();
        if(role.equalsIgnoreCase("manager")||role.equalsIgnoreCase("lead")){
        value= PS.updateProjectStatus(pid,status);
//        if(status.equalsIgnoreCase("completed")){
//          //  PS.updateTaskStatus(pid);
//        }
        }
        
       
        if(value==true){
          ModelAndView result=new ModelAndView("DisplayProjects");
	  result.addObject("AllProjects", PS.getAllProjects(sessuser.getUserid(), role));
	  return  result;
        }
        
        else{
            ModelAndView result=new ModelAndView("Customerror");
            result.addObject("Message","You are not authorized to perform the action.");
            return result;
        }
    }
    
    
    @RequestMapping(value="/updateTaskDelay",method=RequestMethod.GET)
    public ModelAndView updateTaskDelayGet(HttpServletRequest req) throws ParseException
    {
        return new ModelAndView("Customerror");
    }
    
    //Update delay to individual tasks in a project
    @RequestMapping(value="/updateTaskDelay",method=RequestMethod.POST)
    public ModelAndView updateTaskDelay(HttpServletRequest req) throws ParseException
    {
        String tid=req.getParameter("transId");
        String delay=req.getParameter("taskDelayTime");
        String pid=req.getParameter("projectid");
        int delayHours=Integer.parseInt(delay);
        int projectId=Integer.parseInt(pid);
        int transid=Integer.parseInt(tid);
        List<ProjectTransactionBean> PTBList=PS.getProjectTransaction(projectId);
        List<ProjectTransactionBean> PTBList2=new ArrayList<>();
        for(ProjectTransactionBean PTBean: PTBList)
        {
            if(PTBean.getTransid()==transid)
            {
                float hours= PTBean.getTaskhours()+ delayHours;
                PTBean.setTaskhours(hours);
                PTBean.setTaskdays(hours/9);
                PTBean.setStatus("Delayed");
                PTBList2.add(PTBean);
            }
           if(PTBean.getTransid()>transid)
           {
                PTBList2.add(PTBean);
            }  
        }
        List<ProjectTransactionBean> PTBList3=CU.updateDelayForTasks(PTBList2, delayHours);
        PS.updateProjectTransaction(PTBList3);
        ModelAndView result;
        List<ProjectTransactionBean> PSBList;
        ProjectBean PRDATA=PS.getProjectById(projectId);
        PSBList = PS.getProjectTransaction(projectId);   
        result=new ModelAndView("DisplayProjectProgress");
        result.addObject("Message","Delay updated successfully");
        result.addObject("ProjectData",PRDATA);
        result.addObject("TaskDetails",PSBList);
        return result;
    }

@RequestMapping(value="/uploadfiles",method=RequestMethod.POST)    
public ModelAndView uploaddocstoProject(HttpServletRequest req,@ModelAttribute fileuploadBean filebean,Model model)
    {
    HttpSession sess=req.getSession();    
    String PID=(String) sess.getAttribute("uploadPID");    
    List<MultipartFile> upfiles = filebean.getFiles();    
    List<String> fileNames = new ArrayList<String>();    
    
    if (null != upfiles && upfiles.size() > 0) 
        {
            for (MultipartFile multipartFile : upfiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                String filepath=Aservice.getSystemSettings().getUploadpath();
                File uploadFile = new File(filepath+File.separator+"files"+File.separator+PID, fileName);
                System.out.println(uploadFile);
                if(!uploadFile.exists())uploadFile.mkdirs();
                try
                {
                multipartFile.transferTo(uploadFile);
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
 
     return new ModelAndView("DocumentUpload");
    
    }


//download files start
@RequestMapping(value="/Downloadfiles",method=RequestMethod.POST)    
public ModelAndView Downloadfiles(HttpServletRequest req,Model model,HttpServletResponse response) throws FileNotFoundException, IOException
    {
    HttpSession sess=req.getSession();    
    String PID=(String) sess.getAttribute("DownloadPID");    
    System.out.println("projectid"+PID);    
    String filepath=Aservice.getSystemSettings().getUploadpath();
    
    File downloadfile = new File(filepath+File.separator+"files"+File.separator+PID);
    System.out.println("folder " + downloadfile);

    File[] listOfFiles = downloadfile.listFiles();

    for (File listOfFile : listOfFiles) {
        if (listOfFile.isFile()) {
            System.out.println("File " + listOfFile.getName());
        } else if (listOfFile.isDirectory()) {
            System.out.println("Directory " + listOfFile.getName());
        }
    }  
     return new ModelAndView("downloadDocuments");
    
    }
//download files end
@RequestMapping(value="/uploadfiles",method=RequestMethod.GET)
public ModelAndView uploaddocs(@RequestParam String pid,HttpServletRequest req)
{
HttpSession sess=req.getSession();
sess.setAttribute("uploadPID", pid);
return new ModelAndView("DocumentUpload","SysSettings",Aservice.getSystemSettings());
}

//download files
@RequestMapping(value="/Downloadfiles",method=RequestMethod.GET)
public ModelAndView Downloadfiles(@RequestParam String pid,HttpServletRequest req)
{
HttpSession sess=req.getSession();
sess.setAttribute("DownloadPID", pid);
return new ModelAndView("downloadDocuments","SysSettings",Aservice.getSystemSettings());
}
    
    
    
    
    @RequestMapping(value="/GetAllProjectDetails",method=RequestMethod.GET)
    public void getAllProjectsDetails(HttpServletRequest req)
    {
        
        HttpSession sess= req.getSession(false);
        UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
	ModelAndView result=new ModelAndView("Welcome");
        List<ProjectBean> PBList=(List<ProjectBean>)PS.getAllProjects(sessuser.getUserid(), sessuser.getRole());
        int total_projects=PBList.size();
        int project_new=0;
        int project_progress=0;
        int project_completed=0;
        for(ProjectBean PB:PBList){
            if(PB.getStatus().equalsIgnoreCase("new")){
                project_new++;
               
            }
            if(PB.getStatus().equalsIgnoreCase("progress")){
                project_progress++;
              
            }
            if(PB.getStatus().equalsIgnoreCase("completed")){
                project_completed++;
              
            }
        }
        System.out.println("No of completed projects : "+project_completed);
        System.out.println("No of on going projects : "+project_progress);
        System.out.println("No of new projects : "+project_new);
	
    }
      
    
}
