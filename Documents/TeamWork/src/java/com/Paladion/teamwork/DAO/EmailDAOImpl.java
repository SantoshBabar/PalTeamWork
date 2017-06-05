/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.EmailTemplateBean;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class EmailDAOImpl implements EmailDAO{
	
	
    @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public boolean createEmailTemplate(EmailTemplateBean emailTempBean) {
		try{
		Session session=sessionFactory.openSession();
		Transaction tx = null;
	            tx = session.beginTransaction();
	           session.save(emailTempBean );
	           tx.commit();
		}catch(Exception ex){
			return false;
			
		}
		return true;
	}

	@Override
	public boolean updateEmailTemplate(EmailTemplateBean emailTempBean) {
		try{
		Session session=sessionFactory.openSession();
		Transaction tx = null;
	            tx = session.beginTransaction();
	           session.update(emailTempBean );
	           tx.commit();
		}catch(Exception ex){
			return false;
			
		}
		return true;
	}

	@Override
	public List<EmailTemplateBean> listEmailTemplate() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean deleteEmailTemplate(EmailTemplateBean emailTempBean) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
