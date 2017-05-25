<%-- 
    Document   : DisplayProjectStatus
    Created on : May 10, 2017, 6:51:12 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);

body {
 
  background-image: url(http://www.glossa-group.com/fileadmin/background/background11.jpg);
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
  width: 1000px;
  height: auto;
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
  width: 100%;
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
}

th {
    text-align:left;
}

</style>
</head>
    <body>
    <div align="left">
    <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
 
        <div class="login-card">
	   <div align="center">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Create Task</h2><br></div>
 
     <table border="1">
            
            <tr>
                <th width="15%" >PID </th>
                <th width="15%" >OPID </th>
                <th width="20%" Project Title</th>
                <th width="15%" >Engineer</th>
                <th width="15%" >Lead</th>
                <th width="10%" >Start Date</th>
                <th width="10%" >End Date</th>
                <th width="10%" >Man Days</th></tr>
            </tr>
            
<c:forEach  items="${AllProjects}" var="project">     
    
<fmt:formatDate value="${project.startdate}" var="SDate" type="date" pattern="dd-MMM-yyyy" />
<fmt:formatDate value="${project.enddate}" var="EDate" type="date" pattern="dd-MMM-yyyy" />
	   
            <tr> 
                <td > <a href="showProgress.do?id=${project.projectid}">${project.projectid}</a></td>
                <td > ${project.opid}</td>
	        <td style=>${project.projectname}</td>
                <td style=>${project.engineer}</td>
                <td style=>${project.lead}</td>
                <td style=${SDate}</td>
                <td style=>${EDate}</td>
                <td style=>${project.mandays}</td>
	   </tr>
           
</c:forEach>
          
        </table>
	   </div>
</body>
</html>
