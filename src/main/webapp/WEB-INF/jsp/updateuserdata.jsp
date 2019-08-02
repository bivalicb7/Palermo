<%-- 
    Document   : userprofile
    Created on : 15 Ιουλ 2019, 8:41:21 μμ
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
        <style>

            input[type=text], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            #editform {
                margin-left: auto;
                margin-right: auto;
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 10px;
                top: 50%;
                width: 40%;
                text-align: center;
                font-size: 18px;
            }

            input[type=submit] {
                width: 100%;
                background-color:darkred ;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-align: center;
            }
            input[type=submit]:hover {
                background-color:#000;
            }

        </style>
    </head>
    <body>
        <c:if test="${loggedinuser.userid eq null}">
            <%@include file="errorpage.jsp"%>
        </c:if>
        <c:if test="${loggedinuser.userid ne null}">
            
            <%@ include file="navbar.jsp" %>
            
            <springForm:form id="editform" method="post" modelAttribute="myprofile" action ="${pageContext.request.contextPath}/updateprofile/addmydata" enctype="multipart/form-data">
                <div id="form">
                    <table>
                        <tr>
                            <td>First name</td>
                            <td><springForm:input path="firstname"  placeholder="Your first name.."/></td> 
                            <td><springForm:errors path="firstname"/></td> 
                        </tr>
                        <tr>
                            <td>Last name</td>
                            <td><springForm:input path="lastname"  placeholder="Your last name.."/></td> 
                            <td><springForm:errors path="lastname"/></td> 
                        </tr>
                        <tr>
                            <td>Date Of Birth</td>
                            <td><springForm:input type="date" path="dateofbirthday"/></td> 
                            <td><springForm:errors path="dateofbirthday"/></td> 
                        </tr>
                        <tr>
                        <input class="inputfile" id ="upload" type="file" name="myfile" accept=".png, .jpg" data-max-size="2048">
                        <label for="upload">Choose file to upload</label>
                        </tr>
                        <input type="submit">
                        </table>
                        </div>
                    </springForm:form>
                </c:if>
                </body>
                </html>
