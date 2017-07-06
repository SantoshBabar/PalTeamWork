/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.UserDataBean;
import com.Paladion.teamwork.beans.TemplateBean;

import java.util.List;


/**
 *
 * @author user
 */
public interface UserDAO {

	//public void addUserDao(UserBean Ubean);
	//public List<UserDataBean> getUsersByRole(String role);

	public boolean addUser(UserDataBean loginbean);
        
        public List<UserDataBean> GetAllUser();
        
        public boolean DeleteUser(int id);
        
       public boolean UpdateUserDetails(UserDataBean LB);
       
       public UserDataBean GetUserById(int id);

}
