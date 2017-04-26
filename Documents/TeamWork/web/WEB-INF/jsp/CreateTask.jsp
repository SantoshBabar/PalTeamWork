<%-- 
    Document   : Login
    Created on : 12 Apr, 2017, 8:31:39 PM
    Author     : Administrator
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1 align="center">Create Task</h1>
	   <form:form action="CreateTask.do" method="post" modelAttribute="TaskM">
<table align="center" border="2" width="25%">

<tr><td align="center"><h2>Task Name:<form:input path="taskname" /></h2></td></tr>    
<tr><td align="center"><h2>Description:<form:input path="Description" /></h2></td></tr>  
<tr><td align="center"><input type="submit" value="Login" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
<center>${Lerror}</center><br>      
    </body>
</html>
