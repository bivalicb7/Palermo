/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

/**
 *
 * @author Los_e
 */
public class Vote {
    
    private String voter;
    private String personvotedout;

    public Vote() {

    }

    public String getVoter() {
        return voter;
    }

    public String getPersonvotedout() {
        return personvotedout;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public void setPersonvotedout(String personvotedout) {
        this.personvotedout = personvotedout;
    }
    
    
}
