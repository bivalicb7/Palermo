/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Los_e
 */
public class GameTable {

    private int gametableid;
    private Set<GameUserInTable> usersintable = new HashSet();

    public GameTable() {
    }

    public int getGametableid() {
        return gametableid;
    }

    public void setGametableid(int gametableid) {
        this.gametableid = gametableid;
    }

    public Set<GameUserInTable> getUsersintable() {
        return usersintable;
    }

    public void setUsersintable(Set<GameUserInTable> usersintable) {
        this.usersintable = usersintable;
    }

}
