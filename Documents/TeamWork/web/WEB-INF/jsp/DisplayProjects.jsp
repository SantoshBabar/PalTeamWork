<%-- 
    Document   : DisplayProjectStatus
    Created on : May 10, 2017, 6:51:12 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"></script>
<link rel="icon" href="Network-Security.png" type="image/x-icon">
<head>
    
<style>
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
  height: 550px;
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
  width: 20%;
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



table {
    border-collapse: collapse;
    width: 100%;
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
<script>
        function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}
        </script>
<title>All Projects</title>
</head>
    <body>
         

<%@include file="Header.jsp" %>
         

        <div class="login-card">
	   <div align="left">  <h2 style="color: #ff3333; font-family: sans-serif; font-style: normal">All Projects</h2><br></div>
 <table border="1" id="example" class="display" width="100%"  cellspacing="0">
        <thead>
            <tr bgcolor="#ff6666">
                
                <th>OPID </th>
                <th>Project Title</th>
                <th>Lead</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Man Days</th>
                <th>Status</th>
           
            </tr>
        </thead>
            
            <tbody>
            
<c:forEach  items="${AllProjects}" var="project">     
    
<fmt:formatDate value="${project.startdate}" var="SDate" type="date" pattern="dd-MMM-yyyy" />
<fmt:formatDate value="${project.enddate}" var="EDate" type="date" pattern="dd-MMM-yyyy" />
	   
            <tr> 
                
                <td > <a href="showProgress.do?id=${project.projectid}"> ${project.opid}</a></td>
	        <td >${project.projectname}</td>
                <td >${project.lead}</td>
                <td >${SDate}</td>
                <td >${EDate}</td>
                <td >${project.mandays}</td>
                <td >
                    <div class="dropdown">
                    <button class="dropbtn1">${project.status}</button>
                      <div class="dropdown-content">
                        <a href="updateProjectStatus.do?pid=${project.projectid}&status=New">New</a>
                        <a href="updateProjectStatus.do?pid=${project.projectid}&status=Progress">Progress</a>
                        <a href="updateProjectStatus.do?pid=${project.projectid}&status=Completed">Completed</a> 
                      </div>
                    </div> 
                </td>
	   </tr>
           
</c:forEach>
            </tbody>
          
        </table>
     <button class="login login-submit" id="btnExport" onclick="fnExcelReport();"> EXPORT TO EXCEL </button>
 </div>
           
	   </div>
         <iframe id="txtArea1" style="display:none"></iframe>
     
</body>
</html>
