/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import java.util.ArrayList;
import java.util.Arrays;
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
    
}
