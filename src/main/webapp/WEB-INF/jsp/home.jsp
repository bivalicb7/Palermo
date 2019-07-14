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
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <h1>Userid = ${loggedinuser.userid}</h1>
        <h1>Username = ${sessionScope.loggedinuser.username}</h1>



        <%--<c:if test="${param.success ne null}">--%>
        <!--<div id="overlay">-->
        <!--<a id="close" href="#overlay">&times;</a>-->

        <c:choose>
            <c:when test = "${empty runningtables}">
                <p id="lyrics" >No tables at the moment</p>
            </c:when>
            <c:when test = "${!empty runningtables}">
                <c:forEach items="${runningtables}" var="table">
                    <div id="tablecontainer" style="border: 3px solid black; width: auto; margin: 10px;">
                        <p> Table id: ${table.key} </p>
                        <p>Num of users:  ${table.value.usersintable.size()}</p>
                        
                        <ul id="userslist">
                            <c:forEach items="${table.value.usersintable}" var="userintable">
                                <li> ${userintable.value.user.username} </li>
                            </c:forEach>
                        </ul>

                        <c:url value="/lobby/joingame" var = "joinURL">
                            <c:param name="tableid" value="${table.value.gametableid}" />
                        </c:url>
                        <a href="${joinURL}">Join game</a>
                        <!--Key = ${entry.key}, value = ${entry.value}<br>-->
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
        <!--</div>-->
        <%--</c:if>--%>

        <a href="${pageContext.request.contextPath}/lobby/startgame">Create new game</a>
        <!--<script src="${pageContext.request.contextPath}/js/app.js"></script>-->
    </body>
</html>
