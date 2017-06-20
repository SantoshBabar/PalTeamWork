/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;
import java.util.UUID;
import com.Paladion.teamwork.beans.UserDataBean;
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
    public UserDataBean Login(UserDataBean LB) {
        UserDataBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        System.out.println("Inside LoginDao");
        String userid="";
        String SQL_QUERY1 ="from UserDataBean as o where o.email=? and o.password=?";
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,LB.getEmail());
        query1.setParameter(1,LB.getPassword());
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
         SessUserBean=(UserDataBean) it.next();
            System.out.println("Query succefully executed");
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
    public UserDataBean ForgotPassword(UserDataBean LB){
	    UserDataBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        String userid="";
        String SQL_QUERY1 ="from UserDataBean as o where o.email=?";
        
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,LB.getEmail());
        
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
         SessUserBean=(UserDataBean) it.next();
        }
                        
        if ((list1 != null) && (list1.size() > 0)) 
        {
            EmailUtil email=new EmailUtil();
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid);
            String emailSubject="OTP";
            String emailMessage="Dear user \nYour OTP is: " +uuid+"";
            email.sendEmail(SessUserBean.getEmail(), emailSubject,emailMessage);
            
             System.out.print("i got your email id"+SessUserBean.getEmail());
             //update userdata set OTP=? where email=? email ge SessUserBean.getEmail()
             String sql = "update users set otp=? where email=?";
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
        
        
      
        
        
        
}
      @Override
    public UserDataBean ResetPassword(UserDataBean UB){
	    UserDataBean SessUserBean=null;
        Session session = this.sessionFactory.openSession();
        String userid="";
        String SQL_QUERY1 ="from UserDataBean as o where o.otp=?";
        
        Query query1 = session.createQuery(SQL_QUERY1);
        query1.setParameter(0,UB.getOtp());
        
        List list1=query1.list();
        Iterator it= list1.iterator();
        while(it.hasNext())
        {
            System.out.println("hello sumukh "+UB.getOtp());
            SessUserBean=(UserDataBean) it.next();
        }
                        
        if ((list1 != null) && (list1.size() > 0)) 
        {      
            String sql = "update userstable set password=?, OTP=? where OTP=?";
             SQLQuery query = session.createSQLQuery(sql);
             query.setParameter(0, UB.getPassword());
             query.setParameter(1, null);
             query.setParameter(2, UB.getOtp());
             query.executeUpdate();
             System.out.println("running");
        return SessUserBean;
        }
        else
        {
        return null;
        }            
}
}
        
              
    
        
    

