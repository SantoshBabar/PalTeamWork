<%-- 
    Document   : Login
    Created on : 12 Apr, 2017, 8:31:39 PM
    Author     : Administrator
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="icon" href="Network-Security.png" type="image/x-icon">
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

.login-card select[type=submit] {
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
   
        <div class="login-card">
	   <div align="left">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Create User</h2></div>

	   <form:form action="CreateUser.do" method="POST" modelAttribute="UserM">
<table>
   
<tr><td align="center"><h4 >User Name:</td>
    <td><form:input placeHolder="Enter the Username"  path="username" /></h4></td></tr>    
<tr><td align="center"><h4 >Email id</td>
    <td><form:input placeHolder="Enter the Email ID" path="email" /></h4></td></tr>  
<tr><td align="center"><h4>Mobile</td>
    <td><form:input placeHolder="Enter the Mobile Number" path="phone" /></h4></td></tr>

<!--sumukh csrf  -->
<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
<!--sumukh csrf  -->
<tr><td align="center"><h4 >Team</td>
    <td><form:select path="team">
         <form:option class="login login-submit" value="">Select</form:option>
	 <form:option class="login login-submit" value="codereview">Code Review</form:option>
	 <form:option class="login login-submit" value="appsec">App Sec</form:option>
         <form:option class="login login-submit" value="netpt">Network PT</form:option>
    </form:select></td></tr>
      
<tr><td align="center"><h4 >Password</td>
    <td><form:input type="password" placeHolder="Enter the password" path="password" /></h4></td></tr>
<tr><td align="center"><h4 >Enter the Role</td>
    <td>
   <form:select path="role">
            <form:option class="login login-submit" value="">Select</form:option>
	    <form:option class="login login-submit" value="admin">Admin</form:option>
	    <form:option class="login login-submit" value="manager">Manager</form:option>
            <form:option class="login login-submit" value="lead">Lead</form:option>
            <form:option class="login login-submit" value="engineer">Engineer</form:option>   
             <form:option class="login login-submit" value="scheduling">Scheduling</form:option>
   </form:select></h4></td></tr>

<input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
<tr><td align="center"><input type="submit" value="Create" class="login login-submit"/></td></tr>


    
</table>
</form:form>
     
<center>${Lerror}</center>      
    </body>
</html>
