/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.gameModel.GameMain;
import com.palermo.palermo.messageBeans.TableState;
import com.palermo.palermo.messageBeans.TablesInLobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Los_e
 */
@Component
public class TablesInLobbyController {

    @Autowired
    SimpMessagingTemplate smp;
    @Autowired
    GameMain gamemain;

    public void updateTablesInLobby() {
        TablesInLobby tablesInLobby = new TablesInLobby();
        
//        tablestate.setUsersintable(gamemain.getGametables().get(tableid).getUsersintable());
        tablesInLobby.setGametablesinlobby(gamemain.getGametables());
        smp.convertAndSend("/topic/tablesinlobby",   tablesInLobby);
    }
}
