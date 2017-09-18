/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.services.AdminService;
import com.Paladion.teamwork.utils.CommonUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lenovo
 */
@Controller
public class AdminController {

    @ModelAttribute("AdminModel")
    public SystemBean populate() 
    {
        return new SystemBean();
    }

    @Autowired()
    @Qualifier(value = "AdminService")
    AdminService Aservice;
    
    @Autowired
    @Qualifier(value = "CommonUtil")
    CommonUtil CU;

    public void setAservice(AdminService Aservice) {
        this.Aservice = Aservice;
    }

    @RequestMapping(value = "/Administration", method = RequestMethod.GET)
    public ModelAndView updateSettings(HttpServletRequest req) {
        
        String[] authorizedRoles = {"admin","manager"};
        boolean authorized =CU.checkUserAuthorization(authorizedRoles, req);
		
        if(authorized==true){
        SystemBean syssetting=Aservice.getSystemSettings();
        return new ModelAndView("Administration", "SysSettings",syssetting);
        }
        else{
            return new ModelAndView("Error");
        }
    }
    
    @RequestMapping(value = "/Administration",method = RequestMethod.POST)
    public ModelAndView updateSettings(@ModelAttribute("AdminModel") SystemBean Sysmodel, HttpServletRequest req)
    {
        String[] authorizedRoles = {"admin","manager"};
        boolean authorized =CU.checkUserAuthorization(authorizedRoles, req);
		
        if(authorized==true){
        Aservice.SaveSettings(Sysmodel);
        return new ModelAndView("Administration");
        }
        else{
            return new ModelAndView("Error");
        }
    }
    
}
