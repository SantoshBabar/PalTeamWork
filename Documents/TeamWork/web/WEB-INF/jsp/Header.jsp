<%-- 
    Document   : Header.jsp
    Created on : Jun 20, 2017, 2:58:56 PM
    Author     : santosh.babar
--%>
<%@page import="com.Paladion.teamwork.beans.UserDataBean"%>
<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
   <style>
.container {
    overflow: hidden;
    background-color: #333;
    font-family: Arial;
}

.container a {
    float: left;
    font-size: 16px;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

.dropdown {
    float: left;
    overflow: hidden;
}

.dropdown .dropbtn {
    font-size: 16px;    
    border: none;
    outline: none;
    color: white;
    padding: 14px 16px;
    background-color: inherit;
}

.dropdown .dropbtn1 {
    font-size: 16px;    
    border: none;
    outline: none;
    color: white;
    padding: 14px 16px;
    background-color: red;
}

.container a:hover, .dropdown:hover .dropbtn {
    background-color: red;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    float: none;
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #ddd;
}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>
</head>
    
<body>
    <%-- Session Validation Code --%>
    <% 
        if (null==session.getAttribute("Luser"))
           {
               response.sendRedirect("Login.do");
           }
    %> <%-- Session Code Ends --%>
    
     
    <%-- Fetch the UserName from the Session --%>
    <%! UserDataBean b; String name;%>
    <% 
        b=(UserDataBean)session.getAttribute("Luser"); 
        name=b.getUsername().toString();
    %>
    
    <%-- Header Code Begins --%> 
    <br><br>   
    <div align="center">
    <ul>
        <li><a href="Welcome.do">Home</a></li>
        
        <div class="dropdown">
            <button class="dropbtn">Projects</button>
                <div class="dropdown-content">
                <a href="CreateProject.do">Schedule Project</a>
                <a href="showAllProject.do">View All Projects</a>
                </div>
        </div> 
        
          <div class="dropdown">
            <button class="dropbtn">Users</button>
                <div class="dropdown-content">
                <a href="CreateUser.do">Create User</a>
                <a href="ViewAllUser.do">View Users</a>
                </div>
        </div> 
        
          <div class="dropdown">
            <button class="dropbtn">Tasks</button>
                <div class="dropdown-content">
                <a href="CreateTask.do">Create Task</a>
                <a href="#">View Tasks</a>
                </div>
        </div> 
        
          <div class="dropdown">
            <button class="dropbtn">Project Template</button>
                <div class="dropdown-content">
                <a href="CreateTaskTemplate.do">Create Project Template</a>
                <a href="GetAllTaskTemplates.do">View Project Templates</a>
                </div>
        </div> 
         
        <li style="float:right"><a class="active" href="Logout.do">Logout</a></li>
        <li style="float:right"><a class="active"><%=name%></a></li>
    </ul>
    </div>
         
    <br>  

    <div> <font color="red"><b><center>${Message}</center><br></font> </div>
    <%-- Header Code Ends --%>  
    </body>
</html>
