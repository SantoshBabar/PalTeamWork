/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.SystemBean;
import com.Paladion.teamwork.DAO.AdminDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService
{
    @Autowired
    AdminDAO AdminDAO;

    @Override
    public boolean SaveSettings(SystemBean SysModel) {
        
        return AdminDAO.SaveSettings(SysModel);
    }

    @Override
    public SystemBean getSystemSettings() {
    return AdminDAO.getSystemSettings();    
    }
    
}
