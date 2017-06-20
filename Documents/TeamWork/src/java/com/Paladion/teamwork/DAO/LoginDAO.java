/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.UserDataBean;


/**
 *
 * @author Administrator
 */
public interface LoginDAO {
    
    public UserDataBean Login(UserDataBean LB);
    public UserDataBean ForgotPassword(UserDataBean LB);
    public UserDataBean ResetPassword(UserDataBean LB);
}
