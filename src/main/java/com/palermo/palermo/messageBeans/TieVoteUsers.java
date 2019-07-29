/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

import java.util.ArrayList;

/**
 *
 * @author Los_e
 */
public class TieVoteUsers {
    ArrayList<String> tievoteuserslist;

    public TieVoteUsers(ArrayList<String> list) {
        this.tievoteuserslist = new ArrayList();
        tievoteuserslist.addAll(list);
    }

    public ArrayList<String> getTievoteuserslist() {
        return tievoteuserslist;
    }

    public void setTievoteuserslist(ArrayList<String> tievoteuserslist) {
        this.tievoteuserslist = tievoteuserslist;
    }
    
    
}
