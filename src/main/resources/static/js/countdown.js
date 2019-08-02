/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//let seconds = document.getElementById("countdown").innerHTML;
function showRussian() {


    let div = document.createElement("div");
    div.classList.add("phasecontainer");
    let cont = document.createElement("div");
    cont.setAttribute("id", "countercont");

    let counter = document.createElement("div");
    counter.setAttribute("id", "countdown");
    counter.innerHTML = "3";

    cont.appendChild(counter);
    div.appendChild(cont);

    document.querySelector("#gamecontainer").appendChild(div);

    let imgtext = document.createElement("img");
    imgtext.setAttribute("src", "images/russian.png");

    imgtext.classList.add("imgtextrussian");

    div.appendChild(imgtext);

    document.querySelector(".imgtextrussian").style.display = "block";
    document.querySelector(".imgtextrussian").classList.add("animated");
    document.querySelector(".imgtextrussian").classList.add("bounceInUp");

    document.querySelector(".imgtextrussian").addEventListener("animationend", function (event) {


        if (event.animationName == "bounceInUp") {

            initCountdown();

//            setTimeout(function () {
//                document.querySelector(".imgtextrussian").classList.add("bounceOutUp");
//            }, 3000);
        }

        if (event.animationName == "bounceOutUp") {
            clearPhase();
        }
    });
}

function clearPhase() {
    document.querySelector("#gamecontainer").removeChild(document.querySelector(".phasecontainer"));
}

function countdownfunc() {
    let seconds = null;
    if (document.querySelector("#countdown") != null) {
        seconds = document.querySelector("#countdown").innerHTML;
    } else {
        seconds = 3;
    }
    seconds--;
//    (seconds == 1) ? document.getElementById("plural").textContent = "" : document.getElementById("plural").textContent = "s";
    document.getElementById("countdown").innerHTML = seconds;
    if (seconds <= 0) {
        document.getElementById("countdown").innerHTML = 0;
        document.querySelector(".imgtextrussian").classList.add("bounceOutUp");
        document.querySelector("#countercont").classList.add("animated", "bounceOutDown");

    }

}
function initCountdown() {

    var myVar = setInterval(countdownfunc, 1000);

}

function myStopCountdown(myVar) {
    clearInterval(myVar);
}


//function myFunction() {
//    document.getElementById("holes").style="display:block";
//  }
//  
// 
//  setInterval(myFunction, 3000);
  