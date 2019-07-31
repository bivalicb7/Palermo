<%-- 
    Document   : allusers
    Created on : Jul 30, 2019, 5:16:58 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" /><!DOCTYPE html>
<html>
    <head>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All users</title>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>

        <h1>All users</h1>
        <c:choose>
            <c:when test="${!empty allusers}">
                <table>
                    <thead>
                        <tr>
                            <td >Profile picture</td>
                            <td >User id</td>
                            <td >Username</td>
                            <td >First Name</td>
                            <td >Last Name</td>
                            <td >Email</td>
                            <td >Role</td>
                            <td >Account status</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allusers}" var="user">
                            <tr>
                                <c:choose>
                                    <c:when test="${user.profileimagebase64 != null}">
                                        <td ><img src="data:image/png;base64, ${user.profileimagebase64}" alt="profile pic"/></td>
                                        </c:when>    
                                        <c:otherwise>
                                        <td ><img src="images/man-user.png" alt="profile pic"/></td>
                                        </c:otherwise>
                                    </c:choose>

                                <td >${user.userid}</td>
                                <td >${user.username}</td>
                                <td >${user.firstname}</td>
                                <td >${user.lastname}</td>
                                <td >${user.email}</td>
                                <td >${user.role}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p id="nousers">No users at the moment</p>                    
            </c:otherwise>
        </c:choose>
    </body>
</html>
