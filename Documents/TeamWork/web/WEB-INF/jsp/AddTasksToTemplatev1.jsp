<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
<script src="http://prog.linkstraffic.net/jquery/jquery-2.1.1.js"></script>

<style type="text/css">
    select {
        width: 200px;
        float: left;
    }
    .controls {
        width: 40px;
        float: left;
        margin: 10px;
    }
    .controls a {
        background-color: #222222;
        border-radius: 4px;
        border: 2px solid #000;
        color: #ffffff;
        padding: 2px;
        font-size: 14px;
        text-decoration: none;
        display: inline-block;
        text-align: center;
        margin: 5px;
        width: 20px;
    }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js">
    </script>
    <script>
    function moveAll(from, to) {
        $('#'+from+' option').remove().appendTo('#'+to); 
    }
    
    function moveSelected(from, to) {
        $('#'+from+' option:selected').remove().appendTo('#'+to); 
    }
    function selectAll() {
        $("select option").attr("selected","selected");
    }
    </script>
       
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);
body {
	background-image: url("grey.jpg");
  background-repeat: repeat-y;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}
.login-card {
  padding: 40px;
  width: 1420px;
  height: auto;
   background-color: white;
  margin: 0 auto 10px;
  border-radius: 2px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}
.login-card h1 {
  font-weight: 1;
  text-align: center;
  font-size: 2.3em;
}
.login-card input[type=submit] {
  width: 10%;
  display: block;
  margin-bottom: 10px;
  position: relative;
  float: center;
}
.login-card input[type=text], input[type=password] {
  height: 25px;
  font-size: 16px;
  width: 65px;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}
.login-card input[type=text]:hover, input[type=password]:hover {
  border: 2px solid #b9b9b9;
  
  border-top: 5px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}
.login {
  text-align: center;
  font-size: 14px;
  font-family: 'Arial', sans-serif;
  font-weight: 700;
  height: 36px;
  padding: 0 8px;
/* border-radius: 3px; */
/* -webkit-user-select: none;
  user-select: none; */
}
.login-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #ff8080;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}
.login-submit {
  /* border: 1px solid #3079ed; */
  width: 5%;
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #a6a6a6;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}
.login-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #ff8080;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}
.login-card a {
  text-decoration: none;
  color: #666;
  font-weight: 400;
  text-align: center;
  display: inline-block;
  opacity: 0.6;
  transition: opacity ease 0.5s;
}
.login-card a:hover {
  opacity: 1;
}
.submit {
  /* border: 1px solid #3079ed; */
  width: 150px;
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #c0c0c0;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}
.submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #c0c0c0;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}
.login-help {
  width: 100%;
  text-align: center;
  font-size: 12px;
}
     
</style>          
        </head>
	<body>
        <%@include file="Header.jsp" %>
        <%! TemplateBean TempB;
            String TempName;%>
        <% TempB = (TemplateBean) session.getAttribute("Template");
            TempName = TempB.getTemplatename().toString();
        %>
        
        <div class="login-card">
        
        <h2>Select tasks to the template</h2>
<<<<<<< HEAD
        <h3>Task List. Click on the task to select</h3>
 

 
 
<h3>Selected Tasks. Assign weights to selected task</h3>
 
<form name="selection" method="post" onSubmit="return selectAll()"> 
    <select multiple size="10" id="from">
        <c:forEach  items="${AllTasks}" var="task">
            <option> ${task.taskname}</option>
      
       </c:forEach>
    </select>
    <div class="controls"> 
        <a href="javascript:moveAll('from', 'to')">&gt;&gt;</a> 
        <a href="javascript:moveSelected('from', 'to')">&gt;</a> 
        <a href="javascript:moveSelected('to', 'from')">&lt;</a> 
        <a href="javascript:moveAll('to', 'from')" href="#">&lt;&lt;</a> </div>
    <select multiple id="to" size="10" name="topics[]"></select>
    <input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
    <br>
    <input type="submit" value="submit">
    <form>    
=======
        <h3>List of all the tasks</h3>
        
  
            <tr><td  style="vertical-align:top;overflow:scroll;max-height: 400px">
        <select id="sbOne" multiple="multiple" style="width: 500px;height:130px" >
	
	<c:forEach items="${AllTasks}" var="task">
	<option value="${task.taskid}" style="height:30px">${task.taskname}</option>
	</c:forEach>
	</select>
                </td>
      
        </tr></table>
              
        <form:form action="AddTaskTemplate.do" method="post" id="frm"><br><br>
            <input class="login login-submit" type="button" id="left" value="<" align="left"/>
        <input class="login login-submit" type="button" id="right" value=">" align="left"/>
           
             <table border="2" id="test">
     
            </table>
     
        <input type="hidden" id="ACRF" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
        <br><br>
        <input class="login login-submit" type="submit" id="tt" value="submit" />
        </form:form>
        <br>
        
        </div>
        
<!--        <input type="button" id="leftall" value="<<" />
        <input type="button" id="rightall" value=">>" />-->
>>>>>>> origin/LatestBranch


</body>
</html>
