/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.UserDAO;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class UserServiceImpl implements UserService{
@Autowired
@Qualifier(value="UserDAO")
 UserDAO UserD;
	
	
	@Override
	public void addUser(UserBean ubean) {
		
		
		}

	@Override
	public List<LoginBean> getAllEngineers() {
		return UserD.getAllEngineers();
	}

	@Override
	public List<LoginBean> getAllLeads() {
	return UserD.getAllLeads();
	}
	
	
}
