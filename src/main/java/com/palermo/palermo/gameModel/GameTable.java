/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

}
