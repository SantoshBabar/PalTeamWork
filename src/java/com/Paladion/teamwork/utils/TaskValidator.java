/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.UserDataBean;
import org.springframework.validation.Errors;

/**
 *
 * @author sumukh.r
 */
public class TaskValidator implements org.springframework.validation.Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return TaskBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
      
	
		TaskBean tbean = (TaskBean) target;
                
		if(tbean.getTaskname().length() == 0)
		{
		    errors.rejectValue("taskname","taskname.required");
		}
                else if(tbean.getDescription().length()==0)
                {
                    errors.rejectValue("Description","Description.required");
                }
                
                
    }
    
}
