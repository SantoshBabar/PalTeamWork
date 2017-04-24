<%-- 
    Document   : Header
    Created on : 24 Apr, 2017, 11:54:55 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teamwork</title>
    </head>

<body>
<table>     
 <tr>
 <td width="40%"><img src="PaladionLogo.png"/></td>
 <td width="60%"><h1>Paladion Team work Software</h1></td>
 <td width="20%"><a href="Logout.do" style="text-decoration:none"><input type="button" value="Sign Out"/></a></td>
 </tr>
</table>    
<% if (null==session.getAttribute("Luser"))
              {response.sendRedirect("Login.do");
              }
              %>
 </body>
</html>
