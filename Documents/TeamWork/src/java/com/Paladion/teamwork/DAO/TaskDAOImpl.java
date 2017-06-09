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
	public void getAllTasks()
	{
	List <TaskBean> Tasklist=new ArrayList<TaskBean>();
	System.out.println("Inside getAllTasks DAO");
        Tasklist= sessionFactory.getCurrentSession().createQuery("from Tasks").list();
	System.out.println("Inside Get all tasks method");
	
        Iterator<TaskBean> item= Tasklist.iterator();
        while(item.hasNext())
        {
        TaskBean TB = item.next();
        System.out.println(TB.getTaskname());
        }
    }
	
}
