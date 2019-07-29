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
        <c:if test="${loggedinuser.userid eq null}">
            <%@include file="errorpage.jsp"%>
        </c:if>
        <c:if test="${loggedinuser.userid ne null}">


            <%@ include file="navbar.jsp" %>
            <h1>Userid = ${loggedinuser.userid}</h1>
            <h1>Username = ${sessionScope.loggedinuser.username}</h1>

            <div id="tableslist"></div>

            <%--<c:if test="${param.success ne null}">--%>
            <!--<div id="overlay">-->
            <!--<a id="close" href="#overlay">&times;</a>-->


            <!--</div>-->
            <%--</c:if>--%>
            <!--<div >-->
                <form id="creategamecontainer" action="lobby/startgame">
                <label for="creategame_seat_total">Choose number of players</label>
                <select id="creategame_seat_total" name="numofplayers">
                    <option value=""></option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
                <input type="submit" value="Create new game">
                <!--<a href="lobby/startgame">Create new game</a>-->
                </form>
            <!--</div>-->
        </c:if>
    </body>
</html>
