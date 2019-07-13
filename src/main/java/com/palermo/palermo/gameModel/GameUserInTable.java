/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.entities.User;

/**
 *
 * @author Los_e
 */
public class GameUserInTable {

    private User user;
    private String usersessionid;

    public GameUserInTable() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
