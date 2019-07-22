<%-- 
    Document   : userprofile
    Created on : 15 Ιουλ 2019, 8:41:21 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
    </head>
    <body>
        <c:if test="${loggedinuser.userid eq null}">
            <%@include file="errorpage.jsp"%>
            </c:if>
        <c:if test="${loggedinuser.userid ne null}">
           
            
        <%@ include file="navbar.jsp" %>
        </br>
        <springForm:form method="post" modelAttribute="myprofile" action ="${pageContext.request.contextPath}/myprofile/addmydata" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>First name</td>
                    <td><springForm:input path="firstname"/></td> 
                    <td><springForm:errors path="firstname"/></td> 
                </tr>
                <tr>
                    <td>Last name</td>
                    <td><springForm:input path="lastname"/></td> 
                    <td><springForm:errors path="lastname"/></td> 
                </tr>
                <tr>
                    <td>Date Of Birth</td>
                    <td><springForm:input type="date" path="dateofbirthday"/></td> 
                    <td><springForm:errors path="dateofbirthday"/></td> 
                </tr>
                <tr>
                    <input class="inputfile" id ="upload" type="file" name="myfile" accept=".png, .jpg" data-max-size="2048">
                    <label for="upload">Choose file to upload</label>
                </tr>
                <input type="submit">
            </springForm:form>
                </c:if>
            </body>
            </html>
