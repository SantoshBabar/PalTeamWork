/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.SystemBean;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Lenovo
 */
public class AdminDAOImpl implements AdminDAO{
    
      @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
      @SuppressWarnings("empty-statement")
    public boolean SaveSettings(SystemBean SysModel) {
      Session session=sessionFactory.getCurrentSession();
      Transaction tx = null;
      
      try
      {
      tx = session.beginTransaction();    
      session.update(SysModel);
      tx.commit();
      }
     
      catch(Exception e){return false;};
      return true;
    }

    @Override
    public SystemBean getSystemSettings() {
        Session session1 = sessionFactory.getCurrentSession();
        SystemBean settings;
        Transaction tx = null;
	    tx = session1.beginTransaction();
            Criteria criteria = session1.createCriteria(SystemBean.class);    
            
            settings=(SystemBean) criteria.uniqueResult();
             tx.commit();
            return settings;
            
    }
    
}
