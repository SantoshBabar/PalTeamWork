package com.Paladion.teamwork.services;


import com.Paladion.teamwork.DAO.UserDAO;
import com.Paladion.teamwork.beans.UserDataBean;

import java.util.List;

import com.Paladion.teamwork.DAO.LoginDAO;
import com.Paladion.teamwork.DAO.UserDAO;
import com.Paladion.teamwork.beans.UserDataBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserServiceImpl implements UserService{
	
@Autowired
@Qualifier(value="UserDAO")
UserDAO userDAO;
	
@Override
	public boolean addUser(UserDataBean loginBean) {
		return userDAO.addUser(loginBean);
		//System.out.println("com.Paladion.teamwork.services.UserServiceImpl.addUser()");	

}
	
	
	
//	@Override
//	public List<UserDataBean> getUsersByRole(String role) {
//		return userDAO.getUsersByRole(role);
//	}
        
        @Override
	public List<UserDataBean> GetAllUser() {
		return userDAO.GetAllUser();
		
}

    @Override
    public boolean DeleteUser(int id) {
        return userDAO.DeleteUser(id);
    }
    
    @Override
	 public boolean UpdateUserDetails(UserDataBean LB) {
		return userDAO.UpdateUserDetails(LB);
		
}

    
@Override
public UserDataBean GetUserById(int id){
    
    return userDAO.GetUserById(id);
}
    

    
        
		
}




