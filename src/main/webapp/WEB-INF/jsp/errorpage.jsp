<%-- 
    Document   : errorpage
    Created on : 20 Ιουλ 2019, 1:27:13 πμ
    Author     : djbil
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
    .center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 30%;
}
.text{
    color:brown;
    text-align: center;
  
    </style>
</head>
<body>
        <h2 class="text">Follow the link in your email to activate your account.</h2>
    </br>
    <img src="/palermo/images/ashamed.jpg" alt="sad face" class="center">


</body>
</html>
