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
    } else if (typeofphase == "night") {
        imgpic.setAttribute("src", "images/moonphase.png");
    } else if (typeofphase == "russian") {
        let span = document.createElement("span");
        span.setAttribute("id", "countdown");
        span.innerHTML = "5";
        div.appendChild(span);
        
        
    } 

    if (imgpic.hasAttribute("src")) {
        imgpic.classList.add("imgpic");
    }

    let imgtext = document.createElement("img");
    if (typeofphase == "day") {
        imgtext.setAttribute("src", "images/newday.png");
    } else if (typeofphase == "night") {
        imgtext.setAttribute("src", "images/nighthasfallen.png");
    } else if (typeofphase == "russian") {
        imgtext.setAttribute("src", "images/russian.png");
    }
    imgtext.classList.add("imgtext");

    div.appendChild(imgpic);
    div.appendChild(imgtext);

    document.querySelector("#gamecontainer").appendChild(div);

    if (document.querySelector(".imgpic") != null) {
        document.querySelector(".imgpic").addEventListener("animationstart", function () {
            setTimeout(function () {
                document.querySelector(".imgtext").style.display = "block";
                document.querySelector(".imgtext").classList.add("animated");
                document.querySelector(".imgtext").classList.add("bounceInUp");
            }, 1000);
        });

    }

    if (document.querySelector("#countdown") != null) {

    document.querySelector("#countdown").addEventListener("animationstart", function () {
        setTimeout(function () {
            document.querySelector(".imgtext").style.display = "block";
            document.querySelector(".imgtext").classList.add("animated");
            document.querySelector(".imgtext").classList.add("bounceInUp");
            countdownfunc();
        }, 1000);
    });
    }
    
    document.querySelector(".imgtext").addEventListener("animationend", function (event) {
        if (event.animationName == "bounceInUp") {
            setTimeout(function () {
                document.querySelector(".imgtext").classList.add("bounceOutUp");
            }, 10000);
        }

        if (event.animationName == "bounceOutUp") {
            clearPhase();
        }
    });
}

function clearPhase() {
    document.querySelector("#gamecontainer").removeChild(document.querySelector(".phasecontainer"));
}