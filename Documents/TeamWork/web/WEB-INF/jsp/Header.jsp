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
 <td width="30%"><img src="PaladionLogo.png"/></td>
 <td width="50%"><h1 style="color: white">Paladion Team work Software</h1></td>
 <td></td>
 <td></td>
 <td width="20%"><a href="Logout.do" style="text-decoration:none"><input type="button" value="logout"/></a></td>
 </tr>
</table>    
<% if (null==session.getAttribute("Luser"))
              {response.sendRedirect("Login.do");
              }
              %>
 </body>
</html>
