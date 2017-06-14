<%-- 
    Document   : CreateProject
    Created on : 24 Apr, 2017, 5:28:38 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
  <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<head>
<script type="text/javascript">
  $(document).ready(function() {
    $("#date").datepicker();
  });
  </script>
  <script type="text/javascript">
  $(document).ready(function() {
    $("#datepicker").datepicker();
  });
  </script>
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
    width: 100%;
    color: #ff0000;
    border-color: white;
    align-items: center;
}

th {
    text-align: center;
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
        <center>${Projectresp}</center><br> 
	   <div class="login-card">
	   <div align="left">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Create New Project</h2><br></div>
<form:form action="AddProject.do" method="post" commandName="ProjectM">
<div align="center">
    <table  align="center" border="0">

<tr><td align="right"><h4>OPID :</td><td><form:input placeholder="Enter OPID" path="opid" /></h4></td></tr>    
<tr><td align="right"><h4>Project Name :</td><td><form:input placeholder="Enter Project Name" path="projectname" /></h4></td></tr>  
<tr><td align="right"><h4>Lead :</td>
    <td><form:select path="lead">
	           <c:forEach  items="${AllLeads}" var="lead"> 
	           <form:option class="login login-submit" value="${lead.userinfo.name}">${lead.userinfo.name}</form:option>
	           </c:forEach></form:select>
    </td>
</tr>
<tr><td align="right"><h4>Start Date :</td><td><form:input placeholder="Enter Start Date" id="date" path="startdate" /></h4></td></tr>
<tr><td align="right"><h4>End Date :</td><td><form:input placeholder="Enter Project Name" id="datepicker" path="enddate"/></h4></td></tr>

<tr><td align="right"><h4 >Template :</td>
    <td ><form:select  path="templateid">  
	  <c:forEach items="${AllTemplates}" var="template">     
	  <option class="login login-submit" value="${template.templateid}">${template.templatename}</option>
	  </c:forEach></td>	  
</form:select>
	  
<tr><td align="center"><input type="submit" value="Create" class="login login-submit"/></td></tr>           
</table >
</div>
</form:form>
	   </div>
     
	   <script>
  $(document).ready(function() {
    $("#datepicker").datepicker();
  });
  </script>
  <script>
  $(document).ready(function() {
    $("#datepickers").datepicker();
  });
  </script>
    </body>
</html>
