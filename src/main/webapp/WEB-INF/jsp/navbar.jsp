<%-- 
    Document   : navbar
    Created on : 9 Ιουλ 2019, 12:22:19 μμ
    Author     : djbil
--%>

<!-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
  
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
<div class="topnav">
  <a class="active" href="myprofile">My profile</a>
  <a href="main">Main</a>
  <a href="contact">Contact</a>
  <a href="about">About</a>
</div> -->



</body>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

    <link href="css/navbar.css" rel="stylesheet">
</head>
<body>
    <!--<div class="bg-img">-->

    <div class="topnav">
        <a href="lobby/home">Lobby</a>
        <a href="myprofile/showmyprofile">Update my data</a>
        <a href="updateprofile/showmydata">My profile</a>
        <a href="contact">Contact</a>
        <a href="about">About</a>

        <c:if test="${sessionScope.loggedinuser.role eq 'admin'}">
            <div id="adminmenu">
                <a href="allusers/showallusers">All users</a>
                <a href="ongoinggames/showongoinggames">Ongoing games</a>
            </div>
        </c:if>

        <p>${loggedinuser.username}</p>
        <a href="logout">Log out</a>


    </div>



</body>
</html>
