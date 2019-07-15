/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var stompClient = null;


$(function () {

    $(document).ready(function () {
        connect();
    });

});

function connect() {
    var socket = new SockJS('/palermo/lobby');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe(`/topic/tablesinlobby`, function (tableinlobby) {
            showTables(JSON.parse(tableinlobby.body));
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}


function showTables(tablesinlobby) {

//    console.log(tablesinlobby);
    var list = document.querySelector("#tableslist");
    list.innerHTML = "";
    var fragment = document.createDocumentFragment();
    for (var elem in tablesinlobby.gametablesinlobby) {
        var divcont = document.createElement("div");
        divcont.setAttribute("id", "tablecontainer");
        divcont.setAttribute("style", "border: 3px solid black; width: auto; margin: 10px;");

        var tableid = document.createElement("p");
        tableid.innerHTML = `Table id: ${tablesinlobby.gametablesinlobby[elem].gametableid}`;
        var numofusers = document.createElement("p");
        numofusers.innerHTML = `Num of users: ${Object.keys(tablesinlobby.gametablesinlobby[elem].usersintable).length}`;
        var userslist = document.createElement("ul");
//        li.innerHTML = tablestate.usersintable[elem].user.username;

        for (var userelem in tablesinlobby.gametablesinlobby[elem].usersintable) {
            var li = document.createElement("li");
            li.innerHTML = tablesinlobby.gametablesinlobby[elem].usersintable[userelem].user.username;
            userslist.appendChild(li);
//            console.log(tablesinlobby.gametablesinlobby[elem].usersintable[userelem].user.username);
        }
        
        var jointablelink = document.createElement("a");
        jointablelink.innerHTML = "Join table";
        jointablelink.setAttribute("href", `joingame?tableid=${tablesinlobby.gametablesinlobby[elem].gametableid}`);
        
        divcont.appendChild(tableid);
        divcont.appendChild(numofusers);
        divcont.appendChild(userslist);
        divcont.appendChild(jointablelink);
        fragment.appendChild(divcont);
        list.appendChild(fragment);
    }
    
}