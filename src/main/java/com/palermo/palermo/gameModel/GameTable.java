/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.entities.Game;
import com.palermo.palermo.messageBeans.EndOfGame;
import com.palermo.palermo.messageBeans.Roles;
import com.palermo.palermo.messageBeans.Vote;
import com.palermo.palermo.messageControllers.TableStateController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Los_e
 */
public class GameTable {

    private int gametableid;
    private String phase;
    private boolean intiebreakmode;
    private String numofplayers;
    private boolean gamefinished;
    private boolean gamestarted;
    private String gameid;
    private String startdatetime;
    private String enddatetime;

    // Key: String user websocket sessiodin  Value: GameUserInTable
    private Map<String, GameUserInTable> usersintable = new HashMap();

    //This could change to a MAP in order to map users' votes. This will change implementation of methods using it 
    private ArrayList<String> usersthatgotvotes = new ArrayList();

    public GameTable() {
        this.phase = "daykill";
        this.intiebreakmode = false;
        this.gamefinished = false;
        this.gamestarted = false;
        this.startdatetime = LocalDateTime.now().toString();
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(String startdate) {
        this.startdatetime = startdate;
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(String enddate) {
        this.enddatetime = enddate;
    }

    public boolean isGamestarted() {
        return gamestarted;
    }

    public void setGamestarted(boolean gamestarted) {
        this.gamestarted = gamestarted;
    }

    public boolean isGamefinished() {
        return gamefinished;
    }

    public void setGamefinished(boolean gamefinished) {
        this.gamefinished = gamefinished;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getNumofplayers() {
        return numofplayers;
    }

    public void setNumofplayers(String numofplayers) {
        this.numofplayers = numofplayers;
    }

    public boolean isIntiebreakmode() {
        return intiebreakmode;
    }

    public void setIntiebreakmode(boolean intiebreakmode) {
        this.intiebreakmode = intiebreakmode;
    }

    public int getGametableid() {
        return gametableid;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public ArrayList<String> getUsersthatgotvotes() {
        return usersthatgotvotes;
    }

    public void setUsersthatgotvotes(ArrayList<String> usersthatgotvotes) {
        this.usersthatgotvotes = usersthatgotvotes;
    }

    public void setGametableid(int gametableid) {
        this.gametableid = gametableid;
    }

    public Map<String, GameUserInTable> getUsersintable() {
        return usersintable;
    }

    public void setUsersintable(Map<String, GameUserInTable> usersintable) {
        this.usersintable = usersintable;
    }

    //TO BE DELETED
//    public void assignFakeRoles() {
//        ArrayList<String> roleslist = new ArrayList();
//        roleslist.add("nothiddenkiller");
//        roleslist.add("hiddenkiller");
//        
//        int index = 2;
//        for (Map.Entry<String, GameUserInTable> entry : usersintable.entrySet()) {
//
//            if (entry.getKey().equals("tmpa")) {
//                entry.getValue().setIngamerole("civilian");
//            } else if (entry.getKey().equals("tmpb")) {
//                entry.getValue().setIngamerole("civilian");
//            } else if (entry.getKey().equals("tmpc")) {
//                entry.getValue().setIngamerole("civilian");
//            } else if (entry.getKey().equals("tmpd")) {
//                entry.getValue().setIngamerole("spy");
//            } else {
//                entry.getValue().setIngamerole(roleslist.get(index - 1));
//                index = index-1;
//            }
//
//        }
//
//    }
    //TO BE DELETED
    public void assignRoles() {
        ArrayList<String> roleslist = new ArrayList();
        roleslist.add("nothiddenkiller");
        roleslist.add("hiddenkiller");
        roleslist.add("spy");

        for (int i = 0; i < (Integer.parseInt(numofplayers) - 3); i++) {
            roleslist.add("civilian");
        }

        for (Map.Entry<String, GameUserInTable> entry : usersintable.entrySet()) {

            //Get random index
            Random r = new Random();
            int index = r.nextInt((roleslist.size() - 1) + 1) + 1;

            //set role and remove role from list
            entry.getValue().setIngamerole(roleslist.get(index - 1));
            System.out.println(entry.getKey() + " = " + roleslist.get(index - 1));

            roleslist.remove(index - 1);
        }

    }

    public boolean checkIfAllUsersReady() {
        return IterableUtils.matchesAll(usersintable.values(), user -> (user.isReady()));
    }

    public Roles returnRolesObject() {
        return new Roles(usersintable);
    }

    public boolean checkIfAllNonDeadUsersHaveVoted() {
        boolean checkresult = IterableUtils.matchesAll(IterableUtils.filteredIterable(usersintable.values(), user -> !user.isDead()), user -> (user.isHasvoted()));

        //If everyone has voted then automatically reset their hasvoted field
        if (checkresult) {
            IterableUtils.forEach(IterableUtils.filteredIterable(usersintable.values(), user -> (user.isHasvoted())), user -> user.setHasvoted(false));
        }

        return checkresult;
    }

    public boolean checkIfAllNonDeadKillersHaveVoted() {
        boolean checkresult = IterableUtils.matchesAll(
                IterableUtils.filteredIterable(
                        IterableUtils.filteredIterable(
                                //                                IterableUtils.filteredIterable(
                                usersintable.values(), user -> user.getIngamerole().endsWith("hiddenkiller")
                        //                                ), user -> !user.getIngamerole().equals("nothiddenkiller")
                        ), user -> !user.isDead()
                ), user -> (user.isHasvoted()));

        //If everyone has voted then automatically reset their hasvoted field
        if (checkresult) {
            IterableUtils.forEach(IterableUtils.filteredIterable(usersintable.values(), user -> (user.isHasvoted())), user -> user.setHasvoted(false));
        }

        return checkresult;
    }

    public void openVote(Vote vote) {
        usersintable.get(vote.getVoter()).setHasvoted(true);
        usersthatgotvotes.add(vote.getPersonvotedout());

        System.out.println("Voter " + vote.getVoter() + " Voted for " + vote.getPersonvotedout());
        System.out.println("Votes at the moment " + usersthatgotvotes.toString());
    }

//    public String returnPersonOrPersonsVotedOut() {
//        String personvotedout = null;
//        int highestoccurancesofar = 0;
//
//        for (String person : usersthatgotvotes) {
//            int frequency = IterableUtils.frequency(usersthatgotvotes, person);
//            if (frequency > highestoccurancesofar) {
//                highestoccurancesofar = frequency;
//                personvotedout = person;
//            }
//        }
//
//        //reset votes list for next voting
//        usersthatgotvotes.clear();
//
//        return personvotedout;
//    }
    public String returnPersonKilled() {
        String personkilled = null;
        int highestoccurancesofar = 0;

        for (String person : usersthatgotvotes) {
            int frequency = IterableUtils.frequency(usersthatgotvotes, person);
            if (frequency > highestoccurancesofar) {
                highestoccurancesofar = frequency;
                personkilled = person;
            }
        }

        //reset votes for next voting
        usersthatgotvotes.clear();

        return personkilled;
    }

    public ArrayList<String> returnPersonOrPersonsVotedOut() {
        int highestoccurancesofar = 0;
        ArrayList<String> personswithvoteslist = new ArrayList();

        for (String person : usersthatgotvotes) {
            int frequency = IterableUtils.frequency(usersthatgotvotes, person);
            if (frequency > highestoccurancesofar) {
                highestoccurancesofar = frequency;
            }
        }

        for (String person : usersthatgotvotes) {
            int frequency = IterableUtils.frequency(usersthatgotvotes, person);
            if (frequency == highestoccurancesofar) {
                if (!personswithvoteslist.contains(person)) {
                    personswithvoteslist.add(person);
                }
            }
        }

        //reset votes for next voting
        usersthatgotvotes.clear();

        return personswithvoteslist;

    }

    public void russianRoulette() {

        Random r = new Random();
        int index = r.nextInt(((int) IterableUtils.countMatches(usersintable.values(), user -> !user.isDead()) - 1) + 1) + 1;

        IterableUtils.get(IterableUtils.filteredIterable(usersintable.values(), user -> !user.isDead()), index - 1).setDead(true);

    }

    public boolean checkKillersCongruence() {

        //For two killers. If there were more killers all killers should agree with this implementation
        int voteslength = usersthatgotvotes.size();

        int frequency = IterableUtils.frequency(usersthatgotvotes, usersthatgotvotes.get(0));

        if (frequency == voteslength) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkIfWinner() {
        int usersalive = IterableUtils.size(IterableUtils.filteredIterable(usersintable.values(), user -> !user.isDead()));

        int numofkillers = IterableUtils.size(IterableUtils.filteredIterable(usersintable.values(), user -> (!user.isDead() && user.getIngamerole().endsWith("hiddenkiller"))));
        int numofciviliansspy = IterableUtils.size(IterableUtils.filteredIterable(usersintable.values(), user -> (!user.isDead() && (user.getIngamerole().equals("civilian") || user.getIngamerole().equals("spy")))));

        System.out.println("Num of killers " + numofkillers + " Num of civilspies " + numofciviliansspy + "Users alive " + usersalive);

        //If both killers have diesd  or all civilians have died game ends
        if (numofkillers == 0 || numofciviliansspy == 0) {
            return true;
        } else if (usersalive == 2) {

            if (phase.equals("daykill")) {

                // here we've got a tie
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public EndOfGame returnEndOfGame() {

        EndOfGame endofgame = new EndOfGame();
        int numofkillers = IterableUtils.size(IterableUtils.filteredIterable(usersintable.values(), user -> (!user.isDead() && user.getIngamerole().endsWith("hiddenkiller"))));
        int numofciviliansspy = IterableUtils.size(IterableUtils.filteredIterable(usersintable.values(), user -> (!user.isDead() && (user.getIngamerole().equals("civilian") || user.getIngamerole().equals("spy")))));

//        System.out.println("Num of killers " + numofkillers + " Num of civilspies " + numofciviliansspy + "Users alive " + usersalive);
        //If bothkillers have diesd game ends
        if (numofkillers == 0) {

            endofgame.setRoleofwinners("civilians");
            System.out.println("Winners: civilians!");

        } else if (numofciviliansspy == 0) {

            endofgame.setRoleofwinners("killers");
            System.out.println("Winners: killers!");

        } else if (numofkillers == numofciviliansspy) {

            endofgame.setRoleofwinners("tie");
            System.out.println("Tie!");
        }

        //Get users alive and their roles
        for (Map.Entry<String, GameUserInTable> entry : usersintable.entrySet()) {

            if (!entry.getValue().isDead()) {
                endofgame.getWinners().put(entry.getKey(), entry.getValue().getIngamerole());
                System.out.println("In list:" + entry.getKey() + " " + entry.getValue().getIngamerole());
            }
        }

        gamefinished = true;
        gamestarted = false;
        return endofgame;
    }

    public void resetTableForNewGame() {

        if (gamefinished) {
            this.phase = "daykill";
            this.intiebreakmode = false;
            this.gamefinished = false;
            this.gamestarted = false;
            this.startdatetime = LocalDateTime.now().toString();

            for (Map.Entry<String, GameUserInTable> entry : usersintable.entrySet()) {

                entry.getValue().setDead(false);
                entry.getValue().setHasvoted(false);
                entry.getValue().setIngamerole(null);
                entry.getValue().setReady(false);

            }
        }

    }

}
