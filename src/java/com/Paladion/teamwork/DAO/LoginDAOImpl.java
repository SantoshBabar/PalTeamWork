/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;
import com.Paladion.teamwork.beans.EmailBean;
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
    
 
}
        
              
    
        
    

