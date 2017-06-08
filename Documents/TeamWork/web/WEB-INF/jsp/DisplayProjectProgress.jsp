<%-- 
    Document   : DisplayProjectProgress
    Created on : 14 May, 2017, 9:07:43 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  width: Auto;
  height: Auto;
  background-color: #F7F7F7;
  margin: 0 auto 10px;
  border-radius: 20px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}

.login-card h1  {
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
    overflow:scroll;
    color: red;
}

tr,th {
    text-align:center;
}

.right {
    position: absolute;
    right: 10px;
    width: 200px;
    border: 2px solid ;
    padding: 10px;
}
.left {
    
    right: 0px;
    width: 250px;
    border: 2px solid;
    padding: 10px;
}
</style>
</head>
    <body>
    <div align="left">
    <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
    <br>
        <div class="login-card">
	   <div align="center">  <h3 style="color: #ff3333; font-family: sans-serif; font-style: normal">Project Details</h3></div>
	   
	   <div class="right">
	   <tr><h4 style="color: red; font-size: 15px">Project Name: ${ProjectData.projectname}</h2></tr>
	   <tr><h4 style="color: red; font-size: 15px">OPID: ${ProjectData.opid}</h4></h3></tr>
	   
	   </div>
	   <div class="left">
	   <tr><h4 style="color: red; font-size: 15px">Lead Assigned: ${ProjectData.lead}</h4></tr>
             
              </tr><h4 style="color: red; font-size: 15px" >Start Date: ${ProjectData.startdate}</h4></tr>
	   </div><br>
<div style="overflow: auto;height: 200px; width: auto;">
<table border="1" align="center">
    <tr><th>Task Name </th><th>Task Start Date</th><th>Start End Date</th><th>Hours</th><th>Status</th></tr>
<c:forEach  items="${TaskDetails}" var="Task">     
            <tr> 
                <td style="color: black">${Task.taskname}</td>
                <td style="color: black">${Task.taskstartdate}</td>
	        <td style="color: black">${Task.taskenddate}</td>
                <td style="color: black">${Task.taskhours}</td>
                <td style="color: black">${Task.status}</td>
            </tr>
           
</c:forEach>
</table>
</div>
    </body>
</html>
