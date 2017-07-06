/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.UserDataBean;

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
	public boolean addUser(UserDataBean userBean) {
		try{
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
	        tx = session.beginTransaction();
		session.save(userBean);
		System.out.println("User added succefully ");
				 
	           tx.commit();
                   return true;
		}catch(Exception ex){
			System.out.println("In catch block: Exception raised");
			return false;
		}
	}
        
    

        
        
//      Unused Code  
//	
//	@Override
//	public List<UserDataBean> getUsersByRole(String role)
//	{
//		List<UserDataBean> UserList=new ArrayList<UserDataBean>();
//		UserDataBean ubean=new UserDataBean();
//		
//		Session session1 = sessionFactory.getCurrentSession();
//		Transaction tx;
//		tx = session1.beginTransaction();
//		
//		System.out.println("Get Users by Role UserDAO");
//	        String SQL_QUERY1= "from UserDataBean where role=?";
//                Query query2 = session1.createQuery(SQL_QUERY1);
//	        query2.setParameter(0,role);
//         
//                List list2 = query2.list();
//	        tx.commit();
//	        System.out.println("Query executed :)");
//	        Iterator it= list2.iterator();
//                    while(it.hasNext())
//                      {
//		           ubean=(UserDataBean) it.next();
//                           System.out.print("\nUser retrived from DB based on Role: "+role+" User name: "+ubean.getUsername());
//	                   UserList.add(ubean);
//                      }
//		
//	           return UserList;	
//	}
        
  
       @Override
	public List<UserDataBean> GetAllUser()
	{
		List<UserDataBean> UserList=new ArrayList<UserDataBean>();
                
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx;
		tx = session1.beginTransaction();
		
                String SQL_QUERY1= "from UserDataBean";
                Query query2 = session1.createQuery(SQL_QUERY1);
	        List list2 = query2.list();
                tx.commit();
                   
	        Iterator it= list2.iterator();
                   while(it.hasNext())
                    {
                       UserDataBean ubean=new UserDataBean();
		       ubean=(UserDataBean)it.next();
                       UserList.add(ubean);
                    }
		return UserList;	
	}
        
        
        @Override
	public boolean DeleteUser(int id)
	{
            Session session = this.sessionFactory.openSession();
            String sql = "Delete from users where userid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            query.executeUpdate();
            return true;	
	}
        
        
        @Override
	public boolean UpdateUserDetails(UserDataBean UB)
	{
            Session session = this.sessionFactory.openSession();
            Transaction tx;
            tx = session.beginTransaction();
            String sql = "UPDATE users SET username=?,email=?,team=?,phone=?,role=? WHERE userid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0,UB.getUsername());
            query.setParameter(1,UB.getEmail());
            query.setParameter(2,UB.getTeam());
            query.setParameter(3,UB.getPhone());
            query.setParameter(4,UB.getRole());
            query.setParameter(5,UB.getUserid());
            query.executeUpdate();
            tx.commit();
            return true;	
	}
        
        @Override
        public UserDataBean GetUserById(int id)
        {
            Session session1 = sessionFactory.getCurrentSession();
            Transaction tx;
            tx = session1.beginTransaction();
            UserDataBean ubean=new UserDataBean();
		
            String SQL_QUERY1= "from UserDataBean where userid=?";
                Query query2 = session1.createQuery(SQL_QUERY1);
                query2.setParameter(0, id);
	        List list2 = query2.list();
                Iterator it= list2.iterator();
                while(it.hasNext())
                  {
                     ubean=(UserDataBean)it.next();
                  }
                tx.commit();
                return ubean;       
        }
	

}

    

