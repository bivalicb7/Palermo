<%-- 
    Document   : navbar
    Created on : 9 Ιουλ 2019, 12:22:19 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
    <link href="css/modal.css" rel="stylesheet">
    <script src="js/modal.js"></script>
    <link href="css/navbar.css" rel="stylesheet">
</head>
<body>
    <div class="topnav">
        <div id="sitelinks">
            <a href="lobby/home">Lobby</a>
            <!--<a href="updateprofile/createmyprofile">Update my data</a>-->
            <a href="myprofile/showmydata">My profile</a>
            <!--<a href="contact">Contact</a>-->
            <!--<a href="about">About</a>-->
            <c:if test="${sessionScope.loggedinuser.role eq 'admin'}">
                <div id="adminmenu">
                    <a href="allusers/showallusers">All users</a>
                </div>
            </c:if>
<!--            <form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_top" class="topnav">
                <input type="hidden" name="cmd" value="_s-xclick" />
                <input type="hidden" name="hosted_button_id" value="CM6S52A42ZU58" />
                <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donate_SM.gif" border="0" name="submit" title="PayPal - The safer, easier way to pay online!" alt="Donate with PayPal button" />
                <img alt="" border="0" src="https://www.sandbox.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1" />
            </form>-->
            
        </div>

        <div id="midlinks">
            <button id="myBtn">Instructions</button>
            <button id="payPalDonate" title="Donate through PayPal">Donate</button>
        </div>

        <div id="endnav">
            <p>${loggedinuser.username}</p>
            <a href="logout">Log out</a>
        </div>
        <!-- The Modal -->
        <div id="myModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close">&times;</span>
                <pre> <b> Rules: </b>

   The roles of the game are as follows:  
   hidden killer, killer, spy & civilian and are randomly assigned when players click start game and the game starts. 
   The spy is automatically informed  about who the killer is by the application.

         <b>Purpose: </b>

   The purpose of the game is for civilians to identify and expel the killers before they kill them all, 
   and for the killers to kill the spy and the civilians before they are caught. 

         <b>Day in Palermo (game starts)</b>

   Players (having been informed about their role by application) discuss who the killer might be and 
   vote appropriately! 

         <b>Night falls in Palermo:</b>

    During the night the two murderers decide who they will kill (they communicate through personal chat). 
    If the killers do not decide on the next victim in a timely manner, the night goes away without a victim! 

        <b>A new day is rising: </b>

    One of the players is dead! The remaining ones start (via chat) to blame each other! 
    They will have to vote once again!

         <b>Caution:</b>
         
    In the event of a tie, the procedure is repeated once between the tie-breakers and if the result is still tie, 
    the Russian roulette procedure is triggered, in which one of the remaining players 
    (not just the candidates for departure) will be killed by chance! 

         <b>End of game: </b>

    Winners are the live players who have managed to defeat their opponents. Either the killers 
    who have killed everyone else, or the players who have remained in the game after the killers have been  killed. 
    
         <b>Have fun!!!</b></pre>
            </div>
            </div>
            
        <!-- The PayPal Modal -->
        <div id="payPalModal" class="modal">

            <!-- Modal content -->
            <div id="paypal-modal-content">
                <span class="close">&times;</span>
                <div id="paypalformcontainer">
                    <p id="formtext">Choose doanation amount</p>
                    <form method="post" action="paypal/make/payment">
                        <input id="suminput" type="text" name="sum" placeholder="EUR"/>
                        <input id="donatebutton" type="submit" value="Donate"/>
                    </form>
                </div>
            </div>
            </div>

        </div>
</body>
</div>
