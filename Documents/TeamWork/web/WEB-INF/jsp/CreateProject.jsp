<%-- 
    Document   : CreateProject
    Created on : 24 Apr, 2017, 5:28:38 PM
    Author     : Administrator
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
 
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  
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
  
  
       <%@include file="Header.jsp" %>
        <h3>Create New Project</h3>
	   
	   <form:form action="AddProject.do" method="post" commandName="ProjectM">
<table align="left" border="2" width="50%">

<tr><td align="center"><h4>OPID :</td><td><form:input path="opid" /></h4></td></tr>    
<tr><td align="center"><h4>Project Name :</td><td><form:input path="projectname" /></h4></td></tr>  
<tr><td align="center"><h4>Lead :</td><td><form:input path="lead" /></h4></td></tr>
<tr><td align="center"><h4>Engineer :</td><td><form:input path="engineer" /></h4></td></tr>
<tr><td align="center"><h4>Start Date :</td><td><form:input id="datepicker" path="startdate"  /></h4></td></tr>
<tr><td align="center"><h4>End Date :</td><td><form:input  id="datepickers" path="enddate" /></h4></td></tr>
<tr><td align="center"><h4>Template :</td><td><form:select path="templateid" >  
	  <c:forEach items="${AllTemplates}" var="template">     
	  <option value="${template.templateid}">${template.templatename}</option>
	  </c:forEach></td>	  
</form:select>
	  
<tr><td align="center" colspan="2"><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
<center>${Lerror}</center><br>      
	   
    </body>
</html>
