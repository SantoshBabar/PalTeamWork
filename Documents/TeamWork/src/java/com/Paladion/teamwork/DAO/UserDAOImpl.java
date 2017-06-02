/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class UserDAOImpl implements UserDAO{

	 @Autowired
           @Qualifier(value="hibernate4AnnotatedSessionFactory")
           private SessionFactory sessionFactory;

           public void setSessionFactory(SessionFactory sessionFactory) {
                       this.sessionFactory = sessionFactory;
           }
	
	
	@Override
	public boolean addUser(LoginBean loginBean) {
		try{
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
	            tx = session.beginTransaction();
		UserBean ub=loginBean.getUserinfo();
		ub.setName(loginBean.getUsername());
		session.save(loginBean);
		session.save(ub);
		System.out.println("done");
				 
	           tx.commit();
		}catch(Exception ex){
			System.out.println("In catch block: Exception raised");
			return false;
		}
		System.out.println("User created successfully");
		return true;
		
	}
	
}
