<%-- 
    Document   : CreateProject
    Created on : 24 Apr, 2017, 5:28:38 PM
    Author     : Administrator
--%>

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
        <h1>Create New Project</h1>
	 <form:form action="CreateProject.do" method="post" modelAttribute="ProjectM">
<table align="left" border="2" width="50%">

<tr><td align="center"><h4>OPID :</td><td><form:input path="opid" /></h4></td></tr>    
<tr><td align="center"><h4>Project Name :</td><td><form:input path="projectname" /></h4></td></tr>  
<tr><td align="center"><h4>Lead :</td><td><form:input path="lead" /></h4></td></tr>
<tr><td align="center"><h4>Engineer :</td><td><form:input path="engineer" /></h4></td></tr>
<tr><td align="center"><h4>Start Date:</td><td><form:input path="startdate" /></h4></td></tr>
<tr><td align="center"><h4>End Date:</td><td><form:input path="enddate" /></h4></td></tr>
<tr><td align="center"><h4>Template :</td><td><form:input path="templateid" /></h4></td></tr>


<tr><td align="center"><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>           
</table>
</form:form>
<center>${Lerror}</center><br>      
	   
    </body>
</html>
