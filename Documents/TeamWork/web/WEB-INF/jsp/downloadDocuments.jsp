<%-- 
    Document   : Downloadfiles
    Created on : 29-Aug-2017, 14:24:10
    Author     : sumukh.r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
       <form  action="Downloadfiles.do?AntiCSRFToken=${csrfPreventionSalt}" method="post" enctype="multipart/form-data">
            
                
               
                    
                    <input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
                    
               
                       <input class="login login-submit" id="submit" type="submit" value="download Documents" >
                      
              
        </form> 
    </body>
</html>
