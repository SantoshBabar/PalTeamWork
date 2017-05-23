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
     
     <table border="2" width="80%" align="center">
            
<!--            <tr>
                <th width="15%" style="color: whitesmoke">PID </th>
                <th width="15%" style="color: whitesmoke">OPID </th>
                <th width="20%" style="color: whitesmoke">Project Title</th>
                <th width="15%" style="color: whitesmoke">Engineer</th>
                <th width="15%" style="color: whitesmoke">Lead</th>
                <th width="10%" style="color: whitesmoke">Start Date</th>
                <th width="10%" style="color: whitesmoke">End Date</th>
                <th width="10%" style="color: whitesmoke">Man Days</th></tr>
            </tr>-->

<c:forEach  items="${WeightData}" var="MTTP">     
    

            <tr> 
                <td style="color: black">${MTTP.taskname}</td>
                <td style="color: black"> ${MTTP.taskstartdate}</td>
	        <td style="color: black">${MTTP.taskenddate}</td>
                	        <td style="color: black">${MTTP.taskdays}</td>

	   </tr>
           
</c:forEach>
          
        </table>
    </body>
</html>
