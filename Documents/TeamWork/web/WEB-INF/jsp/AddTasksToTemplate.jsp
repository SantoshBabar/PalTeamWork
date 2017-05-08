<%-- 
    Document   : AddTasks
    Created on : May 4, 2017, 8:19:13 AM
    Author     : user
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.Paladion.teamwork.controllers.TaskController"%>
<%@page import="com.Paladion.teamwork.DAO.TaskDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.Paladion.teamwork.beans.TaskBean"%>
<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Tasks To Template</title>
<script>
document.getElementById('checkfield').onchange = function() {
    document.getElementById('textfield').disabled = !this.checked;
};
</script>
</head>

<%@include file="Header.jsp" %>

   <%! TemplateBean TempB; String TempName;%>
        <% TempB=(TemplateBean)session.getAttribute("Template"); 
        TempName=TempB.getTemplatename().toString();
        %>
	   <h2>  Select the Tasks for the <font color="red"><%=TempName%> Template</font></h2> 
	   
	   <h4>List of All the Tasks </h4>   
	   <form:form action="AddTaskTemplate.do" method="post">
	   <table border="1">
	   <tr><th>Task Name </th>  <th> Check/Uncheck</th> <th> Weight(%)</th></tr>
                <c:forEach items="${AllTasks}" var="task">     
	   <tr> <td><c:out value="${task.taskname}"/></td> <td><input type="checkbox" id="checkfield"  name="task" value="${task.taskid}"> </td><td><input type="number" id="textfield" name="${task.taskid}" style="width:150px;"></td></tr>
               </c:forEach>
	   
	   <tr><td><input type="submit" value="Create" style="height:40px; width:330px"/></td></tr>
</table>
	   </form:form>
	   <center>${Temperror}</center><br>
</html>
l