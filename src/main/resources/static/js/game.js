/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;
var tableid = null;


$(function () {
    
    tableid = getCookie("tableidincookie");
    $("form").on("submit", function(e) {
        e.preventDefault();
    });
    $(document).ready(function () {
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

function connect() {
    var socket = new SockJS('/palermo/game');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/topic/greetings/${tableid}`, function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        }, {userid: checkCookie("useridincookie")});
        stompClient.subscribe(`/topic/voting/${tableid}`, function (votegreeting) {
            showVote(JSON.parse(votegreeting.body).content);
        }, {userid: checkCookie("useridincookie")});
    });
}

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


//function connect2() {
//    num =2;
//    var socket = new SockJS('/palermo/game/liana2');
//    stompClient = Stomp.over(socket);
//    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings/2', function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
//        stompClient.subscribe('/topic/voting', function (votegreeting) {
//            showVote(JSON.parse(votegreeting.body).content);
//        });
//    });
//}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send(`/app/hello/${tableid}`, {}, JSON.stringify({'name': $("#name").val()}));
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

//Cookie play

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie(cname) {
    var value = getCookie(cname);
    if (value != "") {
        return value;
    } else {
        return 0;
    }
}
