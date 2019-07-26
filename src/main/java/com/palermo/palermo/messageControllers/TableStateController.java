/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.gameModel.GameMain;
import com.palermo.palermo.messageBeans.NextPhase;
import com.palermo.palermo.messageBeans.Roles;
import com.palermo.palermo.messageBeans.TableState;
import com.palermo.palermo.messageBeans.TieVoteUsers;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Los_e
 */
@Component
public class TableStateController {

    @Autowired
    SimpMessagingTemplate smp;
    @Autowired
    GameMain gamemain;

    public void updateTableState(int tableid) {
        TableState tablestate = gamemain.returnTableState(tableid);
        smp.convertAndSend("/topic/tablestate/" + tableid, tablestate);
    }

    public void sendRoles(int tableid, Roles roles) {
        smp.convertAndSend("/topic/roles/" + tableid, roles);
    }

    public void triggerNextPhase(int tableid, NextPhase nextphase) {
        smp.convertAndSend("/topic/nextphase/" + tableid, nextphase);
    }

    public void votingTieHandler(int tableid, TieVoteUsers usersintie) {
        smp.convertAndSend("/topic/tievote/" + tableid, usersintie);
    }
}
