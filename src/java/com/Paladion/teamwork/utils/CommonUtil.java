/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.EmailBean;
import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.beans.UserDataBean;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class CommonUtil {
    
    public List<MapTemplateTaskBean> Maptasktotemplate(HttpServletRequest req, HttpSession session) 
    {
        List<MapTemplateTaskBean> mTTBList = new ArrayList<MapTemplateTaskBean>();
        List<TaskBean> tasklist = null;
        TemplateBean TempB = null;
        int Tempid, i, j = 0;

        //retrieving tasks and templates from session stored in controller
        TempB = (TemplateBean) session.getAttribute("Template");
        tasklist = (List<TaskBean>) session.getAttribute("TaskList");

        Tempid = TempB.getTemplateid();
        System.out.println("The templateid to which tasks will be added: " + Tempid);

	String[] taskID=req.getParameterValues("task");
	
           int[] taskid=new int[taskID.length];
	i=0;
	
           for(String str:taskID)
          {
            taskid[i]=Integer.parseInt(str);//Exception in this line
            i++;
          }
	
	int[] weight=new int[taskid.length];
	
           for(i=0;i<taskid.length;i++)
	{
		String tid=taskID[i];
	           weight[i]=Integer.parseInt(req.getParameter(tid));
	}
	
	int sum= IntStream.of(weight).sum();
	System.out.println(Arrays.toString(taskid));
	System.out.println(Arrays.toString(weight));	
	System.out.println("The sum of all the weights entered: "+sum);
        
           if(sum==100)
          {
            for(i=0;i<taskid.length;i++)
                 {
                      MapTemplateTaskBean mb=new MapTemplateTaskBean();
                      mb.setTaskid(taskid[i]);
                      for(TaskBean tb:tasklist)
                     {
                                  if(tb.getTaskid()==taskid[i])mb.setTaskname(tb.getTaskname());
                     }
                      mb.setTemplateid(Tempid);
                      mb.setWeight(weight[i]);
                      mTTBList.add(mb);
                 }   
           return mTTBList;
        }
      else{return null;}
    }
    
 
    
    
    public List<ProjectTransactionBean> devideDaysfortasks(ProjectBean PB, List<MapTemplateTaskBean> MTTP) throws ParseException
    {
        List <ProjectTransactionBean> PSBList=new ArrayList<ProjectTransactionBean>();
        Date TaskEndDate=null;
        float iMandays;
        int Weight;
        float TotalMandays=PB.getMandays();
        int taskcount=MTTP.size();
        Calendar ProjectTime = Calendar.getInstance();
        ProjectTime.setTime(PB.getStartdate());
	if (ProjectTime.get(Calendar.HOUR_OF_DAY) < 10) 
           {
                       ProjectTime.set(Calendar.HOUR, 10);
           }
       
        for(MapTemplateTaskBean MB :MTTP)
        {
        ProjectTransactionBean PSB = new ProjectTransactionBean();
        Weight =MB.getWeight();
        iMandays=TotalMandays * Weight/100;
        PSB.setTaskdays(iMandays);
        if(null==TaskEndDate)
        {
        PSB.setTaskstartdate(ProjectTime);
        TaskEndDate=calculateResponseTime(ProjectTime, iMandays*9);
        PSB.setTaskenddate(TaskEndDate);
        PSB.setTaskname(MB.getTaskname());
        }
        else
        {
           ProjectTime.setTime(TaskEndDate);
           PSB.setTaskstartdate(ProjectTime);
           TaskEndDate=calculateResponseTime(ProjectTime, iMandays*9);
           
        PSB.setTaskenddate(TaskEndDate);
        PSB.setTaskname(MB.getTaskname());
        }
        
        PSBList.add(PSB);
        System.out.println("second end date is"+TaskEndDate);          
        }

      
        return PSBList;
    
    }

    
 Date calculateResponseTime(Calendar ProjectTime, float ProjectDurationinHours) {
     
int fromHour = 10;//start time of the day usually 10 AM
int fromMinute = 0;
int toHour = 19;//end time of the day usually 7 PM
int toMinute = 0;
Date end = null;


//Check to get start time of the current day. If less than 10 AM make it 10 AM

    if (ProjectTime.get(Calendar.HOUR_OF_DAY) < fromHour) 
    {
        ProjectTime.set(Calendar.HOUR, fromHour);
    }
   //Check to get end time of the current day. If less more than 7 PM add a day and set the time to 10 AM 
   //if the current day is sunday make it Monday 10 AM
    if (ProjectTime.get(Calendar.HOUR_OF_DAY) >= toHour || ProjectTime.get(Calendar.DAY_OF_WEEK) == 1) 
    {
        ProjectTime.add(Calendar.DATE, 1);
        ProjectTime.set(Calendar.HOUR_OF_DAY, fromHour);
        ProjectTime.set(Calendar.MINUTE, fromMinute);
    } 
    
    //If its a saturday make it Monday 10 AM   
    if (ProjectTime.get(Calendar.DAY_OF_WEEK) == 7) 
    {
        ProjectTime.add(Calendar.DATE, 2);
        ProjectTime.set(Calendar.HOUR_OF_DAY, fromHour);
        ProjectTime.set(Calendar.MINUTE, fromMinute);
    }
    
    int hour = ProjectTime.get(Calendar.HOUR_OF_DAY);
    int minute = ProjectTime.get(Calendar.MINUTE);

    long StartofDayMilliseconds = ((hour * 60) + minute) * 60 * 1000;
    long EndofDayMilliseconds = ((toHour * 60) + toMinute) * 60 * 1000;
    long WorkHoursInMilliSeconds = EndofDayMilliseconds - StartofDayMilliseconds;

    long ProjectDurationInMilliseconds =  (long) (ProjectDurationinHours * 60 * 60 * 1000);

    if (ProjectDurationInMilliseconds <= WorkHoursInMilliSeconds) 
    {
        end = new Date(ProjectTime.getTimeInMillis() + ProjectDurationInMilliseconds);
    } 
    
    else 
    {
        long remainder = (ProjectDurationInMilliseconds - WorkHoursInMilliSeconds) / 60 / 60 / 1000;
        Date NextWorkingDate = new Date(ProjectTime.getTimeInMillis() + WorkHoursInMilliSeconds);
        ProjectTime.setTime(NextWorkingDate);
        end = calculateResponseTime(ProjectTime, remainder);
    }

    return end;

} 
    
    
    
     public List<ProjectTransactionBean> setTaskHours(ProjectBean PB, List<MapTemplateTaskBean> MTTP) throws ParseException
    {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        
        List <ProjectTransactionBean> PSBList=new ArrayList<ProjectTransactionBean>();
       
        float iMandays;
        int Weight;
        float TotalMandays=PB.getMandays();
        
        for(MapTemplateTaskBean MB :MTTP)
        {
            ProjectTransactionBean PSB = new ProjectTransactionBean();
            Weight =MB.getWeight();
            iMandays=TotalMandays * Weight/100;
            PSB.setTaskdays(iMandays);
            PSB.setTaskhours(iMandays*9);
            PSB.setProjectid(PB.getProjectid());
            PSB.setTaskname(MB.getTaskname());
            PSB.setStatus("New");
            PSBList.add(PSB);
        }
      return PSBList;
    }
     
     
     public List<ProjectTransactionBean> updateProjectTransaction(List<ProjectTransactionBean> PTBList, ProjectBean PB, HttpSession sess) throws ParseException{
         
         HashMap<Integer, List<ProjectTransactionBean>> hashMap = new HashMap<Integer, List<ProjectTransactionBean>>();
         List <ProjectTransactionBean> ResultList=new ArrayList();
         
         for(ProjectTransactionBean PTBean : PTBList)
         {
            if (!hashMap.containsKey(PTBean.getUserid())) {
            List<ProjectTransactionBean> list = new ArrayList();
            list.add(PTBean);
            hashMap.put(PTBean.getUserid(), list);
           } 
            else {
            hashMap.get(PTBean.getUserid()).add(PTBean);}
        }
         
        System.out.println("\nNo of users assigned to project : "+hashMap.size());
        
        for (Map.Entry<Integer, List<ProjectTransactionBean>> entry : hashMap.entrySet())
        {
            Date TaskEndDate=null;
            List<ProjectTransactionBean> PTlist = entry.getValue();
            Calendar ProjectTime = Calendar.getInstance();
            ProjectTime.setTime(PB.getStartdate());
	    if (ProjectTime.get(Calendar.HOUR_OF_DAY) < 10) 
               {
                       ProjectTime.set(Calendar.HOUR, 10);
               }
            for (ProjectTransactionBean PTBean : PTlist)
              {
                if(null==TaskEndDate)
                  {
                    PTBean.setTaskstartdate(ProjectTime);
                    PTBean.setEngname(this.getUsernameFromSession(PTBean.getUserid(),sess));
                    TaskEndDate=calculateResponseTime(ProjectTime, PTBean.getTaskhours());
                    PTBean.setTaskenddate(TaskEndDate);
                   }
                else
                  {
                    ProjectTime.setTime(TaskEndDate);
                    
                     if (ProjectTime.get(Calendar.HOUR_OF_DAY) == 19 || ProjectTime.get(Calendar.DAY_OF_WEEK) == 1) 
                        {
                              ProjectTime.add(Calendar.DATE, 1);
                              ProjectTime.set(Calendar.HOUR_OF_DAY, 10);
                              ProjectTime.set(Calendar.MINUTE, 00);
                        }
                    PTBean.setTaskstartdate(ProjectTime);
                    TaskEndDate=calculateResponseTime(ProjectTime,PTBean.getTaskhours());
                    PTBean.setEngname(this.getUsernameFromSession(PTBean.getUserid(),sess));
                    PTBean.setTaskenddate(TaskEndDate);
                  }
                ResultList.add(PTBean);
            }
        }
      return ResultList;    
    }
     
     
    public  int getWorkingDays(Date startDate, Date endDate) {
    Calendar startCal = Calendar.getInstance();
    startCal.setTime(startDate);        

    Calendar endCal = Calendar.getInstance();
    endCal.setTime(endDate);

    int workDays = 1;

   
    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
        return 1;
    }

    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
        startCal.setTime(endDate);
        endCal.setTime(startDate);
    }

    do {
       
        startCal.add(Calendar.DAY_OF_MONTH, 1);
        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
           workDays++;
        }
    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); 

    return workDays;
}

    public String getUsernameFromSession(int userid,HttpSession sess) 
    
    {
        List<UserDataBean> UDBean=(List<UserDataBean>) sess.getAttribute("AllUsers");
        for (UserDataBean ub:UDBean)
        {
        if(ub.getUserid()==userid)
        return ub.getUsername();
        }
        return null;
        
    }
    
    public List<UserDataBean> getUsersByRole(String role, HttpSession sess){
        List<UserDataBean> UserList=new ArrayList();
        List<UserDataBean> UDBean=(List<UserDataBean>) sess.getAttribute("AllUsers");
        for(UserDataBean ub:UDBean){
            if(role.equalsIgnoreCase(ub.getRole())){
               UserList.add(ub);
            }
        }
        return UserList;
    }
    
    public UserDataBean getUserById(int userid, HttpSession sess){
        List<UserDataBean> UDBean=(List<UserDataBean>) sess.getAttribute("AllUsers");
        for(UserDataBean ub:UDBean){
            if(userid==ub.getUserid()){
               return ub;
               }
             }
        return null;
    }
    
    public boolean sendSchedulingMailToEngineers(List<ProjectTransactionBean> PTBList, HttpSession sess, String projectname){
        
        HashMap<Integer, List<ProjectTransactionBean>> hashMap = new HashMap();
        // List <ProjectTransactionBean> ResultList=new ArrayList();
        PTBList.forEach((PTBean) -> {
            if (!hashMap.containsKey(PTBean.getUserid())) {
                List<ProjectTransactionBean> list = new ArrayList();
                list.add(PTBean);
                hashMap.put(PTBean.getUserid(), list);
            } 
            else {
                hashMap.get(PTBean.getUserid()).add(PTBean);}
        });
         
         hashMap.entrySet().forEach((entry) -> {
             int userid = entry.getKey();
             List <ProjectTransactionBean> PTBlist =entry.getValue();
             EmailBean ebean=new EmailBean();
             EmailUtil EU=new EmailUtil();
             UserDataBean ubean=this.getUserById(userid, sess);
             ebean.setTo(ubean.getEmail());
             ebean.setSubject("Project Scheduling Mail");
             int i=0;
             StringBuilder mess=new StringBuilder();
                    mess.append("Dear ")
                     .append(ubean.getUsername())
                     .append("\n\nYou have been assigned to the following tasks in the ")
                     .append(projectname)
                     .append(" project.\n\n");
             
             for(ProjectTransactionBean task:PTBlist){
                 i++;
                 mess.append(i)
                         .append(". ")
                         .append(task.getTaskname()).append("\n");
             }
             
             mess.append("\n\nBest Regards\nTeam Paladion");
             String message=mess.toString();
             ebean.setMessage(message);
              SystemBean syssetting = (SystemBean)sess.getAttribute("SysConfig");
             EU.sendEmail(ebean, syssetting);
        });
        
        
        return true;
    }
    
    public boolean sendSchedulingMailToLead(ProjectBean PB, HttpSession sess) throws ParseException{
        
         EmailUtil EU=new EmailUtil();
         EmailBean ebean=new EmailBean();
         UserDataBean ub=this.getUserById(PB.getLeadid(), sess);
         ebean.setTo(ub.getEmail());
         ebean.setSubject("Project Scheduling Mail");
         
         SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
         String sDate = sm.format(PB.getStartdate());
         String eDate = sm.format(PB.getEnddate());
         
         StringBuilder mess=new StringBuilder();
         mess.append("Dear ").append(ub.getUsername()).append("\n\nYou have been assigned to the below project as delivery lead.")
                  .append(" Please assign Engineers to execute the project.")
                  .append("\n\nProject : ").append(PB.getProjectname())
                  .append("\nOPID : ").append(PB.getOpid())
                  .append("\nStart Date : ").append(sDate)
                  .append("\nEnd Date : ").append(eDate)
                  .append("\nNo Of Days : ").append(PB.getMandays())
                  .append("\n\nBest Regards,").append("\nTeam Paladion");
         
         String message=mess.toString();
         
         ebean.setMessage(message);
         SystemBean syssetting = (SystemBean)sess.getAttribute("SysConfig");
         EU.sendEmail(ebean, syssetting);
        return true;
    }
    

    
    
    public List<ProjectTransactionBean> updateDelayForTasks(List<ProjectTransactionBean> PTBList, int hours){
        Date delayedTaskEndDate=null;
        for(ProjectTransactionBean PTBean : PTBList)        
        {
            Calendar ProjectTime = Calendar.getInstance();
            ProjectTime.setTime(PTBean.getTaskstartdate());
           
            if(null==delayedTaskEndDate)
                  {
                    delayedTaskEndDate=calculateResponseTime(ProjectTime, PTBean.getTaskhours());
                    PTBean.setTaskenddate(delayedTaskEndDate);
                   }
                else
                  {
                    ProjectTime.setTime(delayedTaskEndDate);
                    PTBean.setTaskstartdate(ProjectTime);
                    delayedTaskEndDate=calculateResponseTime(ProjectTime,PTBean.getTaskhours());
                    PTBean.setTaskenddate(delayedTaskEndDate);
                  }
            }
        return PTBList;
    }
   
    
    //code not used any where
    public String randomGenetator(int size){
        System.out.println("Generating password using random() : ");
     
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?^";
        String values = Capital_chars + Small_chars + numbers + symbols;
 
        // Using random method
        SecureRandom rand=new SecureRandom();
 
        char[] password = new char[size];
 
        for (int i = 0; i < size; i++)
        {
            password[i] = values.charAt(rand.nextInt(values.length()));
        }
        System.out.println("Generared OTP :"+new String(password));
        return new String(password);
    }
    
    public boolean checkUserAuthorization(String[] roles, HttpServletRequest req ){
        HttpSession sess=req.getSession(true);
        UserDataBean uBean=(UserDataBean)sess.getAttribute("Luser");
        String userRole = uBean.getRole();
        
        for(String role : roles){
            if(userRole.equalsIgnoreCase(role)){
                return true;
            }
        }
        return false;
    }
    
}

 
 
 
    
    
    

