/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.DAO;

import com.Paladion.teamwork.beans.MapTemplateTaskBean;
import com.Paladion.teamwork.beans.ProjectBean;
import com.Paladion.teamwork.beans.ProjectTransactionBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
	public void addProjectDao(ProjectBean PB) {
	    Session session1 = sessionFactory.getCurrentSession();
            Transaction tx = null;
	    tx = session1.beginTransaction();
	    session1.save(PB );
	    tx.commit();
            System.out.println("Project created successfully");
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

	@Override
	public List<ProjectBean> getAllProjects() {
		
            Session session1 = sessionFactory.getCurrentSession();
	    Transaction tx = null;
	    tx = session1.beginTransaction();
            Criteria criteria = session1.createCriteria(ProjectBean.class);
	    List <ProjectBean>allProjects = criteria.list();
	    tx.commit();
	    return allProjects;
        }

        @Override
        public ProjectBean getProjectById(int id) {
	   Transaction tx = null;
	   Session session1 = sessionFactory.getCurrentSession();
           tx = session1.beginTransaction();
           String SQL_QUERY1= "from ProjectBean as O where O.projectid=?";
           Query query1 = session1.createQuery(SQL_QUERY1);
           query1.setParameter(0,id);
           List list1 = query1.list();       
           ProjectBean PB = (ProjectBean) list1.get(0);
           tx.commit();
        
           return PB;
      }

    @Override
    public void insertProjectTransaction(List <ProjectTransactionBean> PTBList){
        
        for(ProjectTransactionBean PTBean : PTBList){
                Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = null;
	        tx = session1.beginTransaction();
	        session1.save(PTBean);
	        tx.commit();
		System.out.println("Project transaction updated successfully");
        }
        
    }
    
    
        @Override
       public List<ProjectTransactionBean> getProjectTransaction(int projectid){
        
           List<ProjectTransactionBean> PList;
           Transaction tx = null;
	   Session session1 = sessionFactory.getCurrentSession();
           tx = session1.beginTransaction();
           Criteria criteria = session1.createCriteria(ProjectTransactionBean.class);
         criteria.add(Restrictions.eq("projectid", projectid));
//         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	   PList = criteria.list();
	 tx.commit();
           return PList;
    }

}