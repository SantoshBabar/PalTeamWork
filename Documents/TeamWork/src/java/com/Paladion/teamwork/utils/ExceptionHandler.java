/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionHandler {
    
@org.springframework.web.bind.annotation.ExceptionHandler
public ModelAndView handleException(Exception ex) 
{
return new ModelAndView("Customerror");
}
    
}
