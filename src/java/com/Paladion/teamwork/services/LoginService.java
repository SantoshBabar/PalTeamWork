/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.UserDataBean;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public interface LoginService {
    
    public UserDataBean Login(UserDataBean LB);
    public void Logout(HttpSession sess);
   
}
