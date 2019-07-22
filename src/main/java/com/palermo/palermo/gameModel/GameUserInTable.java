/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userprofileview;

/**
 *
 * @author Los_e
 */
public class GameUserInTable {

    private Userprofileview userprofileview;
    private String usersessionid;
    private String ingamerole;
    private boolean ready;
    private boolean dead;

    public GameUserInTable() {
        this.dead = false;
        this.ready = false;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getUsersessionid() {
        return usersessionid;
    }

    public void setUsersessionid(String usersessionid) {
        this.usersessionid = usersessionid;
    }

    public Userprofileview getUserprofileview() {
        return userprofileview;
    }

    public void setUserprofileview(Userprofileview userprofileview) {
        this.userprofileview = userprofileview;
    }

    public String getIngamerole() {
        return ingamerole;
    }

    public void setIngamerole(String ingamerole) {
        this.ingamerole = ingamerole;
    }

}
