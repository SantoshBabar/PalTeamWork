/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.UserDataBean;


import java.util.List;



/**
 *
 * @author user
 */
public interface UserService {

	
		  // public List<UserDataBean> getUsersByRole(String role);
		  

	           public boolean addUser(UserDataBean loginBean);
                   
                   public List<UserDataBean> GetAllUser();
                   
                   public boolean DeleteUser(int id);
                   
                   public boolean UpdateUserDetails(UserDataBean LB);
                   
                   public UserDataBean GetUserById(int id);

}
