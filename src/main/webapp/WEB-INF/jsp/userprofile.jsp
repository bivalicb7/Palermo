<%-- 
    Document   : userprofile
    Created on : 19 Ιουλ 2019, 3:40:10 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <title>JSP Page</title>
        <link href="css/userprofile.css" rel="stylesheet">
    </head>
    <body id="page">
        <c:if test="${loggedinuser.userid eq null}">
            <%@include file="errorpage.jsp"%>
        </c:if>
        <c:if test="${loggedinuser.userid ne null}">

            <%@ include file="navbar.jsp" %>
            </br>
            <c:if test="${myprofile.profileimagebase64 ne null}">
                <img src="data:image/png;base64, ${myprofile.profileimagebase64}"/>
            </c:if>
            <c:if test="${myprofile.profileimagebase64 eq null}">
                <img src="images/man-user.png"/>
            </c:if>

            </br></br>
            <a href="updateprofile/updatemydata">Edit user data</a>


            <div id="Info">
                <p>
                    <strong>First Name:</strong>
                    <span>${myprofile.firstname}</span>
                </p>
                <p>
                    <strong>Last Name:</strong>
                    <span>${myprofile.lastname}</span>
                </p>
                <p>
                    <strong>Date of birth:</strong>
                    <span>${myprofile.dateofbirthday}</span>
                </p>
                <p>
                    <strong>Number of wins:</strong>
                    <span type="number"> ${gameswon} </span>
                </p>
                <!--                <p>
                                    <strong>Number of losts:</strong>
                                    <span type="numner"> 1  </span>
                                </p>-->
            </div>

            <div id="gamefinishedcontainer">
                <c:choose>
                    <c:when test="${!empty finishedgames}">
                        <c:forEach  items="${finishedgames}" var="finishedgame">
                            <div class="gamefinished">
                                <p class="date">Start date: ${finishedgame.startdatetime}</p>
                                <p class="date">End date: ${finishedgame.enddatetime}</p>
                                <c:if test="${finishedgame.won eq 1}">
                                    <p class="ingamerole"> Won as a: ${finishedgame.ingamerole}</p>
                                </c:if>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p id="nofinishedgames">There are no finished games!</p>                    
                    </c:otherwise>
                </c:choose>


            </div>

            <!-- Needed because other elements inside ProfilePage have floats -->
            <div style="clear:both"></div>
        </div>
    </c:if>

</body>

</html>
