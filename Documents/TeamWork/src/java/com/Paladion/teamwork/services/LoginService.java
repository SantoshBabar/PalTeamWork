/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;

/**
 *
 * @author Administrator
 */
public interface LoginService {
    
    public UserBean Login(LoginBean LB);
    
}
