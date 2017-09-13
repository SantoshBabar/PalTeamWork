/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.TemplateBean;
import org.springframework.validation.Errors;

/**
 *
 * @author reshma.m
 */
public class TaskTemplateValidator implements org.springframework.validation.Validator{
   @Override
    public boolean supports(Class<?> clazz) {
        return TemplateBean.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        
       TemplateBean tbean=(TemplateBean) target;
       
        if(tbean.getTemplatename().length() == 0)
		{
		    errors.rejectValue("templatename","templatename.required");
		}
            if(tbean.getTemplateDesc().length() == 0)
		{
		    errors.rejectValue("templateDesc","templateDesc.required");
		}
        
    }
}
