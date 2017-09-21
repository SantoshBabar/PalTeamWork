/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.UserDataBean;
import org.springframework.validation.Errors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author sumukh.r
 */
public class Validator implements org.springframework.validation.Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDataBean.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDataBean sbean = (UserDataBean) target;
        String emailpattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(emailpattern);
        Matcher m = p.matcher(sbean.getEmail());
        if(sbean.getEmail().length()==0)
        {
              errors.rejectValue("email","email.required");
        }
        else if(!m.matches())
	{
	   errors.rejectValue("email","email.required");
        }
        
        else if(sbean.getPassword().length()==0)
        {
                    errors.rejectValue("password","password.required");
        }        
                
    }
    
}
