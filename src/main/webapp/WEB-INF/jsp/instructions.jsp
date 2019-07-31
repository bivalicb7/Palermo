<%-- 
    Document   : instructions
    Created on : 31 Ιουλ 2019, 8:18:18 μμ
    Author     : djbil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>modal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="modal.css">
        
        
        </head>

<body>
<button id="myBtn">Instructions</button>

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

    </body>
    <script src="modal.js"></script>
</html>