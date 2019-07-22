/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;
var tableid = null;
let allusers = [];
let socketusersessionid = null;



$(function () {

    tableid = getCookie("tableidincookie");
    $("form").on("submit", function (e) {
        e.preventDefault();
    });
    $(document).ready(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#sendchatmessage").click(function () {
        sendChatMessage();
    });
    $("#sendvote").click(function () {
        sendVote();
    });
    $("#startbutton").click(function () {
        sendReadyToStart();
    });
});

function connect() {
    var socket = new SockJS('/palermo/game');
    stompClient = Stomp.over(socket);
    stompClient.connect({tableid: tableid}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe(`/topic/chatincoming/${tableid}`, function (chatmessage) {
            showChatmessage(JSON.parse(chatmessage.body).content);
        }, {userid: checkCookie("useridincookie")});

        stompClient.subscribe(`/topic/tablestate/${tableid}`, function (tablestate) {
            updateTableState(JSON.parse(tablestate.body));
        }, {
            userid: checkCookie("useridincookie"),
            tableid: tableid
        });

        stompClient.subscribe(`/topic/voting/${tableid}`, function (votegreeting) {
            showVote(JSON.parse(votegreeting.body).content);
        }, {userid: checkCookie("useridincookie")});

        stompClient.subscribe(`/topic/roles/${tableid}`, function (roles) {
            showAssignedRoles(JSON.parse(roles.body).roles);
        });
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

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect(function () {}, {tableid: tableid});
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendChatMessage() {
    stompClient.send(`/app/chatsending/${tableid}`, {}, JSON.stringify({
        'message': document.querySelector("#messagetextarea").value,
        'name': checkCookie("usernameincookie")
    }));

    document.querySelector("#messagetextarea").value = "";
}

function sendReadyToStart() {
    stompClient.send(`/app/vote/readystate/${tableid}`, {}, JSON.stringify({
//        'message': document.querySelector("#messagetextarea").value,
//        'name': checkCookie("usernameincookie")
    }));

}

function sendVote() {
    stompClient.send("/app/vote", {}, JSON.stringify(
            {
                'voter': $("#voter").val(),
                'personvotedout': $("#personvotedout").val()
            }
    ));
}

function showChatmessage(message) {
    $("#incomingmessages").append("<tr><td>" + message + "</td></tr>");
    document.querySelector("#chattablecontainer").scrollTop = document.querySelector("#chattablecontainer").scrollHeight;
}
function showVote(votemessage) {
    $("#votes").append("<tr><td>" + votemessage + "</td></tr>");
}

function showAssignedRoles(roles) {

    document.querySelector("#ingamerole").innerHTML = roles[socketusersessionid];

    if (roles[socketusersessionid] == "spy" || roles[socketusersessionid] == "hiddenkiller" ) {
        for (var elem in roles) {
            if (roles[elem] == "nothiddenkiller") {
                  document.querySelector("#otheruserrole").innerHTML = "nothiddenkiller";
                  document.querySelector("#otherusername").innerHTML = document.querySelector(`li[usersessionid="${elem}"] > p`).innerHTML;
            }
        }
    }  
    else if (roles[socketusersessionid] == "nothiddenkiller") {
        for (var elem in roles) {
            if (roles[elem] == "hiddenkiller") {
                  document.querySelector("#otheruserrole").innerHTML = "hiddenkiller";
                  document.querySelector("#otherusername").innerHTML = document.querySelector(`li[usersessionid="${elem}"] > p`).innerHTML;
            }
        }
    }
    else {
                      document.querySelector("#otheruserrole").innerHTML = "";
                  document.querySelector("#otherusername").innerHTML = "";
    }


}

function updateTableState(tablestate) {

    let newusersarray = [];

    var list = document.querySelector("#userslist");
//    list.innerHTML = "";

    for (var elem in tablestate.usersintable) {
        let usernamevalue = tablestate.usersintable[elem].userprofileview.username;
        newusersarray.push(elem);

        if (!allusers.includes(elem)) {

            //Set user in page arrea
            if (usernamevalue == checkCookie("usernameincookie")) {
                document.querySelector("#userinpageseat p").innerHTML = usernamevalue;
                document.querySelector("#userinpageseat").setAttribute("usersessionid", elem);
                socketusersessionid = elem;

                //Check if image is null in case default avatar needs to be displayed
                if (tablestate.usersintable[elem].userprofileview.profileimagebase64 != "") {
                    document.querySelector("#userinpageseat img").setAttribute("src", `data:image/png;base64, ${tablestate.usersintable[elem].userprofileview.profileimagebase64}`);
                } else {
                    document.querySelector("#userinpageseat img").setAttribute("src", "images/man-user.png");
                }


            } else {

                // Set other players
                var li = document.createElement("li");
                li.setAttribute("usersessionid", elem);
                var imgcontainer = document.createElement("div");
                imgcontainer.setAttribute("class", "imgcontainer");
                var img = document.createElement("img");

                if (tablestate.usersintable[elem].userprofileview.profileimagebase64 != "") {
                    img.setAttribute("src", `data:image/png;base64, ${tablestate.usersintable[elem].userprofileview.profileimagebase64}`);
                } else {
                    img.setAttribute("src", "images/man-user.png");
                }
                imgcontainer.appendChild(img);
                var p = document.createElement("p");
                p.innerHTML = usernamevalue;

                li.appendChild(imgcontainer);
                li.appendChild(p);
                li.classList.add("seat", "userentrance");
                list.appendChild(li);
            }

        }

    }

    //  Remove disconnected users
    function notincluded(elem) {
        return !newusersarray.includes(elem);
    }
    let userstoberemoved = allusers.filter(notincluded);

    userstoberemoved.forEach((elem) => {
        list.removeChild(list.querySelector(`li[usersessionid=${elem}]`));
    });

    //Check if table is full in order to start the game
    checkIfReadyToStart(newusersarray);

    allusers = newusersarray;
}

function checkIfReadyToStart(array) {

    if (array.length == 6) {
        document.querySelector("#startbutton").disabled = false;
    } else {
        document.querySelector("#startbutton").disabled = true;
    }
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
