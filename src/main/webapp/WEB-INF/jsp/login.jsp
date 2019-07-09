<%-- 
    Document   : index
    Created on : Jul 8, 2019, 4:55:18 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in Page</title>
    </head>
    <body>
        <p>Please log in</p>
           <springForm:form method="post" modelAttribute="user" action ="${pageContext.request.contextPath}/index/dologin">
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
            
            <a href="${pageContext.request.contextPath}/register/createaccount">REGISTER</a>
    </body>
</html>
