/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Los_e
 */
public class EndOfGame {
    
    private String roleofwinners;
    
    //Key websoccetid value ingamerole
    private Map<String, String> winners;
    private boolean tie;

    public EndOfGame() {
        this.winners = new HashMap();
    }

    public String getRoleofwinners() {
        return roleofwinners;
    }

    public void setRoleofwinners(String roleofwinners) {
        this.roleofwinners = roleofwinners;
    }

    public Map<String, String> getWinners() {
        return winners;
    }

    public void setWinners(Map<String, String> winners) {
        this.winners = winners;
    }

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }
    
    
}
