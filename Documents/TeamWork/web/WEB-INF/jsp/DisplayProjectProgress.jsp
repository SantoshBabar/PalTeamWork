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
    width:80%;
}

table1 {
    border-collapse: collapse;
    width:40%;
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
    
  <%@include file="Header.jsp" %>
        
    <div class="login-card" >
        <div align="center">  <h3 style="color: #ff3333; font-family: sans-serif; font-style: normal">Project Details</h3></div>
	<div class="right">
            <table class="table1" border="1">
        <tr>
            <td ><h4 style="color: red; font-size: 15px">Project Name: ${ProjectData.projectname}</h2></td>
            <td ><h4 style="color: red; font-size: 15px">OPID: ${ProjectData.opid}</h4></h3></td>
        </tr>
	   
	<tr>
            <td><h4 style="color: red; font-size: 15px">Lead Assigned: ${ProjectData.lead}</h4></td>
            <td><h4 style="color: red; font-size: 15px">Project Status: ${ProjectData.status}</h4></td>
        </tr>
        
        <tr>
            
            <td> <h4 style="color: red; font-size: 15px" >Start Date:
                   <fmt:formatDate type = "date" value = "${ProjectData.startdate}"/></h4></td>
            <td> <h4 style="color: red; font-size: 15px" >End Date: 
                   <fmt:formatDate type = "date"  value = "${ProjectData.enddate}"/></h4></td>
        </tr>
        </table>
        <br>
	</div>

<table border="1" align="left">
    <tr>
        <th>Task Name </th>
        <th>Engineer Name </th>
        <th>Task Start Date</th>
        <th>Start End Date</th>
        <th>Hours</th>
        <th>Days</th>
        <th>Status</th>
        
    </tr>
</table>
           <br>   
           <br>
              
    <div style="overflow: auto;height: 500px; width: auto;"> 
        
          
    <table border="1" align="left">
   
    <c:forEach  items="${TaskDetails}" var="ProjectTaskList">  
        
        <fmt:formatDate value="${ProjectTaskList.taskstartdate}" var="SDate" type="both" dateStyle = "short" timeStyle = "short"/>
        <fmt:formatDate value="${ProjectTaskList.taskenddate}" var="EDate" type="both" dateStyle = "short" timeStyle = "short" />
        
        <tr> 
            <td style="color: black">${ProjectTaskList.taskname}</td>
            <td style="color: black">${ProjectTaskList.engname}</td>
            <td style="color: black">${SDate}</td>
	    <td style="color: black">${EDate}</td>
            <td style="color: black">${ProjectTaskList.taskhours}</td>
            <td style="color: black">${ProjectTaskList.taskdays}</td>
       
            <td style="color: black">
            <div class="dropdown">
            <button class="dropbtn1">${ProjectTaskList.status}</button>
                <div class="dropdown-content">
                <a href="updateTaskStatus.do?pid=${ProjectTaskList.projectid}&tid=${ProjectTaskList.transid}&status=New">New</a>
                <a href="updateTaskStatus.do?pid=${ProjectTaskList.projectid}&tid=${ProjectTaskList.transid}&status=Progress">Progress</a>
                <a href="updateTaskStatus.do?pid=${ProjectTaskList.projectid}&tid=${ProjectTaskList.transid}&status=Completed">Completed</a> 
                </div>
        </div> 
        </td>
        </tr>
       </c:forEach>
</table>
</div>
    </body>
</html>
