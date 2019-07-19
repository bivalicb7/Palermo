<%-- 
    Document   : test
    Created on : Jul 19, 2019, 4:05:07 AM
    Author     : Los_e
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                <form  method="Post" action="/palermo/index/test" enctype="multipart/form-data">
            <input class="inputfile" id ="upload" type="file" name="myfile" >
            <input class="btn-1" type="submit">
        </form>
        
        <%--<c:if test="${image.length >0}">--%>
                    <img src="data:image/png;base64, ${image}"/>
        <%--</c:if>--%>
    </body>
</html>
