/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.SystemBean;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface AdminService {
    
    public boolean SaveSettings(SystemBean SysModel);
    public SystemBean getSystemSettings();
}
