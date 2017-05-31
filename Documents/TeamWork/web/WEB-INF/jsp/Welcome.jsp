    <%@page import="com.Paladion.teamwork.beans.LoginBean"%>
<%-- 
    Document   : Welcome
    Created on : 13 Apr, 2017, 8:45:16 PM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.Paladion.teamwork.beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.Paladion.teamwork.beans.ProjectBean"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

.login-card {
  padding: 40px;
  width: 274px;
  background-color: #F7F7F7;
  margin: 0 auto 10px;
  border-radius: 2px;
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
  background-color: #e60000;
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



  
        <%! LoginBean b; String name;%>
        <% b=(LoginBean)session.getAttribute("Luser"); 
        name=b.userinfo.getName().toString(); 
        %>
<div align="left">
	   <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
 
</table>  
<h1 align="center" style="color: #e60000; " >Welcome </h1><h2 align="center" style="color: #e60000;"><%=name%></h2>
	   
	   <div> <font color="red"><b><center>${TaskSuccess}</center><br>
	 
	   <center>${TemplateSuccess}</center><br></b></font>
	   	   
	   </div> 
	   
        
	   	   
	 
	   <div align="center">   
	   <table>
        
	   <tr align="center"><td><a href="CreateProject.do" style="text-decoration:none"> <input type="button" value="Start New Project" class="login login-submit"/> </a></td>
           <td><a href="showAllProject.do" style="text-decoration:none"> <input type="button" value="View Projects" class="login login-submit"/> </a></td>
	   <td><a href="CreateTask.do" style="text-decoration:none"> <input type="button" value="Create New Task" class="login login-submit"/> </a></td>
	   <td><a href="CreateUser.do" style="text-decoration:none"> <input type="button" value="Create New user" class="login login-submit"/> </a></td>
	    	    
	   <td><a href="CreateTaskTemplate.do" style="text-decoration:none"> <input type="button" value="Create New Task Template" class="login login-submit"> </a></td>
    </table>
	   </div>
	   
        
