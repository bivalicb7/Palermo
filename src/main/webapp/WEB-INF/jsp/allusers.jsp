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
                            <td >Update user</td>
                            <td >Delete user</td>
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
                        <form action="allusers/updateuserinfo" method="post">
                            <td >
                                <select name="role">
                                    <c:choose>
                                        <c:when test="${user.role eq 'plainuser'}">
                                            <option value="${user.role}">Plain user</option>
                                            <option value="admin">Administrator</option>
                                        </c:when>
                                        <c:when test="${user.role eq 'admin'}">
                                            <option value="${user.role}">Administrator</option>
                                            <option value="plainuser">Plain user</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </td>

                            <td >
                                <select name="active">
                                    <c:choose>
                                        <c:when test="${user.active eq '0'}">
                                            <option value="${user.active}">Inactive</option>
                                            <option value="1">Active</option>
                                        </c:when>
                                        <c:when test="${user.active eq '1'}">
                                            <option value="${user.active}">Active</option>
                                            <option value="0">Inactive</option>
                                        </c:when>
                                    </c:choose>
                                </select>
                            </td>
                            <td ><input type="submit"></td>
                            <input type="hidden" value="${user.userid}" name ="userid">
                        </form>

                        <td ><a href="allusers/deleteuser/${user.userid}">Delete</a></td>
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
<script src="js/allusers.js"></script>
</html>
