/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
public class ForgotPasswordController {
	
@RequestMapping(value="/ForgotPassword",method=RequestMethod.GET)
public String forgot()
{
 
return "ForgotPassword";
}
	
	
}
