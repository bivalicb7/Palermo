/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

import com.palermo.palermo.gameModel.GameTable;
import com.palermo.palermo.gameModel.GameUserInTable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Los_e
 */
public class TablesInLobby {

    // Key: Inetger tableid  Value: Gametable
private Map<Integer, GameTable> gametablesinlobby = new HashMap();


    public TablesInLobby() {
    }

    public Map<Integer, GameTable> getGametablesinlobby() {
        return gametablesinlobby;
    }

    public void setGametablesinlobby(Map<Integer, GameTable> gametablesinlobby) {
        this.gametablesinlobby = gametablesinlobby;
    }



}
