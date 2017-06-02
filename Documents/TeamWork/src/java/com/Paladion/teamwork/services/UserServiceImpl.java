package com.Paladion.teamwork.services;

import com.Paladion.teamwork.DAO.LoginDAO;
import com.Paladion.teamwork.DAO.UserDAO;
import com.Paladion.teamwork.beans.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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