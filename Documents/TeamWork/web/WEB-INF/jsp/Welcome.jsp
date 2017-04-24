<%-- 
    Document   : Welcome
    Created on : 13 Apr, 2017, 8:45:16 PM
    Author     : Administrator
--%>

<%@page import="com.Paladion.teamwork.beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <%@include file="Header.jsp" %>
        <%! UserBean b; String name;%>
        <% b=(UserBean)session.getAttribute("Luser"); 
        name=b.getName().toString(); 
        %>
        <h4 align="center">Welcome <%=name%></h4>
    

