/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;

/**
 *
 * @author Administrator
 */
public interface LoginDAO {
    
    public UserBean Login(LoginBean LB);
    public LoginBean ForgotPassword(LoginBean LB);
}
