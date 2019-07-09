<%-- 
    Document   : register
    Created on : Jul 8, 2019, 6:48:29 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <p>Please register</p>
        <springForm:form method="post" modelAttribute="user" action ="${pageContext.request.contextPath}/register/docreateaccount">
            <table>
                <tr>
                    <td>Username</td>
                    <td><springForm:input path="username"/></td> 
                    <td><springForm:errors path="username"/></td> 
                </tr>
                <tr>
                    <td>Password</td>
                    <td><springForm:input path="password"/></td> 
                    <td><springForm:errors path="password"/></td> 
                </tr>
            </table>
            <input type="submit">
        </springForm:form>
    </body>
</html>
