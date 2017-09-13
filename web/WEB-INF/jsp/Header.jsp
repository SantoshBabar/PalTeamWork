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
<link rel="icon" href="Network-Security.png" type="image/x-icon">
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
    background-color: #cc0000;
}

.container a:hover, .dropdown:hover .dropbtn {
    background-color: #cc0000;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #999966;
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
        if (session==null||null==session.getAttribute("Luser"))
           {
               response.sendRedirect("Login.do");
               return;
           }
    %> <%-- Session Code Ends --%>
    
     
    <%-- Fetch the UserName from the Session --%>
    <%! UserDataBean b; String name; String role;%>
    <% 
        b=(UserDataBean)session.getAttribute("Luser"); 
        name=b.getUsername().toString();
        role=b.getRole().toString();
    %>
    
    <%-- Header Code Begins --%> 
    <br><br>   
    <div align="center">
    <ul>
        <li><a href="Welcome.do">Home</a></li>
        
        <div class="dropdown">
            <button class="dropbtn">Projects</button>
                <div class="dropdown-content">
                    <% if(role.equalsIgnoreCase("Manager")||role.equalsIgnoreCase("scheduling"))
                    {%>
                    <a href="CreateProject.do">Schedule Project</a>
                    <%}%>
                    <a href="showAllProject.do">View All Projects</a>
                </div>
        </div> 
        
        <% if(role.equalsIgnoreCase("Manager")||role.equalsIgnoreCase("Admin"))
        {%>
        <div class="dropdown">
            <button class="dropbtn">Administration</button>
                <div class="dropdown-content">
                <a href="CreateUser.do">Create User</a>
                <a href="ViewAllUser.do">View Users</a>
                <a href="Administration.do">System Properties</a>
                </div>
        </div> 
        <%}%>
        
        <% if(role.equalsIgnoreCase("lead")||role.equalsIgnoreCase("manager"))
        {%>
          <div class="dropdown">
            <button class="dropbtn">Tasks</button>
                <div class="dropdown-content">
                <a href="CreateTask.do">Create Task</a>
                <a href="GetAllTasks.do">View Tasks</a>
                </div>
        </div> 
        <%}%>
        
        
         <% if(role.equalsIgnoreCase("lead")||role.equalsIgnoreCase("manager"))
        {%>
          <div class="dropdown">
            <button class="dropbtn">Project Template</button>
                <div class="dropdown-content">
                <a href="CreateTaskTemplate.do">Create Project Template</a>
                <a href="GetAllTaskTemplates.do">View Project Templates</a>
                </div>
        </div> 
          <%}%>
        <li><a href="index.do">knowledge base</a></li> 
        <li style="float:right"><a class="active" href="Logout.do">Logout</a></li>
       
        <div class="dropdown" style="float:right">
            <button class="dropbtn"><%=name%></button>
                <div class="dropdown-content">
                <a href="ChangePassword.do">Change Password</a>
                </div>
        </div> 
    
    </ul>
    </div>
         
    <br>  

    <div> <font color="red"><b><center>${Message}</center><br></font> </div>
    <%-- Header Code Ends --%>  
    </body>
</html>
