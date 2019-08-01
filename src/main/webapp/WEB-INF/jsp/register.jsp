<%-- 
    Document   : register
    Created on : Jul 8, 2019, 6:48:29 PM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="springForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<html>
    <head>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

        <!--<link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
        <script src="webjars/jquery/jquery.min.js"></script>
        <link href="css/register.css" rel="stylesheet">

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
              integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <div class="container">
        <img src="/palermo/images/title.png">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h3 align="center">Sign Up</h3>
                    <
                </div>
                <div class="card-body">
                    <springForm:form method="post" modelAttribute="user" action="register/docreateaccount">

                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <springForm:input path="username" type="text" class="form-control" placeholder="enter a username"/>
                            <springForm:errors path="username" />

                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-unlock"></i></span>
                            </div>
                            <springForm:input path="password" type="password" class="form-control" placeholder="enter a password"/>
                            <springForm:errors path="password" />
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-unlock"></i></span>
                            </div>
                            <input path="password" name="confirm password" type="password" class="form-control" placeholder="enter a password"/>
                            <springForm:errors path="password" />
                        </div>
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-user"></i></span>
                            </div>
                            <springForm:input path="email" type="email" class="form-control" placeholder="enter your email"/>
                            <springForm:errors path="username" />

                        </div>

                        <div class="form-group">
                            <input type="submit" value="Sign Up" class="btn float-right login_btn">
                        </div>
                    </springForm:form>
                </div>                 
            </div>
        </div>
    </div>
</body>
<script src="js/register.js"></script>
</html>
