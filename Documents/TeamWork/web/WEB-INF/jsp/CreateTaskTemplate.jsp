<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="icon" href="Network-Security.png" type="image/x-icon">
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
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #ff6666;
    width:1500px;
   
}
li {
    float: left;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
li a:hover:not(.active) {
    background-color: #b30000;
}
.active {
    background-color: #ff1a1a;
}
</style>
<style>
@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);
body {
color:#6a6f8c;
background:#c8c8c8;
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
  background-color: #F7F7F7;
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
  height: 44px;
  font-size: 16px;
  width: auto;
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
  width: 20%;
  border: 0px;
  color: #fff;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #ff3333;
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
.login-help {
  width: 100%;
  text-align: center;
  font-size: 12px;
}
table.dataTable.select tbody tr,
table.dataTable thead th:first-child {
  cursor: pointer;
}
  .black_overlay {
  display: none;
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  background-color: black;
  z-index: 1001;
  -moz-opacity: 0.8;
  opacity: .80;
  filter: alpha(opacity=80);
}
.white_content {
  display: none;
  position: absolute;
  top: 25%;
  left: 25%;
  width: 50%;
  height: 50%;
  padding: 16px;
  border: 16px solid orange;
  background-color: white;
  z-index: 1002;
  overflow: auto;
}      
</style>        
        </head>
<body>
        <%@include file="Header.jsp" %>
        
        
        <div class="login-card">
        
        <h2>Select tasks to the template</h2>
<h3>Selected Tasks. Assign weights to selected task</h3>
 
<form:form name="selection" action="CreateTaskTemplate.do" method="post" modelAttribute="TemplateM" onSubmit="return selectAll()"> 
Template Name:<form:input placeholder="Enter template name" name="t1"  path="templatename" />   
Description:<form:input placeholder="Enter description" name="t2"  path="templateDesc" />
    <select multiple size="10" id="from">
        <c:forEach  items="${AllTasks}" var="task">
            <option value="${task.taskid}"> ${task.taskname}</option>
      
       </c:forEach>
    </select>
    <div class="controls"> 
        <a href="javascript:moveAll('from', 'to')">&gt;&gt;</a> 
        <a href="javascript:moveSelected('from', 'to')">&gt;</a> 
        <a href="javascript:moveSelected('to', 'from')">&lt;</a> 
        <a href="javascript:moveAll('to', 'from')" href="#">&lt;&lt;</a> </div>
    <select multiple id="to" size="10" name="taskid"></select>
    <input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
    
    <br>
    <input type="submit" value="submit">
    </form:form>    
        


</body>
</html>
