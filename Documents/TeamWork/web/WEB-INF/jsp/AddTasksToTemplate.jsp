<%-- 
    Document   : AddTasks
    Created on : May 4, 2017, 8:19:13 AM
    Author     : user
--%>
<%@page import="com.Paladion.teamwork.controllers.TaskController"%>
<%@page import="com.Paladion.teamwork.DAO.TaskDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.Paladion.teamwork.beans.TaskBean"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Tasks To Template</title>
</head>
<body>
<%@include file="Header.jsp" %>

   <%! TemplateBean TempB; String TempName;%>
        <% TempB=(TemplateBean)session.getAttribute("Template"); 
        TempName=TempB.getTemplatename().toString();
        %>
	   <h2>  Select the Tasks for the <%=TempName%> Template</h2> 
	   <%! TaskController TC;%>
	   <% 
		   TC=new TaskController();
	   List <TaskBean> list=new ArrayList<TaskBean>();
	   System.out.println("Inside Add task jsp file");
	   System.out.println(list);
	   list= TC.getAllTasks();
	   %>
	   
	   

</body>
</html>
