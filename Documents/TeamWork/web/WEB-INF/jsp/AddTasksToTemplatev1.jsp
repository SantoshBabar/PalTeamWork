<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="listcontrol.js"></script>
               <script>
<style> 
table {
    border-collapse: collapse;
    width: 100%;
    float:left;
    max-height: 100px;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #F7F7F7}

th {
    background-color: #ff3333;
    color: white;
}
        </style>
        
        <script>
$(document).ready(function() {
    $('#example').DataTable( {
        "scrollY":"200px",
        "scrollCollapse": true,
        "paging":         false
    } );
} );
</script>
        </script>
        </head>
	<body>
   
        <%! TemplateBean TempB;
            String TempName;%>
        <% TempB = (TemplateBean) session.getAttribute("Template");
            TempName = TempB.getTemplatename().toString();
        %>
        <h2>Move Items From One List to Another</h2>
        <table id="example" border="2">
            <tr><td width="50%">
        <select id="sbOne" multiple="multiple" style="width: 400px;" >
	<Option value = "select">SELECT</Option>
	<c:forEach items="${AllTasks}" var="task">
	<option value="${task.taskid}">${task.taskname}</option>
	</c:forEach>
	</select>
                </td>
      
            </tr></table>

        <form:form action="AddTaskTemplate.do" method="post" id="frm">
        
            <table border="2">
                <tr>
                    <td style="vertical-align:top;overflow:scroll;max-height: 400px">
                        <select id="sbTwo" multiple="multiple" name="task" style="width:400px">

                        </select>
                    </td>

                    <td id="test" style="vertical-align:top;overflow:scroll;max-height: 400px">    
                    </td>
                </tr>   
            </table>
            <br><br>     <br><br>     <br><br>
        <input type="button" id="left" value="<" align="center"/>
        <input type="button" id="right" value=">" align="cener"/>
        <input type="hidden" id="ACRF" name="AntiCSRFToken" value="${csrfPreventionSalt}"/>     
        <input type="submit" id="tt" value="submit" />
        </form:form>
        <br>
        
   
        
<!--        <input type="button" id="leftall" value="<<" />
        <input type="button" id="rightall" value=">>" />-->


        </body>
        </html>