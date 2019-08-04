<%-- 
    Document   : afterdonation
    Created on : 4 Αυγ 2019, 6:03:36 μμ
    Author     : djbil
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" /><!DOCTYPE html>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <!--<script src="js/afterdonation.js"></script>-->
    <link href="css/afterdonation.css" rel="stylesheet">
        <title>Donation</title>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <h1>Something went wrong :(</h1>
    </body>
</html>
