/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.addEventListener('DOMContentLoaded', function () {
    var url_string = window.location.href;
    var url = new URL(url_string);
    var paymentId = url.searchParams.get("paymentId");
    var payerId = url.searchParams.get("PayerID");

    completePayment(paymentId, payerId);

});


function completePayment(paymentId, payerId) {

    let url = `paypal/complete/payment?paymentId=${paymentId}&PayerID=${payerId}`;

    fetch(url, {method: 'POST'})
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                console.log(myJson);
            });
}