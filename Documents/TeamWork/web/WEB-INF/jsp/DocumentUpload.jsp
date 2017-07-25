<%-- 
    Document   : Document Upload
    Created on : 25 Jul, 2017, 6:56:20 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Product Form</title>
</head>
<body>
    <div id="global">
        <form:form commandName="filebean" action="uploadfiles.do?AntiCSRFToken=${csrfPreventionSalt}" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Upload Project Documents</legend>
                <p>
                    <label for="name">Document Name: </label>
                    <form:input id="name" path="name" cssErrorClass="error" />
                    <form:errors path="name" cssClass="error" />
                </p>
                <p>
                    <label for="description">Description: </label>
                    <form:input id="description" path="description" />
                </p>
                <p>
                    <label for="upload files">Project Documents: </label> 
                    <input type="file" name="files" multiple="multiple"/>
                </p>
                <p id="buttons">
                    <input id="reset" type="reset" tabindex="4"> 
                    <input type="hidden" name="AntiCSRFToken" value="${csrfPreventionSalt}"/> 
                    <input id="submit" type="submit" tabindex="5" value="Upload Documents">
                </p>
            </fieldset>
        </form:form>
    </div>
</body>
</html>