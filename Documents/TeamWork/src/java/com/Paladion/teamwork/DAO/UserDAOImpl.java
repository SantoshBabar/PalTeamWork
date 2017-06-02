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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class UserDAOImpl implements UserDAO{

	 @Autowired
           @Qualifier(value="hibernate4AnnotatedSessionFactory")
           private SessionFactory sessionFactory;

           public void setSessionFactory(SessionFactory sessionFactory) {
                       this.sessionFactory = sessionFactory;
           }
	
	
	@Override
	public void addUserDao(UserBean UB) {
		
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = null;
	            tx = session1.beginTransaction();
	           session1.save(UB);
	           tx.commit();
		System.out.println("User created successfully");
	}
	
	@Override
	public List<LoginBean> getAllEngineers(){
		List<LoginBean> allEngineers=new ArrayList<LoginBean>();
		
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx= null;
		 tx = session1.beginTransaction();
		 System.out.println("Get all engineers UserDAO");
     
	//Session session1 = sessionFactory.getCurrentSession();
	        
        String SQL_QUERY1= "from LoginBean where role='engineer'";
        Query query2 = session1.createQuery(SQL_QUERY1);
            LoginBean ubean=new LoginBean();
        List list2 = query2.list();
	   System.out.println("Query executed :)");
	 Iterator it= list2.iterator();
        while(it.hasNext())
        {
		
         ubean=(LoginBean) it.next();
         System.out.print("\nUser name Engineers from the DB"+ubean.getUserid());
	    allEngineers.add(ubean);
        }
	   return allEngineers;	
	}
	
	@Override
	public List<LoginBean> getAllLeads(){
		List<LoginBean> allLeads = new ArrayList <LoginBean>();
		
		
		 System.out.println("Get all leads UserDAO");
      LoginBean ubean=new LoginBean();
	Session session1 = sessionFactory.getCurrentSession();
	        
        String SQL_QUERY1= "from LoginBean where role='engineer'";
        Query query2 = session1.createQuery(SQL_QUERY1);
        
        List list2 = query2.list();
	   System.out.println("Query executed :)");
	 Iterator it= list2.iterator();
        while(it.hasNext())
        {
         ubean=(LoginBean) it.next();
         System.out.print("\nUser name Leads from the DB"+ubean.userinfo.getName());
	 allLeads.add(ubean);
        }
	   return allLeads;	
	}
	
	
	
	
	
}
