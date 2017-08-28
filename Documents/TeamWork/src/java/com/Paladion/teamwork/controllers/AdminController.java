/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.controllers;

import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.services.AdminService;
import java.util.List;
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

    public void setAservice(AdminService Aservice) {
        this.Aservice = Aservice;
    }

    @RequestMapping(value = "/Administration", method = RequestMethod.GET)
    public ModelAndView updateSettings() {
        SystemBean syssetting=Aservice.getSystemSettings();
        return new ModelAndView("Administration", "SysSettings",syssetting);

    }
    
    @RequestMapping(value = "/Administration",method = RequestMethod.POST)
    public ModelAndView updateSettings(@ModelAttribute("AdminModel") SystemBean Sysmodel)
    {
        Aservice.SaveSettings(Sysmodel);
        return new ModelAndView("Administration");
    }
    
}
