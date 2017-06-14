/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.beans.UserBean;
import java.util.List;


/**
 *
 * @author user
 */
public interface UserDAO {

	//public void addUserDao(UserBean Ubean);
	public List<LoginBean> getUsersByRole(String role);

	public boolean addUser(LoginBean loginbean);
        
        public List<LoginBean> ViewAllUser();
        
        public boolean DeleteUser(int id);
        
       public boolean UpdateUser(int id);

}
