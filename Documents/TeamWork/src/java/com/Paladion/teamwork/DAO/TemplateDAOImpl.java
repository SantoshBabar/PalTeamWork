/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

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
	public void addTaskToTemplate(int[] taskid, int[]weight, int TempID){
	int i,j;
	DatabaseUtils dbUtils=new DatabaseUtils();
		
	try{
	Connection conn=	dbUtils.getConnection();
	Statement st = (Statement)conn.createStatement();
	
	String query="Insert into template_task values(?,?,?)";
              for(i=0;i<taskid.length;i++)
		    {
               PreparedStatement statement=conn.prepareStatement(query);
               statement.setInt(1,TempID);
		statement.setInt(2, taskid[i]);
			 statement.setInt(3,weight[i]);
			 statement.executeUpdate();
			 
			    System.out.println("Insert Query executed for template task " +i);
		    }

	
		}catch(Exception ex){}
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
