/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;

console.log("hi");

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
    $("#votes").html("");
}

//function connect() {
//    var socket1 = new SockJS('gs-guide-websocket');
//    var socket2 = new SockJS('votesSocket');
//    stompClient1 = Stomp.over(socket1);
//    stompClient2 = Stomp.over(socket2);
//    stompClient1.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient1.subscribe('/topic/greetings', function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
//    });
//    stompClient2.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient2.subscribe('/topic/voting', function (votegreeting) {
//            showVote(JSON.parse(votegreeting.body).content);
//        });
//    });
//}
function connect() {
    var socket = new SockJS('gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/topic/voting', function (votegreeting) {
            showVote(JSON.parse(votegreeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendVote() {
    stompClient.send("/app/vote", {}, JSON.stringify(
            {
                'voter': $("#voter").val(),
                'personvotedout': $("#personvotedout").val()
            }
    ));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function showVote(votemessage) {
    $("#votes").append("<tr><td>" + votemessage + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    $("#sendvote").click(function () {
        sendVote();
    });
});
