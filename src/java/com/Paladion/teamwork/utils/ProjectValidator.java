/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.ProjectBean;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.validation.Errors;

/**
 *
 * @author reshma.m
 */
public class ProjectValidator implements org.springframework.validation.Validator{
  
    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectBean.class.isAssignableFrom(clazz);
      
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        
      
	
		ProjectBean pbean = (ProjectBean) target;
                
                Date startDate = null;
                Date endDate = null;
                
               
        try {
             startDate = pbean.getStartdate();
            endDate = pbean.getEnddate();
        } catch (ParseException ex) {
              startDate = null;
               endDate = null;
                
            Logger.getLogger(ProjectValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
                
		if(pbean.getOpid().length() == 0)
		{
		    errors.rejectValue("opid","opid.required");
		}
                else if(pbean.getProjectname().length() == 0)
		{
		    errors.rejectValue("projectname","projectname.required");
		}
                    else if(pbean.getLeadid()<=0)
		{
		    errors.rejectValue("leadid","leadid.required");
		}
                else if(null == startDate)
		{
		    errors.rejectValue("startdate","startdate.required");
		}
                  else if(null == endDate)
		{
		    errors.rejectValue("enddate","enddate.required");
		}
              
                   else if(pbean.getTemplateid()<=0)
		{
		    errors.rejectValue("templateid","templateid.required");
		}
    }
    
}
