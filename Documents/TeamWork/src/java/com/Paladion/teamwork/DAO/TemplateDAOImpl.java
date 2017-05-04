/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.TemplateBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class TemplateDAOImpl implements TemplateDAO{
	
	
	 @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	

	@Override
	public void addTemplateDao(TemplateBean TempB) {
	Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = null;
	tx = session1.beginTransaction();
	session1.save(TempB);
	tx.commit();
	
				System.out.println("Template create successfully");	
	}
	
}
