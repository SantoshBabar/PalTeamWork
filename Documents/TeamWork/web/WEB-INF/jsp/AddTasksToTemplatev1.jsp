<%@page import="com.Paladion.teamwork.beans.TemplateBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<!DOCTYPE html>
	<html lang="en">
	<head>
<script src="http://prog.linkstraffic.net/jquery/jquery-2.1.1.js"></script>

<script type="text/javascript">
var addedrows = new Array();
$(document).ready(function() {
    $( "#sourcetable tbody tr" ).on( "click", function( event ) {
  
    var ok = 0;
    var theid = $( this ).attr('id').replace("sour","");	
	var newaddedrows = new Array();
	
    for	(index = 0; index < addedrows.length; ++index) {
		// if already selected then remove
		if (addedrows[index] == theid) {
			   
			$( this ).css( "background-color", "#ffccff" );
			
			// remove from second table :
			var tr = $( "#dest" + theid );
            tr.css("background-color","#FF3700");
            tr.fadeOut(400, function(){
                tr.remove();
            });
			
	        //addedrows.splice(theid, 1);	
    		
			//the boolean
			ok = 1;
		} else {
		
		    newaddedrows.push(addedrows[index]);
		} 
    }   
    
	addedrows = newaddedrows;
	
	// if no match found then add the row :
	if (!ok) {
		// retrieve the id of the element to match the id of the new row :
		
		
		addedrows.push( theid);
		
		$( this ).css( "background-color", "#cacaca" );
				
     	$('#destinationtable tr:last').after('<tr id="dest' + theid + '"><td>' 
		                               + $(this).find("td").eq(0).html() + '</td><td>' 
		                               + $(this).find("td").eq(1).html() + '</td><td>' 
		                               + $(this).find("td").eq(2).html() + '</td><td>' 
		                               
		                               + '</td></tr>');		  
		
	}
	
    });
});		
</script>	
               
<style> 
    ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #a6a6a6;
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
    background-color: #cc0000;
}
table {
        border     : 1px solid gray;
    width      : 60%;
    text-align : center;
}
 
table#sourcetable tbody tr {
    background-color : #a6a6a6;
}
 
table#sourcetable tbody  tr {
    cursor : pointer;
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
    background-color: #a6a6a6;
}
.active {
    background-color: #a6a6a6;
}
</style>
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
        <h3>The source table</h3>
 
<table id="sourcetable">
    <thead>
        <tr>
            <th>ID</th>
            <th>Task</th>
            <th>Weight</th>
                                  
        </tr>
    </thead>
 
    <tbody>
        <tr id="sour0">
            <td>1</td>
            <td>Name 1</td>
            <td><input type="text"></td>
        </tr>
        <tr id="sour1">
            <td>2</td>
            <td>Name 2</td>
            <td><input type="text"></td>
        </tr>
        <tr id="sour2">
            <td>3</td>
            <td>Name 3</td>
            <td><input type="text"></td>
        </tr>
        <tr id="sour3">
            <td>4</td>
            <td>Name 4</td>
            <td><input type="text"></td>
        </tr>
        <tr id="sour4">
            <td>5</td>
            <td>Name 5</td>
            <td><input type="text"></td>
        </tr>     
    </tbody>
</table>
 
 
<h3>The second table :</h3>
 
<form method="POST" action="">
<table id="destinationtable">
    <thead>
        <tr>
            <th>ID</th>
            <th>Task</th>
            <th>Weight</th>                   
        </tr>
    </thead>
 
     
     
</table>
    <br>
    <br>
<input type="submit" value="submit"/> 
</form>   


</body>
</html>