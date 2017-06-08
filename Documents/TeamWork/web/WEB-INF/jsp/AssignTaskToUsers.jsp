<%-- 
    Document   : AddTasks
    Created on : May 4, 2017, 8:19:13 AM
    Author     : user
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.Paladion.teamwork.controllers.TaskController"%>
<%@page import="com.Paladion.teamwork.DAO.TaskDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.Paladion.teamwork.beans.TaskBean"%>
<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);

body {
 
  background-image: url(new.jpg);
  background-repeat: repeat-y;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}

input{

border-bottom-color: black;

}

.login-card {
  
  top: 30%;
  padding: 40px;
  width: 700px;
  height: Auto;
  background-color: #F7F7F7;
  margin: 0 auto 10px;
  border-radius: 20px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}

.login-card h1 {
  font-weight: 100;
  text-align: center;
  font-size: 2.3em;
}



.login-card input[type=submit] {
  width: 100%;
  display: block;
  margin-bottom: 10px;
  position: relative;
}

.login-card input[type=text], input[type=password] {
  height: 44px;
  font-size: 16px;
  width: 20%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.login-card input[type=text]:hover, input[type=password]:hover {
  border: 1px solid #b9b9b9;
  border-top: 1px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}

.login {
  text-align: center;
  font-size: 14px;
  font-family: 'Arial', sans-serif;
  font-weight: 700;
  height: 36px;
  padding: 0 8px;
/* border-radius: 3px; */
/* -webkit-user-select: none;
  user-select: none; */
}

.login-submit {
  /* border: 1px solid #3079ed; */
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #ff3333;
  
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.login-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #ff0000;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.login-card a {
  text-decoration: none;
  color: #666;
  font-weight: 400;
  text-align: center;
  display: inline-block;
  opacity: 0.6;
  transition: opacity ease 0.5s;
}

.login-card a:hover {
  opacity: 1;
}

.login-help {
  width: 100%;
  text-align: center;
  font-size: 12px;
}
table {
    border-collapse: collapse;
    width: 100%;
    align-items: center;
    overflow:scroll;
}

tr,th {
    text-align:center;
}

</style>
</head>
    <body>
    <div align="left">
    <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
 
        <div class="login-card">
	   <div align="center">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Assign Tasks for the Engineers</h2><br></div>
        
           <form:form  action="AssignTaskToEngineers.do" method="post" modelAttribute="ProjectW">
	   
            <div style="overflow: auto;height: 350px; width: 700px;">
            <table>
            <div align="center">
            <tr><th>Task Name </th>  <th> Engineer</th></tr>
	   
             <c:forEach   varStatus="status"  items="${ProjectW.projectlist}"  var="task" >     
             <tr align="center"> <td><c:out value="${task.taskname}"/></td> 
	
	    <td>
		<form:select path="projectlist[${status.index}].userid">
	           <c:forEach  items="${AllEngineers}" var="engineer"> 
	           <form:option value="${engineer.userinfo.userId}" name="userid"><c:out value="${engineer.username}" /></form:option>
	           </c:forEach></form:select> </td>	
            
            <input type="hidden" name="projectlist[${status.index}].taskname" value="${task.taskname}"/>
            <input type="hidden" name="projectlist[${status.index}].taskhours" value="${task.taskhours}"/>
            <input type="hidden" name="projectlist[${status.index}].taskdays" value="${task.taskdays}"/>
            <input type="hidden" name="projectlist[${status.index}].projectid" value="${task.projectid}"/>
            <input type="hidden" name="projectlist[${status.index}].status" value="${task.status}"/>
            
              <input type="hidden" name="projectid" value="${task.projectid}"/>
	   </tr>
           </c:forEach>
               
	 
	   </div>
	   
	   </table>
	   </div>
	   <tr></tr>
	   <tr><td><input type="submit" value="Create" class="login login-submit"/></td></tr>
	   </form:form>
	  
	   <center>${Temperror}</center><br>
</html>
