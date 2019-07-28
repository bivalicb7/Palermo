/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

import com.palermo.palermo.gameModel.GameUserInTable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Los_e
 */
public class TableState {

    // Key: String user websocket sessiodin  Value: GameUserInTable
    private Map<String, GameUserInTable> usersintable = new HashMap();
    private String phase;
    private boolean killbyrussianroulette;
    private String numofplayers;
    private String gameid;

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

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public boolean isKillbyrussianroulette() {
        return killbyrussianroulette;
    }

    public void setKillbyrussianroulette(boolean killbyrussianroulette) {
        this.killbyrussianroulette = killbyrussianroulette;
    } 

    public TableState() {
    }

    public Map<String, GameUserInTable> getUsersintable() {
        return usersintable;
    }

    public void setUsersintable(Map<String, GameUserInTable> usersintable) {
        this.usersintable = usersintable;
    }

}
