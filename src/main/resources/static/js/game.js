/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;
var tableid = null;
let allusers = [];
let alldeadusers = [];
let socketusersessionid = null;
let ingamerole = null;



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
    $("#killer_sendchatmessage").click(function () {
        sendKillerChatMessage();
    });
    $("#voteoutperson-button").click(function () {
        sendVote();
    });
    $("#killer_voteoutperson-button").click(function () {
        sendKillerVote();
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
            showVotingOptions();
        });

        stompClient.subscribe(`/topic/displayvote/${tableid}`, function (vote) {
            displayVote(JSON.parse(vote.body).voter, JSON.parse(vote.body).personvotedout);
        });

        stompClient.subscribe(`/topic/nextphase/${tableid}`, function (nextphase) {
            updateTableState(JSON.parse(nextphase.body).tablestate);
            triggerNextPhase(JSON.parse(nextphase.body).typeofphase);
        });

        stompClient.subscribe(`/topic/tievote/${tableid}`, function (tielist) {
            handleTie(JSON.parse(tielist.body).tievoteuserslist);
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

function sendKillerChatMessage() {
    stompClient.send(`/app/killer_chatsending/${tableid}`, {}, JSON.stringify({
        'message': document.querySelector("#killer_messagetextarea").value,
        'name': checkCookie("usernameincookie")
    }));

    document.querySelector("#killer_messagetextarea").value = "";
}

function sendReadyToStart() {
    stompClient.send(`/app/vote/readystate/${tableid}`, {}, JSON.stringify({
//        'message': document.querySelector("#messagetextarea").value,
//        'name': checkCookie("usernameincookie")
    }));

}

function sendVote() {

    if ($("#voteoutperson-select").val() != "") {
        stompClient.send(`/app/vote/gamevote/${tableid}`, {}, JSON.stringify(
                {
//                'voter' : socketusersessionid;
                    'phase': 'daykill',
                    'personvotedout': $("#voteoutperson-select").val()
                }
        ));
        document.querySelector("#voteoutperson-button").disabled = true;
        document.querySelector("#votingoptions label").innerHTML = "Choose Player";
    } else {
        document.querySelector("#votingoptions label").innerHTML = "You need to choose a player to vote out!";
    }

}

function sendKillerVote() {

    if ($("#killer_voteoutperson-select").val() != "") {
        stompClient.send(`/app/vote/gamevote/${tableid}`, {}, JSON.stringify(
                {
//                'voter' : socketusersessionid;
                    'phase': 'nightkill',
                    'personvotedout': $("#killer_voteoutperson-select").val()
                }
        ));
        document.querySelector("#killer_voteoutperson-button").disabled = true;
        document.querySelector("#killer_votingoptions label").innerHTML = "Choose Player";
    } else {
        document.querySelector("#killer_votingoptions label").innerHTML = "You need to choose a player to vote out!";
    }

}

function showChatmessage(message) {
    $("#incomingmessages").append("<tr><td>" + message + "</td></tr>");
    document.querySelector("#chattablecontainer").scrollTop = document.querySelector("#chattablecontainer").scrollHeight;
}

function showKillerChatmessage(message) {
    $("#killer_incomingmessages").append("<tr><td>" + message + "</td></tr>");
    document.querySelector("#killer_chattablecontainer").scrollTop = document.querySelector("#killer_chattablecontainer").scrollHeight;
}

function displayVote(voter, personvotedout) {

    var vote = document.createElement("div");
    vote.classList.add("mytooltip");

    var popup = document.createElement("span");
    popup.classList.add("mytooltiptext");

    if (voter == socketusersessionid) {
        popup.innerHTML = checkCookie("usernameincookie");
    } else {
        popup.innerHTML = document.querySelector(`li[usersessionid=${voter}] > p`).innerHTML;
    }

    vote.appendChild(popup);

    if (personvotedout == socketusersessionid) {
        document.querySelector("#votesoutreceived").appendChild(vote);
    } else {
        document.querySelector(`li[usersessionid=${personvotedout}] .votesoutreceived`).appendChild(vote);
    }
}

function showAssignedRoles(roles) {

    updateGameFlowInfo("Game has started! Chat and vote wisely!");

    document.querySelector("#ingamerole").innerHTML = roles[socketusersessionid];
    ingamerole = roles[socketusersessionid];

    if (roles[socketusersessionid] == "spy" || roles[socketusersessionid] == "hiddenkiller") {
        for (var elem in roles) {
            if (roles[elem] == "nothiddenkiller") {
                document.querySelector("#otheruserrole").innerHTML = "nothiddenkiller";

                //here the " " around ${elem} need to be removed!!!!
                document.querySelector("#otherusername").innerHTML = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
                //here the " " around ${elem} need to be removed!!!!

                document.querySelector("#extrainfo").classList.remove("hidediv");
            }
        }
    } else if (roles[socketusersessionid] == "nothiddenkiller") {
        for (var elem in roles) {
            if (roles[elem] == "hiddenkiller") {
                document.querySelector("#otheruserrole").innerHTML = "hiddenkiller";
                document.querySelector("#otherusername").innerHTML = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
                document.querySelector("#extrainfo").classList.remove("hidediv");

            }
        }
    } else {
        document.querySelector("#otheruserrole").innerHTML = "";
        document.querySelector("#otherusername").innerHTML = "";
        document.querySelector("#extrainfo").classList.add("hidediv");

    }

}

function showVotingOptions() {
    document.querySelector("#voteoutperson-select").innerHTML = `<option value=""></option>`;


    if (!alldeadusers.includes(socketusersessionid)) {

        document.querySelector("#startgamecontainer").classList.add("hidediv");

        allusers.forEach(function (elem) {

            if (socketusersessionid != elem && !alldeadusers.includes(elem)) {
                var option = document.createElement("option");
                option.setAttribute("value", elem);
                option.innerHTML = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
                document.querySelector("#voteoutperson-select").appendChild(option);


            }
        });
        document.querySelector("#voteoutperson-button").disabled = false;
        document.querySelector("#votingoptions").classList.remove("hidediv");
    }
}

function showTieVotingOptions(tielist) {
    document.querySelector("#voteoutperson-select").innerHTML = `<option value=""></option>`;

    if (!alldeadusers.includes(socketusersessionid)) {

        tielist.forEach(function (elem) {

            if (socketusersessionid != elem && !alldeadusers.includes(elem)) {
                var option = document.createElement("option");
                option.setAttribute("value", elem);
                option.innerHTML = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
                document.querySelector("#voteoutperson-select").appendChild(option);


            }
        });
        document.querySelector("#voteoutperson-button").disabled = false;
        document.querySelector("#votingoptions").classList.remove("hidediv");
    }

}

function showKillerVotingOptions() {
    document.querySelector("#killer_voteoutperson-select:not(:first-child)").innerHTML = `<option value=""></option>`;

//    document.querySelector("#startgamecontainer").classList.add("hidediv");
    allusers.forEach(function (elem) {

        if (!alldeadusers.includes(elem)) {
            var option = document.createElement("option");
            option.setAttribute("value", elem);

            if (socketusersessionid == elem) {
                option.innerHTML = checkCookie("usernameincookie");
            } else {
                option.innerHTML = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
            }
            document.querySelector("#killer_voteoutperson-select").appendChild(option);
        }
    });
    document.querySelector("#killer_voteoutperson-button").disabled = false;

//    document.querySelector("#votingoptions").classList.remove("hidediv");
}

function updateTableState(tablestate) {

    //Display new Users or remove users that have disconnected
    checkIfUsersAreaExistsElseDisplay(tablestate);
    //Check if table is full in order to start the game
    checkIfReadyToStart(allusers);
    //Check if user is dead
    checkIfDead(tablestate);

}

function checkIfUsersAreaExistsElseDisplay(tablestate) {
    let newusersarray = [];

    var list = document.querySelector("#userslist");
//    list.innerHTML = "";

    for (var elem in tablestate.usersintable) {
        let usernamevalue = tablestate.usersintable[elem].userprofileview.username;
        newusersarray.push(elem);

        if (!allusers.includes(elem)) {

            //Set user in page arrea
            if (usernamevalue == checkCookie("usernameincookie")) {
                document.querySelector("#userinpageusername").innerHTML = usernamevalue;
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

                var votesoutreceived = document.createElement("div");
                votesoutreceived.classList.add("votesoutreceived");

                li.appendChild(imgcontainer);
                li.appendChild(p);
                li.appendChild(votesoutreceived);
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

    allusers = newusersarray;
}

function checkIfDead(tablestate) {
    let newdeadlist = [];
    console.log("table state ", tablestate);
    for (var elem in tablestate.usersintable) {



        if (tablestate.usersintable[elem].dead == true) {
            let message = null;

            //update game flow info message to announce new dead person
            if (!alldeadusers.includes(elem)) {
                let usernameofdead = null;

                if (socketusersessionid == elem) {
                    usernameofdead = checkCookie("usernameincookie");
                } else {
                    usernameofdead = document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML;
                }

                if (tablestate.phase == "nightkill") {
                    if (tablestate.killbyrussianroulette == true) {
                        message = "User " + usernameofdead + " was killed in Russian Roulette!"
                    } else {
                        message = "User " + usernameofdead + " was voted out!";
                    }
                } else if (tablestate.phase == "daykill") {
                    message = "User " + usernameofdead + " was killed during the night!";
                }

                updateGameFlowInfo(message);
            }

            newdeadlist.push(elem);

            if (tablestate.usersintable[elem].userprofileview.username == checkCookie("usernameincookie")) {
                document.querySelector("#status p").innerHTML = "DEAD";
            } else {
                document.querySelector(`li[usersessionid=${elem}]`).classList.add("dead");
            }

        }
    }
    console.log("Dead people before: ", alldeadusers.length);
    console.log("Dead people after: ", newdeadlist.length);

    //If there is no new dead user that means that killers did not agree to killing someone so they lost their chance
    if (alldeadusers.length == newdeadlist.length && alldeadusers.length != 0) {
        updateGameFlowInfo("Killers lost their chance to kill someone!");
    }

    alldeadusers = newdeadlist;

}

function checkIfReadyToStart(array) {

    if (array.length == 6) {
        document.querySelector("#startbutton").disabled = false;
    } else {
        document.querySelector("#startbutton").disabled = true;
    }
}

function triggerNextPhase(typeofphase) {

    if (typeofphase == "nightkill") {
        console.log("Night fell on Palermo!");

        if (ingamerole == "hiddenkiller" || ingamerole == "nothiddenkiller") {
            showKillersChatAndSubscribe();
            let message = document.querySelector("#gameflowinfo textarea").innerHTML;
            updateGameFlowInfo(message + "\nNight has fallen! You get to kill one person. \n Beware that if you don't vote for the same person, your chance is lost.");
        } else {
            let message = document.querySelector("#gameflowinfo textarea").innerHTML;
            updateGameFlowInfo(message + "\nWait while killers pick their kill...");
        }
    } else if (typeofphase == "daykill") {
//        resetTableForNewRound(); //To be implemented
        console.log("a new day has started");
    }

}

function showKillersChatAndSubscribe() {
    document.querySelector("#chatcontainer").classList.add("hidediv");
    document.querySelector("#killer_chatcontainer").classList.remove("hidediv");
    showKillerVotingOptions();

    stompClient.subscribe(`/topic/killer_chatincoming/${tableid}`, function (chatmessage) {
        showKillerChatmessage(JSON.parse(chatmessage.body).content);
    });
}

function handleTie(usersintielist) {
    clearVotes();
    //Create game flow info message
    let message = "Vote tie between: ";

    usersintielist.forEach(function (elem) {
        message = message + document.querySelector(`li[usersessionid=${elem}] > p`).innerHTML + " ";
    });

    message = message + "\nSafe players get to vote once again. Attention: In case of another tie, Russian Roulette mode will be activated!"
    updateGameFlowInfo(message);

    //show voting options
    if (!usersintielist.includes(socketusersessionid)) {
        showTieVotingOptions(usersintielist);
    }
}

function clearVotes() {
    document.querySelector("#votesoutreceived").innerHTML = "";
    Array.from(document.querySelectorAll(".votesoutreceived ")).forEach(function(elem) {
        elem.innerHTML = "";
    });
}


function updateGameFlowInfo(message) {
    document.querySelector("#gameflowinfo textarea").innerHTML = message;
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
