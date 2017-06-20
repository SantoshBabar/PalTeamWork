<%-- 
    Document   : DisplayProjectProgress
    Created on : 14 May, 2017, 9:07:43 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #ff6666;
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
    background-color: #ff1a1a;
}
</style>
<style>

@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);

body {

	color:#6a6f8c;
	background:#c8c8c8;
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
  background-color: #F7F7F7;
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
  background-color: #ff3333;
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
    width: 70%;
    
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #F7F7F7}

th {
    background-color: #ff3333;
    color: white;
}
</style>
</head>
    <body>
    
        <br><br>
        <div align="center">
<ul>
  <li><a href="CreateProject.do">Create New Project</a></li>
  <li><a href="showAllProject.do">View Projects</a></li>
  <li><a href="CreateTask.do">Create New Task</a></li>
  <li><a href="CreateUser.do">Create New user</a></li>
  <li><a href="CreateTaskTemplate.do">Create New Task Template</a></li>
 
  
  <li style="float:right"><a class="active" href="Logout.do">Logout</a></li>
</ul>
</div>
        <br>
        <div class="login-card">
	   <div align="center">  <h3 style="color: #ff3333; font-family: sans-serif; font-style: normal">Project Details</h3></div>
	   
	   <div class="right">
	   <tr><h4 style="color: red; font-size: 15px">Project Name: ${ProjectData.projectname}</h2></tr>
	   <tr><h4 style="color: red; font-size: 15px">OPID: ${ProjectData.opid}</h4></h3></tr>
	   
	   </div>
	   <div class="left">
	   <tr><h4 style="color: red; font-size: 15px">Lead Assigned: ${ProjectData.lead}</h4></tr>
             
              </tr><h4 style="color: red; font-size: 15px" >Start Date:
                   <fmt:formatDate type = "date" value = "${ProjectData.startdate}"/></h4></tr>
               </tr><h4 style="color: red; font-size: 15px" >End Date: 
                   <fmt:formatDate type = "date"  value = "${ProjectData.enddate}"/></h4></tr>
	   </div><br>

<table border="1" align="left">
    <tr>
        <th width="20%">Task Name </th>
        <th width="10%">Task Start Date</th>
        <th width="10%">Start End Date</th>
        <th width="10%">Hours</th>
        <th width="10%">Days</th>
        <th width="10%">Status</th>
        
    </tr>
</table>
           <br>   
           <br>
           <br>    
    <div style="overflow: auto;height: 200px; width: auto;"> 
        
          
    <table border="1" align="left">
   
    <c:forEach  items="${TaskDetails}" var="Task">  
        
        <fmt:formatDate value="${Task.taskstartdate}" var="SDate" type="both" dateStyle = "short" timeStyle = "short"/>
        <fmt:formatDate value="${Task.taskenddate}" var="EDate" type="both" dateStyle = "short" timeStyle = "short" />
        
        <tr> 
            <td width="21%" style="color: black">${Task.taskname}</td>
            <td width="10%" style="color: black">${SDate}</td>
	    <td width="10%" style="color: black">${EDate}</td>
            <td width="10%" style="color: black">${Task.taskhours}</td>
            <td width="10%" style="color: black">${Task.taskdays}</td>
            <td width="10%" style="color: black">${Task.status}</td>
        </tr>
           
</c:forEach>
</table>
</div>
    </body>
</html>
