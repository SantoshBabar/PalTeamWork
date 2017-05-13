/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.Paladion.teamwork.utils.DatabaseUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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

	@Override
	public boolean addTaskToTemplate(MapTemplateTaskBean MTT){
        try 
        {
        Session session1 = sessionFactory.getCurrentSession();
        Transaction tx = null;
	tx = session1.beginTransaction();
	session1.save(MTT);
	tx.commit();
	
	System.out.println("Template weights added successfully");    
        return true;
        }
        catch(Exception e){e.printStackTrace();return false;}
	}

@Override
    public List<TaskBean> getAllTasksforTemplate() 
    {
        
        List <TaskBean> taskList=new ArrayList<TaskBean>();
        Session session=sessionFactory.openSession();
       String taskquery= "from TaskBean";
        System.out.println(taskquery);
        Query query2 = session.createQuery(taskquery);
       
          taskList= query2.list();
	
           return taskList;
        
      }
	
}
