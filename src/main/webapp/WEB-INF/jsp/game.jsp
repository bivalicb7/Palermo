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
        <link href="css/phase.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <script src="webjars/jquery/jquery.min.js"></script>
        <script src="webjars/sockjs-client/sockjs.min.js"></script>
        <script src="webjars/stomp-websocket/stomp.min.js"></script>
        <script src="js/game.js"></script>
        <script src="js/phase.js"></script>
    </head>
    <body>
        <c:if test="${loggedinuser.userid eq null}">
            <%@include file="errorpage.jsp"%>
            </c:if>
        <c:if test="${loggedinuser.userid ne null}">
            
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
                    <div id="status">
                        <p>Alive</p>
                    </div>
                    <div class="userinpageimgcontainer">
                        <img src="images/man-user.png" alt="user's image">
                    </div>
                    <p id="userinpageusername"></p>
                    <div id="ingamerolecontainer">
                        <img/>
                        <p id="ingamerole"></p>
                    </div>
                    <div id="extrainfo"  class="hidediv" style="border: 1px solid;">
                        <p id="otheruserrole"></p>
                        <p id="otherusername"></p>
                    </div>
                    <div id="votesoutreceived"></div>
                </div>

                <div id="gameflowinfo">
                    <textarea>Click "Start Game" to start</textarea>
                    <div id="startgamecontainer">
                        <button id="startbutton" disabled="true">Start game</button>
                    </div>
                </div>

                <div id="votingareacont">
                    <div id="votingarea">
                        <div id="votingoptions" class="hidediv">
                            <label for="voteoutperson-select">Choose Player</label>
                            <select id="voteoutperson-select">
                                <option value=""></option>
                            </select>
                            <button id="voteoutperson-button">Vote out!</button>
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

            <div id="killer_chatcontainer" class="hidediv">
                <h2>Killers Chat!</h2>
                <div id="killer_votingoptions">
                    <label for="killer_voteoutperson-select">Choose Player</label>
                    <select id="killer_voteoutperson-select">
                        <option value=""></option>
                    </select>
                    <button id="killer_voteoutperson-button">Kill!</button>
                </div>
                <div id="killer_chattablecontainer">
                    <table id="killer_conversation" class="table table-striped">
                        <tbody id="killer_incomingmessages">
                        </tbody>
                    </table>
                </div>
                <textarea id="killer_messagetextarea" placeholder="Type message.."></textarea>
                <button id="killer_sendchatmessage" type="submit">Send</button>
            </div>

        </div>
            </c:if>

        <div id="endingmodalcont" class="hidediv">
            <div id="endingmodal" >
                <p id="endheader">The game is over!</p>
                <p id="endresult"></p>
                <ul id="endresultuserslist"></ul>            
                <button id="replaybutton">Replay</button>
            </div>

        </div>

    </body>
</html>
