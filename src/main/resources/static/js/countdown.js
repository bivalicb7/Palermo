/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//let seconds = document.getElementById("countdown").innerHTML;

function countdownfunc() {
    let seconds = document.getElementById("countdown").innerHTML;

    setInterval(function(){
    console.log("In countdown");
    seconds--;
//    (seconds == 1) ? document.getElementById("plural").textContent = "" : document.getElementById("plural").textContent = "s";
    document.getElementById("countdown").innerHTML = seconds;
    if (seconds <= 0) clearInterval(countdown);
},1000);
}
//function myFunction() {
//    document.getElementById("holes").style="display:block";
//  }
//  
// 
//  setInterval(myFunction, 3000);
  