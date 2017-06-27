/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.TaskBean;
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
public class TaskDAOImpl implements TaskDAO{
    
    @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	
	
	@Override
	public void addTaskDao(TaskBean TB) {
		
	Session session1 = sessionFactory.getCurrentSession();
	Transaction tx = null;
	tx = session1.beginTransaction();
	session1.save(TB);
	tx.commit();
	
	System.out.println("Task create successfully");
	}
	
    @Override
	public List<TaskBean> getAllTasks()
	{
	List <TaskBean> Tasklist=new ArrayList<TaskBean>();
	 Session session=sessionFactory.openSession();
        String taskquery= "from TaskBean";
        System.out.println("Get all tasks query");
        Query query2 = session.createQuery(taskquery);
       
        Tasklist= query2.list();
        return Tasklist;
        }
        
        
            @Override
	public boolean deleteTask(int id)
	{
            Session session = this.sessionFactory.openSession();
            String sql = "delete from tasks where taskid=?";
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            query.executeUpdate();
	return true;
        }
  

}