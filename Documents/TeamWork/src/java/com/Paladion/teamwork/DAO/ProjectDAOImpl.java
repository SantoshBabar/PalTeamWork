/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TaskWeightBean;
import com.Paladion.teamwork.beans.UserBean;
import java.util.Iterator;
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
public class ProjectDAOImpl implements ProjectDAO
{
           @Autowired
           @Qualifier(value="hibernate4AnnotatedSessionFactory")
           private SessionFactory sessionFactory;

           public void setSessionFactory(SessionFactory sessionFactory) {
                       this.sessionFactory = sessionFactory;
           }
	
	@Override
	public void addProjectDao(ProjectBean PB) 
	{
		Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = null;
	            tx = session1.beginTransaction();
	           session1.save(PB );
	           tx.commit();
		System.out.println("Project created successfully");
	}

	@Override
	public List<TaskWeightBean> getAllWeights(int tempID) {
		 
		System.out.println("com.Paladion.teamwork.DAO.ProjectDAOImpl.getAllWeights()");
		Session session1 = sessionFactory.getCurrentSession();
		String SQL_QUERY1= "select template_task.taskid,template_task.weight,Tasks.taskname from template_task INNER JOIN Tasks on template_task.taskid=Tasks.taskid where template_task.templateid=?";

        TaskWeightBean TB;
        Query query2 = session1.createSQLQuery(SQL_QUERY1);
        query2.setParameter(0,tempID);
        
        List list2 = query2.list();
	 Iterator it= list2.iterator();
        while(it.hasNext())
        {
         TB=(TaskWeightBean) it.next();
         System.out.print("Taskid from the DB"+TB.getTaskid());
        }
	   return list2;
		
	}
	
}
	

