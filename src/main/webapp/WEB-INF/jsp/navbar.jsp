<%-- 
    Document   : navbar
    Created on : 9 Ιουλ 2019, 12:22:19 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link href="css/navbar.css" rel="stylesheet">
</head>
<body>
    <div class="topnav">
        <a href="lobby/home">Lobby</a>
        <a href="updateprofile/createmyprofile">Update my data</a>
        <a href="myprofile/showmydata">My profile</a>
        <a href="contact">Contact</a>
        <a href="about">About</a>

        <c:if test="${sessionScope.loggedinuser.role eq 'admin'}">
            <div id="adminmenu">
                <a href="allusers/showallusers">All users</a>
            </div>
        </c:if>
        <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_top" class="topnav">
            <input type="hidden" name="cmd" value="_s-xclick" />
            <input type="hidden" name="hosted_button_id" value="CM6S52A42ZU58" />
            <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donate_SM.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal button" />
            <img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1" />
        </form>

        <p>${loggedinuser.username}</p>
        <a href="logout">Log out</a>
</body>
</div>
