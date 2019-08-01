//window.addEventListener("DOMContentLoaded", function () {
//    // setTimeout(showPhase("day"), 0);
//    // setTimeout(clearPhase(), 0);
//    setTimeout(showPhase("night"), 0);
//})

function showPhase(typeofphase) {
    let div = document.createElement("div");
    div.classList.add("phasecontainer");
    let imgpic = document.createElement("img");
    if (typeofphase == "day") {
        imgpic.setAttribute("src", "images/sun.png");
    }
    if (typeofphase == "night") {
        imgpic.setAttribute("src", "images/moonphase.png");
    }
    imgpic.classList.add("imgpic");

    let imgtext = document.createElement("img");
    if (typeofphase == "day") {
        imgtext.setAttribute("src", "images/newday.png");
    }
    if (typeofphase == "night") {
        imgtext.setAttribute("src", "images/nighthasfallen.png");
    }
    imgtext.classList.add("imgtext");

    div.appendChild(imgpic);
    div.appendChild(imgtext);

    document.querySelector("#gamecontainer").appendChild(div);

    document.querySelector(".imgpic").addEventListener("animationstart", function () {
        setTimeout(function () {
            document.querySelector(".imgtext").style.display = "block";
            document.querySelector(".imgtext").classList.add("animated");
            document.querySelector(".imgtext").classList.add("bounceInUp");
        }, 1000);
    });

    document.querySelector(".imgtext").addEventListener("animationend", function (event) {
        if(event.animationName == "bounceInUp") {
        setTimeout(function () {
            document.querySelector(".imgtext").classList.add("bounceOutUp");
        }, 2000);
    }

    if(event.animationName == "bounceOutUp") {
        clearPhase();
    }
    });
}

function clearPhase() {
    document.querySelector("#gamecontainer").removeChild(document.querySelector(".phasecontainer"));
}