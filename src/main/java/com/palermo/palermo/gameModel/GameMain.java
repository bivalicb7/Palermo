/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.entities.Game;
import com.palermo.palermo.entities.Userprofileview;
import com.palermo.palermo.entities.Usersingame;
import com.palermo.palermo.messageBeans.EndOfGame;
import com.palermo.palermo.messageBeans.NextPhase;
import com.palermo.palermo.messageBeans.TableState;
import com.palermo.palermo.messageBeans.TieVoteUsers;
import com.palermo.palermo.messageBeans.Vote;
import com.palermo.palermo.messageControllers.TableStateController;
import com.palermo.palermo.messageControllers.TablesInLobbyController;
import com.palermo.palermo.services.GameService;
import com.palermo.palermo.services.UserProfileViewService;
import com.palermo.palermo.services.UserService;
import com.palermo.palermo.services.UsersInGameService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Los_e
 */
@Component
public class GameMain {

    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;
    @Autowired
    UsersInGameService usersIngameService;
    @Autowired
    UserProfileViewService userProfileViewService;
    @Autowired
    TablesInLobbyController tablesInLobbyController;
    @Autowired
    TableStateController tableStateController;

    private static int nexttableid;

    // Key: Inetger tableid  Value: Gametable
    private Map<Integer, GameTable> gametables = new HashMap();
    // Key: String user websocket sessionid  Value: Integer tableid
    private Map<String, Integer> usersintablesmapping = new HashMap();

    public GameMain() {
    }

    public static int getNexttableid() {
        return nexttableid;
    }

    public static void setNexttableid(int nexttableid) {
        GameMain.nexttableid = nexttableid;
    }

    public Map<Integer, GameTable> getGametables() {
        return gametables;
    }

    public void setGametables(Map<Integer, GameTable> gametables) {
        this.gametables = gametables;
    }

    public Map<String, Integer> getUsersintablesmapping() {
        return usersintablesmapping;
    }

    public void setUsersintablesmapping(Map<String, Integer> usersintablesmapping) {
        this.usersintablesmapping = usersintablesmapping;
    }

    public int returnNextTableId() {
        GameMain.nexttableid = nexttableid + 1;
        return GameMain.nexttableid;
    }

    public String returnGameId() {

        String gameid = UUID.randomUUID().toString();
        System.out.println("New game id " + gameid);
        return gameid;
    }

    public TableState returnTableState(int tableid) {
        TableState tablestate = new TableState();

        tablestate.setKillbyrussianroulette(gametables.get(tableid).isIntiebreakmode());
        tablestate.setPhase(gametables.get(tableid).getPhase());
        tablestate.setNumofplayers(gametables.get(tableid).getNumofplayers());
        tablestate.setGameid(gametables.get(tableid).getGameid());
        tablestate.setUsersintable(gametables.get(tableid).getUsersintable());

        return tablestate;
    }

    public void addUserToTable(int tableid, int userid, String sessionid) {

        GameTable table = gametables.get(tableid);
        GameUserInTable newuserintable = new GameUserInTable();

        Userprofileview newuserprofileintable = userProfileViewService.getUserProfileViewById(userid);
//        User newuseruserintable = userService.getUserById(userid);
        newuserintable.setUserprofileview(newuserprofileintable);
        newuserintable.setUsersessionid(sessionid);
//        newuserintable.setUsername(newuseruserintable.getUsername());
//        newuserintable.setFirstname(newuserprofileintable.getFirstname());
//        newuserintable.setLastname(newuserprofileintable.getLastname());
//        newuserintable.setProfileimagebase64(newuserprofileintable.getProfileimagebase64());
        table.getUsersintable().put(sessionid, newuserintable);

        //Also add usersessionid to usersintablesmapping for quick removal
        //This is because i can't get the tableid as a header in the Disconnect event listener
        usersintablesmapping.put(sessionid, tableid);
        System.out.println(usersintablesmapping.toString());

    }

    public void removeUserFromTable(String sessionid) {
        int tableid = usersintablesmapping.get(sessionid);
        GameTable table = gametables.get(tableid);

        //Check if table still exists and users are still connected and if a game was on handle what happens depending on user's dead status
        if (getGametables().get(tableid) != null) {

            //check if the game has started (by checking their ready status)
            //This check is not efficient enough since it may needlessly reset the table even for fewer users than the number of seats if they somehow enable the "start game" button
            if (table.checkIfAllUsersReady()) {
                //Check if user is dead. If the user is not dead and has left the game then the game must end
                if (!table.getUsersintable().get(sessionid).isDead()) {

                    table.getUsersintable().remove(sessionid);
                    //Also remove from the usersintablesmapping
                    usersintablesmapping.remove(sessionid);

                    table.setGamefinished(true);
                    resetTableForNewGame(tableid);

                } else {

                    //If the game is running don't update table just yet so that it doesn't mess with the flow.
                    //Let the update in the state happen after a user has died
                    //But also update the dead users list on client side so that it can detect the new dead user
                    table.getUsersintable().remove(sessionid);
                    //Also remove from the usersintablesmapping
                    usersintablesmapping.remove(sessionid);
                    tableStateController.deadUserLefttheTable(tableid, sessionid);
                }

            } else {
                table.getUsersintable().remove(sessionid);
                //Also remove from the usersintablesmapping
                usersintablesmapping.remove(sessionid);
                //Update table state to show user gone if there is not a game going on
                tableStateController.updateTableState(tableid);
            }
        }

        //Check if table is empty of users and remove it from the game
        removeTableFromMain(tableid);
        tablesInLobbyController.updateTablesInLobby();

    }

    public void removeTableFromMain(int tableid) {

        if (gametables.get(tableid).getUsersintable().isEmpty()) {
            gametables.remove(tableid);
            tablesInLobbyController.updateTablesInLobby();
        }
    }

    public void setUserReady(int tableid, String sessionid) {

        //This method needs to be here so that tableStateController can be called. CANNOT call tableStateController from inside the table
        GameTable table = gametables.get(tableid);
        table.getUsersintable().get(sessionid).setReady(true);

        //Everytime a user is ready check if all of them are ready in order to start game
        if (table.checkIfAllUsersReady()) {
            table.setGamestarted(true);
            table.assignRoles();
//            table.assignFakeRoles();
//            table.setPhase("daykill");
            tableStateController.sendRoles(tableid, table.returnRolesObject());
        }

    }

    public void setUserDead(int tableid, String sessionid) {
        GameTable table = gametables.get(tableid);
        table.getUsersintable().get(sessionid).setDead(true);
    }

    public void collectVotes(int tableid, Vote vote) {
        GameTable table = gametables.get(tableid);

        if (vote.getPhase().equals("daykill") && table.getPhase().equals("daykill")) {
            //Check if person voting is not dead for extra security
            if (!table.getUsersintable().get(vote.getVoter()).isDead()) {
                table.openVote(vote);

                //if everyone has voted tally the votes and see if there is a tie. else set person dead and move on to next phase
                if (table.checkIfAllNonDeadUsersHaveVoted()) {

                    //Get result from voting
                    ArrayList<String> personvotedout = table.returnPersonOrPersonsVotedOut();

                    //Check if there is a tie or not
                    if (personvotedout.size() == 1) {

                        setUserDead(tableid, personvotedout.get(0));
                        System.out.println("Person voted out " + personvotedout);
                        table.setPhase("nightkill");
                        table.setIntiebreakmode(false);

                        //After a person has been killed check if the game has ended
                        if (!table.checkIfWinner()) {
                            //After user has been killed trigger next phase ---> nighkill
                            tableStateController.triggerNextPhase(tableid, new NextPhase("nightkill", returnTableState(tableid)));
                        } else {
                            tableStateController.updateTableState(tableid);
                            EndOfGame endofgame = table.returnEndOfGame();
                            saveEndedGameInDB(tableid, endofgame);
                            tableStateController.triggerEndOfGame(tableid, endofgame);
                        }

                    } else {
                        //Handle tie for the first time. Users get to vote one more time between the nominees

                        if (!table.isIntiebreakmode()) {
                            table.setIntiebreakmode(true);
                            tableStateController.votingTieHandler(tableid, new TieVoteUsers(personvotedout));
                            System.out.println("We've got a tie between: " + personvotedout.toString());
                        } else {
                            //Activate russian roulette

                            table.russianRoulette();
                            table.setPhase("nightkill");

                            if (!table.checkIfWinner()) {
                                tableStateController.triggerNextPhase(tableid, new NextPhase("nightkill", returnTableState(tableid)));
                            } else {
                                tableStateController.updateTableState(tableid);
                                EndOfGame endofgame = table.returnEndOfGame();
                                saveEndedGameInDB(tableid, endofgame);
                                tableStateController.triggerEndOfGame(tableid, endofgame);
                            }
                            table.setIntiebreakmode(false);

                        }

                    }

                }
            }
        }

        if (vote.getPhase().equals("nightkill") && table.getPhase().equals("nightkill")) {
            //Check if person voting is not dead for extra security
            if (!table.getUsersintable().get(vote.getVoter()).isDead() && (table.getUsersintable().get(vote.getVoter()).getIngamerole().equals("nothiddenkiller") || table.getUsersintable().get(vote.getVoter()).getIngamerole().equals("hiddenkiller"))) {
                table.openVote(vote);

                if (table.checkIfAllNonDeadKillersHaveVoted()) {

                    if (table.checkKillersCongruence()) {
                        //if everyone has voted set person voted out as dead and update tablestate so that user see it
                        String personkilled = table.returnPersonKilled();
                        setUserDead(tableid, personkilled);
                        System.out.println("Person killed " + personkilled);
                        table.setPhase("daykill");
//                        tableStateController.updateTableState(tableid);

                        if (!table.checkIfWinner()) {
                            //After user has been killed trigger next phase ---> daykill
                            tableStateController.triggerNextPhase(tableid, new NextPhase("daykill", returnTableState(tableid)));
                        } else {
                            tableStateController.updateTableState(tableid);
                            EndOfGame endofgame = table.returnEndOfGame();
                            saveEndedGameInDB(tableid, endofgame);
                            tableStateController.triggerEndOfGame(tableid, endofgame);
                        }

                    } else {
                        table.setPhase("daykill");

                        if (!table.checkIfWinner()) {
                            //If killers don't vote for the same person they lose their night kill 
                            tableStateController.triggerNextPhase(tableid, new NextPhase("daykill", returnTableState(tableid)));
                        } else {
                            tableStateController.updateTableState(tableid);
                            EndOfGame endofgame = table.returnEndOfGame();
                            saveEndedGameInDB(tableid, endofgame);
                            tableStateController.triggerEndOfGame(tableid, endofgame);
                        }

                    }

                }
            }
        }

    }

    public void resetTableForNewGame(int tableid) {
        GameTable table = gametables.get(tableid);

        if (table.isGamefinished()) {
            table.setGameid(returnGameId());
            table.resetTableForNewGame();
        }

        tableStateController.updateTableState(tableid);

    }

    public void saveEndedGameInDB(int tableid, EndOfGame endofgame) {
        if (!endofgame.isTie()) {

            GameTable table = gametables.get(tableid);

            Game game = new Game();

            game.setGameid(table.getGameid());
            game.setStartdatetime(table.getStartdatetime());
            game.setEnddatetime(LocalDateTime.now().toString());

            gameService.addGame(game);

            for (Map.Entry<String, String> entry : endofgame.getWinners().entrySet()) {

                Usersingame usersingame = new Usersingame(table.getUsersintable().get(entry.getKey()).getUserprofileview().getUserid(), table.getGameid());
                usersingame.setIngamerole(entry.getValue());
                usersingame.setWon(1);

                usersIngameService.addUserInGame(usersingame);
            }

        }

    }
}
