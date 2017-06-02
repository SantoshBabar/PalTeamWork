/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import java.util.List;

/**
 *
 * @author user
 */
public interface UserService {
	public void addUser(UserBean ubean);
		   public List<LoginBean> getAllEngineers();
		   public List<LoginBean> getAllLeads();
}
