<%-- 
    Document   : DisplayProjectProgress
    Created on : 14 May, 2017, 9:07:43 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Individual Project Status</title>
    </head>
    <body>
         <%@include file="Header.jsp" %>
     
     
    



<h2>Project Name: ${ProjectData.projectname}</h2>

<h3>OPID: ${ProjectData.opid}</h3>
<h3>Lead Assigned: ${ProjectData.lead}</h3>
<h3>Engineer Assigned: ${ProjectData.engineer}</h3>
<h3>Start Date: ${ProjectData.startdate}</h3>

<tr><th>Task Name </th><th>Task Start Date</th><th>Start End Date</th><th>Hours</th></tr>
<c:forEach  items="${WeightData}" var="MTTP">     
    

            <tr> 
                <td style="color: black">${MTTP.taskname}</td>
                <td style="color: black"> ${MTTP.taskstartdate}</td>
	        <td style="color: black">${MTTP.taskenddate}</td>
                	        <td style="color: black">${MTTP.taskdays*9}</td>

	   </tr>
           
</c:forEach>
          
        
    </body>
</html>
