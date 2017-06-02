/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.LoginDAO;
import com.Paladion.teamwork.DAO.UserDAO;
import com.Paladion.teamwork.beans.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class UserServiceImpl implements UserService{
	
@Autowired
@Qualifier(value="UserDAO")
UserDAO userDAO;
	
	@Override
	public void addUser(LoginBean loginBean) {
		userDAO.addUser(loginBean);
		System.out.println("com.Paladion.teamwork.services.UserServiceImpl.addUser()");
		
		}
	
}
