<%-- 
    Document   : CreateProject
    Created on : 24 Apr, 2017, 5:28:38 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
  width: 430px;
  height: 550px;
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


</style>

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
    </head>
    <body>
    <div align="left">
    <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
 

        <center>${Projectresp}</center><br> 
	   <div class="login-card">
	   <div align="center">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Create New Project</h2><br></div>
<form:form action="AddProject.do" method="post" commandName="ProjectM">
<div align="center">
<table >

<tr><td align="center"><h4>OPID :</td><td><form:input placeholder="Enter OPID" path="opid" /></h4></td></tr>    
<tr><td align="center"><h4>Project Name :</td><td><form:input placeholder="Enter Project Name" path="projectname" /></h4></td></tr>  
<tr><td align="center"><h4>Lead :</td><td><form:input placeholder="Enter Lead Name" path="lead" /></h4></td></tr>
<tr><td align="center"><h4>Engineer :</td><td><form:input placeholder="Enter Engineer Name" path="engineer" /></h4></td></tr>
<tr><td align="center"><h4>Start Date :</td><td><form:input placeholder="Enter Start Date" id="datepicker" path="startdate" value=""/></h4></td></tr>
<tr><td align="center"><h4>End Date :</td><td><form:input placeholder="Enter Project Name"  id="datepickers" path="enddate" value=""/></h4></td></tr>
<tr><td align="center"><h4 >Template :</td><td ><form:select  path="templateid">  
	  <c:forEach items="${AllTemplates}" var="template">     
	  <<option class="login login-submit" value="${template.templateid}">${template.templatename}</option>
	  </c:forEach></td>	  
</form:select>
	  
<tr><td align="center" colspan="2"><input type="submit" value="Create" class="login login-submit"/></td></tr>           
</table>
</div>
</form:form>
	   </div>
     
	   
    </body>
</html>
