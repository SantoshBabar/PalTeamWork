/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Paladion.teamwork.utils;

import com.Paladion.teamwork.beans.TaskBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class DatabaseUtils {
	
	public Connection getConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		System.out.println("Inside getConnection mmethod");
Class.forName("com.mysql.jdbc.Driver").newInstance();
System.out.println("Utils Class.forname done");
Connection conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/teamwork?zeroDateTimeBehavior=convertToNull", "root", "root");
System.out.println("inside getConnection method, Connection eastablished");
return conn;
	}
	
	
	public  List<TaskBean>getAllTasks() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		
		System.out.println("Inside Utils Class");
Class.forName("com.mysql.jdbc.Driver").newInstance();
System.out.println("Utils Class.forname done");
Connection conn =getConnection();
	   System.out.println("Utils Connection eastablished");
String query = "select * from Tasks";

Statement st = (Statement)conn.createStatement();

System.out.println("Utils Query executed");
ResultSet rs = st.executeQuery(query);
List <TaskBean> taskList=new ArrayList<TaskBean>();
		while(rs.next()){
			
			TaskBean tb=new TaskBean();
			tb.setTaskid(rs.getInt("taskid"));
			System.out.println("Task Id in Database utils: "+tb.getTaskid());
			tb.setTaskname(rs.getString("taskname"));
			taskList.add(tb);
			
		}
		
		return taskList;
	}
	
}
