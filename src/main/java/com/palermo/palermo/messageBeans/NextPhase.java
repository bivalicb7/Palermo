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
public class NextPhase {
    private TableState tablestate;
    private String typeofphase;


    public NextPhase(String typeofphase, TableState tablestate) {
        this.tablestate = tablestate;
        this.typeofphase = typeofphase;
    }

    public String getTypeofphase() {
        return typeofphase;
    }

    public void setTypeofphase(String typeofphase) {
        this.typeofphase = typeofphase;
    }

    public TableState getTablestate() {
        return tablestate;
    }

    public void setTablestate(TableState tablestate) {
        this.tablestate = tablestate;
    }
    
    
}
