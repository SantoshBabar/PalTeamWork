/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public interface LoginService {
    
    public LoginBean Login(LoginBean LB);
    public void Logout(HttpSession sess);
    public LoginBean ForgotPassword(LoginBean LB);
    public LoginBean ResetPassword(LoginBean LB);
}
