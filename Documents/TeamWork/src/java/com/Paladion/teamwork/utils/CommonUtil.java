/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
public class CommonUtil {
    
    public List<MapTemplateTaskBean> Maptasktotemplate(HttpServletRequest req,HttpSession session)
    {
           List<MapTemplateTaskBean> mTTBList=new ArrayList<MapTemplateTaskBean>();
	List<TaskBean> tasklist = null;
           TemplateBean TempB=null;
           int Tempid,i,j=0;
        
        //retrieving tasks and templates from session stored in controller
	TempB=(TemplateBean)session.getAttribute("Template"); 
            tasklist=(List<TaskBean>)session.getAttribute("TaskList");
        
           Tempid=TempB.getTemplateid();
           System.out.println("The templateid to which tasks will be added: "+Tempid);
		 
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
    
    
     public List<ProjectTransactionBean> assignEngineerToProject(HttpServletRequest req)
 {
	 List<ProjectTransactionBean> TransactionList=new ArrayList<ProjectTransactionBean>();
	 ProjectTransactionBean PTB=new ProjectTransactionBean();
	String[] strUserID=req.getParameterValues("userid");
	String[] taskName=req.getParameterValues("taskname");
	int i=0;
	int[] userid=new int[strUserID.length];
	
           for(String str:strUserID)
          {
               userid[i]=Integer.parseInt(str);//Exception in this line
                i++;
          }
		 
		 
	 
	 
	 
	 return null;
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
//    
// public Date getTaskEndTime(Date sDate, int hours){
//       
//    Calendar startDate = Calendar.getInstance();
//    startDate.setTime(sDate);    
//    while (hours > 0){
//        int step = 0,step1 = 0;
//        if(hours >= 9) step1 = 24;
//        else step = hours;          
//        hours -= step;          
//        startDate.add(Calendar.HOUR_OF_DAY, step1);          
//        int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
//        if(dayOfWeek == Calendar.SATURDAY) hours += 24;
//        if(dayOfWeek == Calendar.SUNDAY) hours += 24;
//    }
//    return startDate.getTime();
//}
//   
    
 Date calculateResponseTime(Calendar ProjectTime, float ProjectDurationinHours) {
     
int fromHour = 10;//start time of the day usually 10 AM
int fromMinute = 0;
int toHour = 19;//end time of the day usually 7 PM
int toMinute = 0;
Date end = null;

 
//    int hourOfDay = ProjectTime.get(Calendar.HOUR_OF_DAY);
//    int dayOfWeek = ProjectTime.get(Calendar.DAY_OF_WEEK);

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
    
    
    
    
    
    
    

 
 
 
    
    
    
}
