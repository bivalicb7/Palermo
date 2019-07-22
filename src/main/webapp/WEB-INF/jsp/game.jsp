<%-- 
    Document   : index
    Created on : Jul 1, 2019, 2:07:00 AM
    Author     : Los_e
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
        <title>Hello WebSocket</title>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
        <link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/game.css" rel="stylesheet">
        <script src="webjars/jquery/jquery.min.js"></script>
        <script src="webjars/sockjs-client/sockjs.min.js"></script>
        <script src="webjars/stomp-websocket/stomp.min.js"></script>
        <script src="js/game.js"></script>
    </head>
    <body>
        <%@ include file="navbar.jsp" %>
        <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
            enabled. Please enable
            Javascript and reload this page!</h2></noscript>

        <div id="gamecontainer">

            <div id="tablecontainer">
                <!--<div id="usersintable">-->
                    <ul id="userslist">

                    </ul>
                <!--</div>-->
                <div id="userinpageseat">
                    <div class="userinpageimgcontainer">
                    <img src="images/man-user.png" alt="user's image">
                    </div>
                    <p></p>
                    <div id="ingamerolecontainer">
                        <img/>
                        <p id="ingamerole"></p>
                    </div>
                    <div id="extrainfo" style="border: 1px solid;">
                        <p id="otheruserrole"></p>
                        <p id="otherusername"></p>
                    </div>
                </div>
                <div id="votingareacont">
                    <div id="votingarea">
                        <div id="startgamecontainer">
                            <button id="startbutton" disabled="true">Start game</button>
                        </div>
                    </div>
                </div>
            </div>


            <div id="chatcontainer">
                <h2>Chat</h2>
                <div id="chattablecontainer">
                    <table id="conversation" class="table table-striped">
                        <tbody id="incomingmessages">
                        </tbody>
                    </table>
                </div>

                <textarea id="messagetextarea" placeholder="Type message.."></textarea>
                <button id="sendchatmessage" type="submit">Send</button>
            </div>
        </div>
    </body>
</html>
