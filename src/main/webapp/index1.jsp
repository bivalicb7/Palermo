<%-- 
    Document   : index
    Created on : Jul 1, 2019, 2:07:00 AM
    Author     : Los_e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello WebSocket</title>
        <link href="webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="main.css" rel="stylesheet">
        <script src="webjars/jquery/jquery.min.js"></script>
        <script src="webjars/sockjs-client/sockjs.min.js"></script>
        <script src="webjars/stomp-websocket/stomp.min.js"></script>
        <script src="js/app.js"></script>
    </head>
    <body>
        <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
            enabled. Please enable
            Javascript and reload this page!</h2></noscript>
        <div id="main-content" class="container">
            <div class="row">
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="connect">WebSocket connection:</label>
                            <button id="connect" class="btn btn-default" type="submit">Connect</button>
                            <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-md-6">
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="name">What is your name?</label>
                            <input type="text" id="name" class="form-control" placeholder="Your name here...">
                        </div>
                        <button id="send" class="btn btn-default" type="submit">Send</button>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="voter">Who is voting?</label>
                        <input type="text" id="voter" class="form-control" placeholder="Voter's name">
                    </div>
                    <div class="form-group">
                        <label for="personvotedout">Vote out:</label>
                        <select name="persons" id="personvotedout">
                            <option value="1">Vaggelis</option>
                            <option value="2">Liana</option>
                            <option value="3">Vasilis</option>
                            <option value="4">Minas</option>
                        </select>
                    </div>
                    <button id="sendvote" class="btn btn-default" type="submit">Sumbit vote</button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="conversation" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Greetings</th>
                        </tr>
                    </thead>
                    <tbody id="greetings">
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="voting" class="table table-striped">
                    <thead>
                        <tr>
                            <th>Votes</th>
                        </tr>
                    </thead>
                    <tbody id="votes">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
