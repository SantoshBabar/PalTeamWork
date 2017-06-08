/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;
import java.util.UUID;
import com.Paladion.teamwork.beans.LoginBean;
import com.Paladion.teamwork.beans.UserBean;
import com.Paladion.teamwork.utils.EmailUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Administrator
 */
public class LoginDAOImpl implements LoginDAO{
    
    @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public LoginBean Login(LoginBean LB) {
        LoginBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        String userid="";
        String SQL_QUERY1 ="from LoginBean as o where o.username=? and o.password=?";
        
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,LB.getUsername());
        query1.setParameter(1,LB.getPassword());
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
         SessUserBean=(LoginBean) it.next();
        
        }
                        
        if ((list1 != null) && (list1.size() > 0)) 
        {
        return SessUserBean;
        }
        else
        {
        return null;
        }            
    }
    @Override
    public LoginBean ForgotPassword(LoginBean LB){
	    LoginBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        String userid="";
        String SQL_QUERY1 ="from LoginBean as o where o.email=?";
        
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,LB.getEmail());
        
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
         SessUserBean=(LoginBean) it.next();
        }
                        
        if ((list1 != null) && (list1.size() > 0)) 
        {
            EmailUtil email=new EmailUtil();
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid);
            String emailSubject="OTP";
            String emailMessage="Dear user \n Your OTP is: " +uuid+"";
            email.sendEmail(SessUserBean.getEmail(), emailSubject,emailMessage);
            
             System.out.print("i got your email id"+SessUserBean.getEmail());
             //update userdata set OTP=? where email=? email ge SessUserBean.getEmail()
             String sql = "update userstable set OTP=? where email=?";
             SQLQuery query = session.createSQLQuery(sql);
             query.setParameter(0, uuid);
             query.setParameter(1, SessUserBean.getEmail());
             query.executeUpdate();
             
        return SessUserBean;
        }
        else
        {
        return null;
        }       
        
        
  ///////////////////////////////////////////////////////////////////      
        
        
        
}
      @Override
    public LoginBean ResetPassword(LoginBean LB){
	    LoginBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        String userid="";
        String SQL_QUERY1 ="from LoginBean as o where o.OTP=?";
        
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,LB.getOTP());
        
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
            System.out.println("hello sumukh "+LB.getOTP());
            
         SessUserBean=(LoginBean) it.next();
        }
                        
        if ((list1 != null) && (list1.size() > 0)) 
        {      
            String sql = "update userstable set password=? where OTP=?";
             SQLQuery query = session.createSQLQuery(sql);
             query.setParameter(0, LB.getPassword());
             query.setParameter(1, LB.getOTP());
             query.executeUpdate();
        return SessUserBean;
        }
        else
        {
        return null;
        }            
}
}
        
              
    
        
    

