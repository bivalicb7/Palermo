/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;
let allexistingtableids = [];
let userrole = checkCookie("roleincookie");

$(function () {

    $(document).ready(function () {
        connect();
    });

    document.addEventListener("click", function (event) {
        if (event.target.nodeName == "BUTTON" && event.target.classList.contains("banbutton")) {
            console.log(event.target.attributes.table_id.value);
            sendBan(event.target.value, event.target.attributes.table_id.value);
        }
    });

});

function connect() {
    var socket = new SockJS('/palermo/lobby');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe(`/topic/tablesinlobby`, function (tableinlobby) {
            checkTables(JSON.parse(tableinlobby.body));
        });

    });
}

function sendBan(sessionid, tableid) {
    stompClient.send(`/app/vote/ban/${tableid}/${sessionid}`, {}, JSON.stringify({
    }));
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function checkTables(tablesinlobby) {
    let newtableidsarray = [];

    for (var elem in tablesinlobby.gametablesinlobby) {
        //Keep track of the new pack of tables
        newtableidsarray.push(elem);

        //Check if table doesn't exist and display 
        if (!allexistingtableids.includes(elem)) {
            displayTable(tablesinlobby, elem);
            checkIfTableIsFull(tablesinlobby.gametablesinlobby[elem]);

        } else {
            updateUsersInTable(tablesinlobby, elem);
            checkIfTableIsFull(tablesinlobby.gametablesinlobby[elem]);
        }
    }

    //filter method
    function notincluded(elem) {
        return !newtableidsarray.includes(elem);
    }

    let tablestoberemoved = allexistingtableids.filter(notincluded);
    removeInactiveTables(tablestoberemoved);

    allexistingtableids = newtableidsarray;
}


function  displayTable(tablesinlobby, elem) {

    var list = document.querySelector("#tableslist");
    var fragment = document.createDocumentFragment();

    var divcont = document.createElement("div");
    divcont.classList.add("tableentrance", "tablecontainer");
    divcont.setAttribute("id", `table_id${tablesinlobby.gametablesinlobby[elem].gametableid}`);
    divcont.setAttribute("style", "border: 3px solid black; width: auto; margin: 0 10px 10px 10px;");

    var numberofseats = document.createElement("p");
    numberofseats.classList.add("numberofseats");
    numberofseats.innerHTML = `Seats: ${tablesinlobby.gametablesinlobby[elem].numofplayers}`;
    var numofusers = document.createElement("p");
    numofusers.classList.add("numofusers");
    numofusers.innerHTML = `Num of users: ${Object.keys(tablesinlobby.gametablesinlobby[elem].usersintable).length}`;
    var userslist = document.createElement("ul");
    userslist.setAttribute("class", "userslist");
//        li.innerHTML = tablestate.usersintable[elem].user.username;

    for (var userelem in tablesinlobby.gametablesinlobby[elem].usersintable) {
        var li = document.createElement("li");
        li.innerHTML = `${tablesinlobby.gametablesinlobby[elem].usersintable[userelem].userprofileview.username} ${tablesinlobby.gametablesinlobby[elem].usersintable[userelem].userprofileview.firstname} ${tablesinlobby.gametablesinlobby[elem].usersintable[userelem].userprofileview.lastname} `;

        if (userrole == "admin") {
            var banbutton = document.createElement("button");
            banbutton.classList.add("banbutton");
            banbutton.setAttribute("value", userelem);
            banbutton.setAttribute("table_id", tablesinlobby.gametablesinlobby[elem].gametableid);
            banbutton.innerHTML = "Ban";
            li.appendChild(banbutton);
        }
        userslist.appendChild(li);

//            console.log(tablesinlobby.gametablesinlobby[elem].usersintable[userelem].user.username);
    }

    var jointablelink = document.createElement("a");
    jointablelink.innerHTML = "Join table";
    jointablelink.setAttribute("href", `lobby/joingame?tableid=${tablesinlobby.gametablesinlobby[elem].gametableid}`);

    divcont.appendChild(numberofseats);
    divcont.appendChild(numofusers);
    divcont.appendChild(userslist);
    divcont.appendChild(jointablelink);
    fragment.appendChild(divcont);
    list.prepend(fragment);

}

function removeInactiveTables(tableidsarray) {
    var list = document.querySelector("#tableslist");
    tableidsarray.forEach(function (id) {
        list.removeChild(document.querySelector(`#table_id${id}`));
    });

}

function updateUsersInTable(tablesinlobby, id) {
    let list = document.querySelector(`#table_id${id} > .userslist`);
    let numofusers = 0;

    let usernames = [];

    list.querySelectorAll("li").forEach(function (username) {
        usernames.push(username.innerHTML);
    });

    list.innerHTML = "";
    for (var userelem in tablesinlobby.gametablesinlobby[id].usersintable) {
        ++numofusers;
        var li = document.createElement("li");
        li.innerHTML = `${tablesinlobby.gametablesinlobby[id].usersintable[userelem].userprofileview.username} ${tablesinlobby.gametablesinlobby[id].usersintable[userelem].userprofileview.firstname} ${tablesinlobby.gametablesinlobby[id].usersintable[userelem].userprofileview.lastname} `;
       
        if (userrole == "admin") {
            var banbutton = document.createElement("button");
            banbutton.classList.add("banbutton");
            banbutton.setAttribute("value", userelem);
            banbutton.setAttribute("table_id", tablesinlobby.gametablesinlobby[id].gametableid);
            banbutton.innerHTML = "Ban";
            li.appendChild(banbutton);
        }
        
        if (!usernames.includes(li.innerHTML)) {
            li.classList.add("userentrance");
        }
        list.appendChild(li);
    }
    document.querySelector(`#table_id${id} > .numofusers`).innerHTML = `Num of users: ${numofusers}`;
}

function checkIfTableIsFull(tableparam) {
    console.log("tableparam ", tableparam);
    let table = document.querySelector(`#table_id${tableparam.gametableid}`);
    if (table.querySelector(".userslist").querySelectorAll("li").length == tableparam.numofplayers) {
        console.log("FULL TABLE");
        table.querySelector("a").style.display = "none";
    } else {

        if (tableparam.gamestarted) {
            console.log("Ongoing game! Can't join right now");
            table.querySelector("a").style.display = "none";
        } else {
            table.querySelector("a").style.display = "block";
        }
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


