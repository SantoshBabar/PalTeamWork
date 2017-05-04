<%-- 
    Document   : CreateTaskTemplate
    Created on : 24 Apr, 2017, 5:29:06 PM
    Author     : Administrator
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Task Template</title>
    </head>
    <body>
        <%@include file="Header.jsp" %>
    
        
	   <form:form action="CreateTaskTemplate.do" method="post" modelAttribute="TemplateM">
<table align="left" border="2" width="25%">

<tr><td align="center"><h4>Template Name:<form:input path="templatename" /></h4></td></tr>    
<tr><td align="center"><h4>Description:<form:input path="templateDesc" /></h4></td></tr>  

<tr><td align="center"><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
<center>${Lerror}</center><br>     
    </body>
</html>
