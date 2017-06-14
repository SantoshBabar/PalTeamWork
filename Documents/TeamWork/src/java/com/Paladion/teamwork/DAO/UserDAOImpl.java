/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
	
	@Override
	public List<LoginBean> getUsersByRole(String role)
	{
		List<LoginBean> UserList=new ArrayList<LoginBean>();
		LoginBean ubean=new LoginBean();
		
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx;
		tx = session1.beginTransaction();
		
		System.out.println("Get Users by Role UserDAO");
	           String SQL_QUERY1= "from LoginBean where role=?";
                      Query query2 = session1.createQuery(SQL_QUERY1);
	           query2.setParameter(0,role);
         
                      List list2 = query2.list();
	           tx.commit();
	           System.out.println("Query executed :)");
	           Iterator it= list2.iterator();
                      while(it.hasNext())
                      {
		           ubean=(LoginBean) it.next();
                                  System.out.print("\nUser retrived from DB based on Role: "+role+" User name: "+ubean.userinfo.getName());
	                       UserList.add(ubean);
                      }
		
	           return UserList;	
	}
        
        
        //////////////////////////////////
       @Override
	public List<LoginBean> ViewAllUser()
	{
		List<LoginBean> UserList=new ArrayList<LoginBean>();
                
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx;
		tx = session1.beginTransaction();
		
                String SQL_QUERY1= "from LoginBean";
                Query query2 = session1.createQuery(SQL_QUERY1);
	           
         
                      List list2 = query2.list();
                      
	           tx.commit();
                   
	           
	           Iterator it= list2.iterator();
                      while(it.hasNext())
                      {
                          LoginBean ubean=new LoginBean();
		            ubean=(LoginBean)it.next();
                           UserList.add(ubean);
                          
	                     
                      }
		
	           return UserList;	
	}
        
        
        @Override
	public boolean DeleteUser(int id)
	{
            Session session = this.sessionFactory.openSession();
            String sql = "Delete from userstable where userId=?";
             SQLQuery query = session.createSQLQuery(sql);
             query.setParameter(0, id);
             query.executeUpdate();
             
		return true;	
	}
        
        
        @Override
	public boolean UpdateUser(int id)
	{
            Session session = this.sessionFactory.openSession();
            String sql = "select from userstable where userId=?";
             SQLQuery query = session.createSQLQuery(sql);
             query.setParameter(0, id);
             query.executeUpdate();
             
		return true;	
	}	
	}

    

