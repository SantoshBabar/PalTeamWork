/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.TaskBean;
import com.Paladion.teamwork.beans.TemplateBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author user
 */
public class DatabaseUtils {

    @Autowired
    @Qualifier(value="hibernate4AnnotatedSessionFactory")
    private SessionFactory sessionFactory;

    
	
public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/teamwork?zeroDateTimeBehavior=convertToNull", "root1", "root");
        return conn;
}
	
	
public  List<TaskBean>getAllTasks() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
{
	List <TaskBean> taskList=new ArrayList<TaskBean>();
        Session session;
       
        session = sessionFactory.openSession();
        String taskquery= "from TaskBean";
        System.out.println(taskquery);
        Query query2 = session.createQuery(taskquery);
       
        taskList= query2.list();
	
        return taskList;
     }




public  List<TemplateBean>getAllTemplates() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
{
	List <TemplateBean> templateList=new ArrayList<TemplateBean>();
	Connection conn =getConnection();
	String query = "select * from Templates";
	try{
		Statement st = (Statement)conn.createStatement();
	           ResultSet rs = st.executeQuery(query);

		while(rs.next())
		{
			TemplateBean tb=new TemplateBean();
			tb.setTemplateid(rs.getInt("templateid"));
			tb.setTemplatename(rs.getString("templatename"));
			templateList.add(tb);
		}
	}
	catch(Exception ex){return null;}
           finally {
                      if (conn != null) {
                         try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
             }
	}	
           return templateList;
     }


//public List<TaskWeightBean> getWeights(){
//	List <TaskWeightBean> TaskWeightList=new ArrayList<TaskWeightBean>();
//	Connection conn =getConnection();
//	String query1 = "select * from template_task";
//	try{
//		Statement st = (Statement)conn.createStatement();
//	           ResultSet rs = st.executeQuery(query);
//
//		while(rs.next())
//		{
//			TemplateBean tb=new TemplateBean();
//			tb.setTemplateid(rs.getInt("templateid"));
//			tb.setTemplatename(rs.getString("templatename"));
//			templateList.add(tb);
//		}
//	}
//	catch(Exception ex){return null;}
//           finally {
//                      if (conn != null) {
//                         try { conn.close(); } catch (Exception e) { /* handle close exception, quite usually ignore */ } 
//             }
//	}	
//           return templateList;
//}

}
