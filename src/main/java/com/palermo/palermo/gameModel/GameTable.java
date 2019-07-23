/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.messageBeans.Roles;
import com.palermo.palermo.messageBeans.Vote;
import com.palermo.palermo.messageControllers.TableStateController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Los_e
 */
public class GameTable {

    private int gametableid;

    // Key: String user websocket sessiodin  Value: GameUserInTable
    private Map<String, GameUserInTable> usersintable = new HashMap();

    private ArrayList<String> usersthatgotvotes = new ArrayList();

    public GameTable() {
    }

    public int getGametableid() {
        return gametableid;
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

    public void assignRoles() {
        ArrayList<String> roleslist = new ArrayList();
        roleslist.add("nothiddenkiller");
        roleslist.add("hiddenkiller");
        roleslist.add("spy");
        roleslist.add("civilian");
        roleslist.add("civilian");
        roleslist.add("civilian");

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

    public String returnPersonVotedOut() {
        String personvotedout = null;
        int highestoccurancesofar = 0;

        for (String person : usersthatgotvotes) {
            int frequency = IterableUtils.frequency(usersthatgotvotes, person);
            if (frequency > highestoccurancesofar) {
                highestoccurancesofar = frequency;
                personvotedout = person;
            }
        }

        //reset votes for next round
        usersthatgotvotes.clear();

        return personvotedout;
    }

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

        //reset votes for next round
        usersthatgotvotes.clear();

        return personkilled;
    }

    public boolean checkKillersCongruence() {

        int voteslength = usersthatgotvotes.size();

        int frequency = IterableUtils.frequency(usersthatgotvotes, usersthatgotvotes.get(0));

        if (frequency == voteslength) {
            return true;
        } else {
            return false;
        }

    }

}
