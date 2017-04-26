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
    <%@include file="Header.jsp" %>
    
        
	   <form:form action="CreateTask.do" method="post" modelAttribute="TaskM">
<table align="left" border="2" width="25%">

<tr><td align="center"><h4>Task Name:<form:input path="taskname" /></h4></td></tr>    
<tr><td align="center"><h4>Description:<form:input path="Description" /></h4></td></tr>  

<tr><td align="center"><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
<center>${Lerror}</center><br>      
    </body>
</html>
