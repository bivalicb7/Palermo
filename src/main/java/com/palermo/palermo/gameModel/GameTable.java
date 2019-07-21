/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Los_e
 */
public class GameTable {

    private int gametableid;

    // Key: String user websocket sessiodin  Value: GameUserInTable
    private Map<String, GameUserInTable> usersintable = new HashMap();

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
            entry.getValue().setIngamerole(roleslist.get(index-1));
            roleslist.remove(index-1);

            System.out.println(entry.getKey() + " " + entry.getValue().getIngamerole());

        }

    }

}
