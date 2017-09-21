/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.EmailBean;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.ProjectTransactionWrapper;
import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.beans.fileuploadBean;
import com.Paladion.teamwork.services.AdminService;
import com.Paladion.teamwork.services.ProjectService;
import com.Paladion.teamwork.services.TemplateService;
import com.Paladion.teamwork.services.UserService;
import com.Paladion.teamwork.utils.CommonUtil;
import com.Paladion.teamwork.utils.ProjectValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	
//@Autowired
//@Qualifier(value="ProjectValidator")
//ProjectValidator PV;
//
//@InitBinder
//protected void initBinder(WebDataBinder binder) {
//      binder.addValidators(PV);
//}
    
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

@Autowired
@Qualifier(value="ProjectValidator")
ProjectValidator projectBeanValidator;

@Autowired
@Qualifier(value="EmailBean")
EmailBean eBean;
   
@InitBinder("ProjectM")
protected void initProjectBeanBinder(WebDataBinder binder) {
      binder.addValidators(projectBeanValidator);
} 
	
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
        String[] authorizedRoles = {"admin","manager","lead","scheduling"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
        HttpSession sess=req.getSession(false);
        List <TemplateBean> TemplateList;
        List <UserDataBean> LeadList;
	ModelAndView model=new ModelAndView("CreateProject");
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
    public Object CreateNewProject(@ModelAttribute("ProjectM")@Validated ProjectBean PB,BindingResult result,HttpServletRequest req,Model E) throws Exception
    {
        String[] authorizedRoles = {"admin","manager","lead","scheduling"};
        if(!CU.checkUserAuthorization(authorizedRoles, req))  return new ModelAndView("Error");
       
        HttpSession sess= req.getSession(false);
        ModelAndView results = null;
        List <TemplateBean> TemplateList;
        List <UserDataBean> LeadList;
            try{
                if (result.hasErrors()) {
                    //validates the user input, this is server side validation
                    System.out.println("error!!!!!!!!");
                    ModelAndView model=new ModelAndView("CreateProject");
                    try{
                        TemplateList=TS.getAllTemplates();
                        LeadList=CU.getUsersByRole("lead",sess);
                        model.addObject("AllTemplates", TemplateList);
                        model.addObject("AllLeads", LeadList);
                        return model;
                    }
                    catch(Exception ex){
                    return new ModelAndView("Error");
                    }                    
                }
	    
                System.out.println("\n inside create Project POST method ");
                PB.setMandays(CU.getWorkingDays(PB.getStartdate(),PB.getEnddate()));
                PB.setStatus("New");
                PB.setLead(CU.getUsernameFromSession(PB.getLeadid(), sess));
                PS.addProject(PB);
                SystemBean sys=Aservice.getSystemSettings();
                CU.sendSchedulingMailToLead(PB, req.getSession(false));
                System.out.println("Project Created with Project id"+PB.getProjectid());
                System.out.println("Man days :"+PB.getMandays());
                UserDataBean sessuser=(UserDataBean) sess.getAttribute("Luser");
                if(sessuser.getRole().equalsIgnoreCase("scheduling")){
                return new ModelAndView("Welcome","Message","Project Created Successfully");
                }
            }
            catch(Exception ex){
                //ex.printStackTrace();
                TemplateList=TS.getAllTemplates();
                LeadList=CU.getUsersByRole("lead",sess);   
                results = new ModelAndView("CreateProject","Message","Project Creation failed due to an error");
                results.addObject("AllTemplates", TemplateList);
                results.addObject("AllLeads", LeadList);
                return results;
            }
           
            ProjectTransactionWrapper PTW=new ProjectTransactionWrapper();
            List<ProjectTransactionBean> PSBList;
            ProjectBean PRDATA=PS.getProjectById(PB.getProjectid());
            List<MapTemplateTaskBean> MTTB=TS.getAllWeights(PRDATA.getTemplateid());
            PSBList=  CU.setTaskHours(PRDATA, MTTB);
            PTW.setProjectlist(PSBList);
            results=new ModelAndView("AssignTaskToUsers");
            List<UserDataBean> Alleng=CU.getUsersByRole("engineer", sess);
            results.addObject("AllEngineers",Alleng);
            results.addObject("ProjectW",PTW);
            return results;
        
    }
    
//    public String updateProject(ProjectBean pBean){return "";}
//    public String deleteProject(String id){return "";}
    
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
    public ModelAndView AssignTask(HttpServletRequest req)
    {
        String[] authorizedRoles = {"admin","manager","lead"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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
        String[] authorizedRoles = {"admin","manager","lead"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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
    public ModelAndView updateTaskStatus(@RequestParam int pid,@RequestParam int tid, @RequestParam String status, HttpServletRequest req) throws ParseException
    {
        String[] authorizedRoles = {"admin","manager","lead","engineer"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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
        String[] authorizedRoles = {"admin","manager","lead"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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
        String[] authorizedRoles = {"admin","manager","lead","engineer"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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
    
    
    @RequestMapping(value="/uploadfiles",method=RequestMethod.GET)
public ModelAndView uploaddocs(@RequestParam String pid,HttpServletRequest req)
{
HttpSession sess=req.getSession();
sess.setAttribute("uploadPID", pid);
return new ModelAndView("DocumentUpload","SysSettings",Aservice.getSystemSettings());
}


@RequestMapping(value="/uploadfiles",method=RequestMethod.POST)    
public ModelAndView uploaddocstoProject(HttpServletRequest req,@ModelAttribute fileuploadBean filebean,Model model)
    {
        String[] authorizedRoles = {"admin","manager","lead","engineer"};
        if(!CU.checkUserAuthorization(authorizedRoles, req)) return new ModelAndView("Error");
        
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


@RequestMapping(value="/Downloadfiles",method=RequestMethod.POST)    
public void  Downloadfiles(HttpServletRequest req,Model model,HttpServletResponse response) throws FileNotFoundException, IOException, Exception
    {
    HttpSession sess=req.getSession();    
    String PID=(String) sess.getAttribute("DownloadPID");    
    System.out.println("projectid"+PID);    
    String filepath=Aservice.getSystemSettings().getUploadpath();
    
    File downloadfile = new File(filepath+File.separator+"files"+File.separator+PID);
    System.out.println("folder " + downloadfile.toString());
    String sourceFolderName =  downloadfile.toString();
        String outputFileName = downloadfile.toString()+".zip";
 
        FileOutputStream fos = new FileOutputStream(outputFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);
        //level - the compression level (0-9)
        zos.setLevel(9);
 
        System.out.println("Begin to compress folder : " + sourceFolderName + " to " + outputFileName);
        addFolder(zos, sourceFolderName, sourceFolderName);
 
        zos.close();
        System.out.println("Program ended successfully!");
        //file download
        
        
String downloadFolder = downloadfile.toString();
String filename1=downloadfile.toString()+".zip";
Path file = Paths.get(filename1);
  if (Files.exists(file)) {
   response.setContentType("application/zip");
   response.addHeader("Content-Disposition", "attachment; filename=" + filename1);
   try {
    Files.copy(file, response.getOutputStream());
    response.getOutputStream().flush();
   } catch (IOException e) {
    System.out.println("Error :- " + e.getMessage());
   }
  } else {
   System.out.println("Sorry File not found!!!!");
  }
   //file download        
        
    }
 
    private static void addFolder(ZipOutputStream zos,String folderName,String baseFolderName)throws Exception{
        File f = new File(folderName);
        if(f.exists()){
 
            if(f.isDirectory()){
                //Thank to peter 
                //For pointing out missing entry for empty folder
                if(!folderName.equalsIgnoreCase(baseFolderName)){
                    String entryName = folderName.substring(baseFolderName.length()+1,folderName.length()) + File.separatorChar;
                    System.out.println("Adding folder entry " + entryName);
                    ZipEntry ze= new ZipEntry(entryName);
                    zos.putNextEntry(ze);    
                }
                File f2[] = f.listFiles();
                for(int i=0;i<f2.length;i++){
                    addFolder(zos,f2[i].getAbsolutePath(),baseFolderName);    
                }
            }else{
                //add file
                //extract the relative name for entry purpose
                String entryName = folderName.substring(baseFolderName.length()+1,folderName.length());
                System.out.print("Adding file entry " + entryName + "...");
                ZipEntry ze= new ZipEntry(entryName);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(folderName);
                int len;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();
                System.out.println("OK!");
 
            }
        }else{
            System.out.println("File or directory not found " + folderName);
        }
     
}
//download files end

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
