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
import org.springframework.stereotype.Component;

/**
 *
 * @author Los_e
 */
@Component
public class GameMain {

    private static int nexttableid;
    private Map<Integer, GameTable> gametables = new HashMap();

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


    
    public int returnNextTableId() {
        GameMain.nexttableid = nexttableid +1;
        return GameMain.nexttableid;
    }
    
    
    
    
}
