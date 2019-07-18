<%-- 
    Document   : home
    Created on : Jul 8, 2019, 7:07:17 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/lobby.css" rel="stylesheet">
        <script src="webjars/jquery/jquery.min.js"></script>
        <script src="webjars/sockjs-client/sockjs.min.js"></script>
        <script src="webjars/stomp-websocket/stomp.min.js"></script>
        <script src="js/lobby.js"></script>

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

        <a href="lobby/startgame">Create new game</a>
    </body>
</html>
