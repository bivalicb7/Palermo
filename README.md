# Palermo #

**About the game**

Palermo is an interactive web application based on the famous game "Night falls on Palermo", a simple game with the purpose of surviving throughout consequent cycles of voting and murders. It is required to create a personal account and sign up to use the application. The game is set on a table with finite number of seats (within a range from 6 to 12), defined at the time of the table’s creation.
A player may create a new table or join an existing one in the Lobby area which still has available seats. Once a table's seats have all been claimed, all the users have to click on the Start Game button and the game starts!
 A set of roles is randomly assigned among the players. The roles of the game are:

* one"hidden killer" whose identity is secret
* one"non hidden killer" whose identity is revealed to the "spy" and to the co-killer
* one spy
* the civilians

The killers' purpose is to get all the civilians plus the spy out of the game, while the rest of the players try to sniff out the killers and vote them out first.
There are two phases in the game:

“Day phase”
The players start chatting in the chat area in order to decide on who they will vote out first. When all the players have voted the result is announced and the player who has been voted is out of the game.

“Night phase”
 The killers have the opportunity to secretly chat and vote out (kill) a person of their desire.

 After “night” a new "day" phase takes place with the remaining players voting out another person and so on until the game comes to an end.
The game ends when all the civilians/spy have been voted out and the killers (or at least one of them) remain alive, or when both killers get voted out. When the two surviving players in a "day" phase are a
killer and a civilian/spy the game ends in a tie.

Wins are registered and displayed in users’ profile page.

**The Administrator**

The application supports two types of accounts: plain user account and administrator account. A user cannot sign up as an administrator. The administrator account is provided by the
developers’ team or a simple account can be upgraded to an administrator one by another administrator who holds CRUD permissions on the users of the application. Furthermore, an administrator
may also ban players from a table, forcing them to disconnect.





## Getting Started

In order to run the application on your local machine, you need to download this project and open it using Netbeans IDE with Apache Tomcat installed on your pc. Test the connection to the application's database otherwise the project most likely won't run. In order to log in to the application use one of the following accounts. The password is the same as the username in all the accounts.

liana
vagelis
vasilis
liana1
vag1 (this is an administrator account)
savage
Vaggos
liana5

Registering a new account is more complicated as you will need a private key for the smtp.gmail.com to work on your machine and thus get the activation link. Still you may activate a new account
using the administrator account though.

**How to play the game in dev mode**

In order to play a game in dev mode you either need to serve the project and have other people log in or log in with at least 6 accounts in different browser windows on your machine.

### Prerequisites

What things you need to install the software: 

* Netbeans IDE
* Apache Tomcat

## Built With

* Maven - Dependency Management
* Spring boot
* Spring WebSocket API with SockJS

Third party APIs

* PayPal developer API

## Authors

* **Liana Kotzabasi** - *Initial work* - [kotzabasi](https://github.com/kotzabasi)
* **Vasilis Efthymiou** - *Initial work* - [vasilissavage](https://github.com/vasilissavage)
* **Evangelos Kalabokis** - *Initial work* - [EvanKal](https://github.com/EvanKal)

