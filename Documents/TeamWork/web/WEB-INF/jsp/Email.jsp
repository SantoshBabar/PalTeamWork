<%-- 
    Document   : Login
    Created on : 12 Apr, 2017, 8:31:39 PM
    Author     : Administrator
--%>

<html> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  width: 430px;
  height: 500px;
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
</head>
    <body>
    <div align="left">
    <img width="230px" height="70px" src="PaladionLogo.png"/>
</div>
<div align="right"><a href="Logout.do" style="text-decoration:none"><input class="login login-submit" type="button" value="logout"/></a></div>
 
         
<div> <font color="red"><b><center>${success}</center><br><br></b></font></div>


<div class="login-card">
<div align="center">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">Create email template</h2><br></div>
<form:form action="createEmailTemp.do" method="POST" modelAttribute="EmailBean">
<table>

<tr ><td align="center"><h4 >Template Name:</td><td><form:input placeHolder="Enter the template name"  path="emailTemplateName" /></h4></td></tr>    
<tr><td align="center"><h4 >Email Subject:</td><td><form:input placeHolder="Enter the email subject" path="emailTemplateSubject" /></h4></td></tr>  
<tr><td align="center"><h4>Email Body</td><td><form:textarea placeHolder="Enter the email message" path="emailTemplateMessage" /></h4></td></tr>
<tr><td align="center" colspan="2"><input type="submit" value="Create email template" class="login login-submit"/></td></tr>            
</table>
</form:form>
<div>
<form:form action="sendMail.do" method="POST">
<table>
<tr><td align="center" colspan="2"><input type="submit" value="Send Mail" class="login login-submit"/></td></tr>            
</table>
</form:form>

</div>
</div>


<hr>

<h2 bgcolor="yellow">Show Email Template</h2>
<table width="50%" bgcolor="yellow">
	<tr>
		<th>Email TemplateID</th>
		<th>Email Template Name</th>
		<th>Email Template Subject</th>
		<th>Email Template Message</th>
		<th>Actions</th>
	</tr>
	<c:forEach items="${emailList}" var="eList">
		<tr>
			<td>${eList.emailTemplateId}</td>
			<td>${eList.emailTemplateName}</td>
			<td>${eList.emailTemplateSubject}</td>
			<td>${eList.emailTemplateMessage}</td>
			<td><a href="<c:url value='/EmailUpdateTemp/${eList.emailTemplateId}.do' />" >Update</a></td>
			<td><a href="<c:url value='/deleteEmailTemp/${eList.emailTemplateId}.do' />" >Delete</a></td>
			
			
		</tr>
	</c:forEach>
</table>	
<br/>


</body>
</html>
