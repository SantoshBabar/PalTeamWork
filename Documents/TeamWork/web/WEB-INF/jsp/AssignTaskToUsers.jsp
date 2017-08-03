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
<link rel="icon" href="Network-Security.png" type="image/x-icon">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"></script>
<head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #a6a6a6;
    width:1500px;
   
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #b30000;
}

.active {
    background-color: #cc0000;
}
</style>
<style>

@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);

body {

	background-image: url("grey.jpg");
  background-repeat: repeat-y;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}

.login-card {
  padding: 40px;
  width: 1420px;
  height: 550px;
   background-color: white;
  margin: 0 auto 10px;
  border-radius: 2px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}

.login-card h1 {
  font-weight: 1;
  text-align: center;
  font-size: 2.3em;
}

.login-card input[type=submit] {
  width: 20%;
  display: block;
  margin-bottom: 10px;
  position: relative;
  float: center;
}

.login-card input[type=text], input[type=password] {
  height: 44px;
  font-size: 16px;
  width: 30%;
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
  border: 2px solid #b9b9b9;
  
  border-top: 5px solid #a0a0a0;
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
  width: 50%;
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #a6a6a6;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.login-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #ff8080;
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
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #F7F7F7}

th {
    background-color: #a6a6a6;
    color: white;
}
</style>
<script>
$(document).ready(function() {
    $('#example').DataTable( {
        "scrollY":"200px",
        "scrollCollapse": true,
        "paging":         false
    } );
} );
</script>
<title>Assign Tasks to Engineers</title>

</head>
    <body>
    
     <%@include file="Header.jsp" %>
        
        <div class="login-card">
	   <div align="left">  <h2 style="color: #a6a6a6; font-family: sans-serif; font-style: normal">Assign Tasks for the Engineers</h2><br></div>
      
           <form:form  action="AssignTaskToEngineers.do" method="post" modelAttribute="ProjectW">
	   
            
                
            <table border="1" id="example" class="display" width="100%"  cellspacing="0">
            <thead>
            <tr bgcolor="#a6a6a6">
            <th>Task Name </th>  <th> Engineer</th>
            </tr>
            </thead>
            
           <tbody>
             <c:forEach   varStatus="status"  items="${ProjectW.projectlist}"  var="task" >     
             <tr align="center"> <td><c:out value="${task.taskname}"/></td> 
	
	    <td>
		<form:select path="projectlist[${status.index}].userid">
	           <c:forEach  items="${AllEngineers}" var="engineer"> 
	           <form:option value="${engineer.userid}" name="userid"><c:out value="${engineer.username}" /></form:option>
                   </c:forEach></form:select> </td>	
            
             <h4> <input style="" type="hidden" name="projectlist[${status.index}].taskname" value="${task.taskname}"/></h4>
             
             <input style="font-family: cursive; font-size: 20px" type="hidden" name="projectlist[${status.index}].taskhours" value="${task.taskhours}"/>
            <input type="hidden" name="projectlist[${status.index}].taskdays" value="${task.taskdays}"/>
            <input type="hidden" name="projectlist[${status.index}].projectid" value="${task.projectid}"/>
            <input type="hidden" name="projectlist[${status.index}].status" value="${task.status}"/>
            
              <input type="hidden" name="projectid" value="${task.projectid}"/>
	   </tr>
           </c:forEach>
           </tbody>
	   </table>
               
               <input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
               <tr><td><input type="submit" value="Create" class="login login-submit"/></td></tr>
	   </div>
	  
	  
	   </form:form>
	  
	   <center>${Temperror}</center><br>
</html>
