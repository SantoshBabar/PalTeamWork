/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.LoginDAO;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Administrator
 */
public class LoginServiceImpl implements LoginService{
    @Autowired
    @Qualifier(value="LoginDAO")
    LoginDAO LD;
    
    @Override
    public UserBean Login(LoginBean LB) {
         
       return LD.Login(LB);
    }
    
    
    
}
