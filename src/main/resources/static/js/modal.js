window.addEventListener('DOMContentLoaded', function () {

    instructionsModal();
    payPalModal();

});

function instructionsModal() {
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal 
    btn.onclick = function () {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function payPalModal() {
    var modal = document.getElementById("payPalModal");

    // Get the button that opens the modal
    var btn = document.getElementById("payPalDonate");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[1];

    // When the user clicks the button, open the modal 
    btn.onclick = function () {
        modal.style.display = "flex";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    document.querySelector("#donatebutton").addEventListener("click", function (event) {
        event.preventDefault();

        let sum = document.querySelector("#suminput").value;
        makePayment(sum);


    })
}

function makePayment(sum) {

    let url = `paypal/make/payment?sum=${sum}`;

    fetch(url, {method: 'POST'})
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                window.open(myJson.redirect_url);
            });

//    return this.http.post(this.url + 'paypal/make/payment?sum=' + sum, {})
//    .map((response: Response) => response.json());
}

function completePayment(paymentId, payerId) {

    let url = `paypal/complete/payment?paymentId=${paymentId}&PayerID=${payerId}`;

    fetch(url, {method: 'POST'})
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {
                console.log(myJson);
            });

//    return this.http.post(this.url + 'paypal/make/payment?sum=' + sum, {})
//    .map((response: Response) => response.json());
}