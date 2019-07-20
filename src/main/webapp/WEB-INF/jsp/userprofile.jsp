<%-- 
    Document   : userprofile
    Created on : 19 Ιουλ 2019, 3:40:10 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <title>JSP Page</title>
    </head>
    <body>

        <c:if test="${myprofile.profileimagebase64 ne null}">
            <img src="data:image/png;base64, ${myprofile.profileimagebase64}"/>
        </c:if>
        <c:if test="${myprofile.profileimagebase64 eq null}">
            <img src="images/man-user.png"/>
        </c:if>

        <table>
            <thead>
                <tr>
                    <td >first name</td>
                    <td >date of birthday</td>
                    <td >last name</td>

                </tr>
            </thead>
            <tbody>

                <tr>
                    <td >${myprofile.firstname}</td>
                    <td >${myprofile.dateofbirthday}</td>
                    <td >${myprofile.lastname}</td>
            </tbody>

            <a href="myprofile/updatemydata">Edit user data</a>
    </body>
</html>
