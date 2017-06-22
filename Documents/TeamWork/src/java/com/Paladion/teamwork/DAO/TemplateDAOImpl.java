/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
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
    
     public  List<TemplateBean> getAllTemplates() {
        
 	List <TemplateBean> templateList=new ArrayList<TemplateBean>();
	 
        Session session=sessionFactory.openSession();
        String templatequery= "from TemplateBean";
        System.out.println("template query");
        Query query2 = session.createQuery(templatequery);
       
        templateList= query2.list();
	
         
        return templateList;
     }
    
    
        @Override
	public boolean deleteTemplate(int id)
	{
            Session session = this.sessionFactory.openSession();
            String sql = "delete from templates where templateid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            query.executeUpdate();
            
            Session session1 = this.sessionFactory.openSession();
            String sql1 = "delete from template_task where templateid=?";
            SQLQuery query1 = session1.createSQLQuery(sql1);
            query1.setParameter(0, id);
            query1.executeUpdate();
            
            return true;	
	}
    
	
        @Override
	public List<MapTemplateTaskBean> getAllWeights(int tempID) {
		 
           System.out.println("com.Paladion.teamwork.DAO.ProjectDAOImpl.getAllWeights()");
           MapTemplateTaskBean MTTB;
	   Session session1 = sessionFactory.getCurrentSession();
	   Transaction tx = null;
           tx = session1.beginTransaction();
           String SQL_QUERY1= "from MapTemplateTaskBean as O where O.templateid=?";
           Query query2 = session1.createQuery(SQL_QUERY1);
           query2.setParameter(0,tempID);
           
           List list2 = query2.list();
	   System.out.println("Query executed :)");
	   Iterator it= list2.iterator();
	   tx.commit();
                while(it.hasNext())
                  {
                      MTTB=(MapTemplateTaskBean) it.next();
                      System.out.print("Taskid from the DB"+MTTB.getTaskid());
                  }
	   return list2;
        }

        
        
}
