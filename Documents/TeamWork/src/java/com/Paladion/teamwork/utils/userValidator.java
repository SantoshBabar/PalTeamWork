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
public class userValidator implements org.springframework.validation.Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDataBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        
      
	
		UserDataBean ubean = (UserDataBean) target;
                
		if(ubean.getUsername().length() == 0)
		{
		    errors.rejectValue("username","username.required");
		}
                else if(ubean.getEmail().length()==0){
                    errors.rejectValue("email","email.required");
                }
                else if(ubean.getPhone().length()==0){
                    errors.rejectValue("phone","phone.required");
                }
                else if(ubean.getTeam().length()==0){
                    errors.rejectValue("team","team.required");
                }
                else if(ubean.getPassword().length()==0){
                    errors.rejectValue("password","password.required");
                }
                else if(ubean.getRole().length()==0){
                    errors.rejectValue("role","role.required");
                }
                
                
                
    }
    
}
