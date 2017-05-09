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
 
    <script type="text/javascript">
      $(function() {
          $('#idDateField').datepicker();
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
<tr><td align="center" colspan="2"><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
   
	   
    </body>
</html>
