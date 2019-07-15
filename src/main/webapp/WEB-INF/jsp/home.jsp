<%-- 
    Document   : home
    Created on : Jul 8, 2019, 7:07:17 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/main.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/webjars/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/sockjs-client/sockjs.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/stomp-websocket/stomp.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lobby.js"></script>

    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <h1>Userid = ${loggedinuser.userid}</h1>
        <h1>Username = ${sessionScope.loggedinuser.username}</h1>

        <div id="tableslist"></div>

        <%--<c:if test="${param.success ne null}">--%>
        <!--<div id="overlay">-->
        <!--<a id="close" href="#overlay">&times;</a>-->


        <!--</div>-->
        <%--</c:if>--%>

        <a href="${pageContext.request.contextPath}/lobby/startgame">Create new game</a>
        <!--<script src="${pageContext.request.contextPath}/js/app.js"></script>-->
    </body>
</html>
