/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#moon').animate({'top': '40px', 'opacity': 1}, 7000);
    $('.open-button').animate({'top': '40%', 'opacity': 1}, 9000);
    $('#title').animate({'top': '170px', 'opacity': 1}, 8000);

    document.querySelector("#change").addEventListener("animationend", function () {
        document.querySelector(".container").style.display = "block";

    });


});
//function openForm() {
//    document.querySelector(".container").style.display = "block";
//}
//
//function closeForm() {
//    document.querySelector(".container").style.display = "none";
//}

