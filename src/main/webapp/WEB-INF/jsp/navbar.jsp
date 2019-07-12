<%-- 
    Document   : navbar
    Created on : 9 Ιουλ 2019, 12:22:19 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
 .bg-img {background-image: url("${pageContext.request.contextPath}/img/palermo.jpg");

  min-height: 380px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
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
<div class="bg-img">
<div class="topnav">
  <a class="active" href="myprofile">My profile</a>
  <a href="main">Main</a>
  <a href="contact">Contact</a>
  <a href="about">About</a>
</div>



</body>
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

            .topnav p {
                /*  float: right;*/
                color: #f2f2f2;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
                position: absolute;
                right: -1px;
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
            <p>${loggedinuser.username}</p>

        </div>



    </body>
</html>
