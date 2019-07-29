/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.listeners;

import com.palermo.palermo.gameModel.GameMain;
import com.palermo.palermo.messageControllers.TableStateController;
import com.palermo.palermo.messageControllers.TablesInLobbyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 *
 * @author Los_e
 */
@Component
public class EventListeners {

    @Autowired
    GameMain gamemain;
    @Autowired
    TableStateController tableStateController;
    @Autowired
    TablesInLobbyController tablesInLobbyController;

    @EventListener
    public void handleContextStart(SessionSubscribeEvent sse) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sse.getMessage());

        if (sha.getDestination().matches("/topic/tablestate/\\d*")) {

            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), Integer.parseInt(sha.getNativeHeader("userid").get(0)), "tmp"+sha.getSessionId());
            tableStateController.updateTableState(Integer.parseInt(sha.getNativeHeader("tableid").get(0)));
            tablesInLobbyController.updateTablesInLobby();

        }

        if (sha.getDestination().matches("/topic/tablesinlobby")) {
            tablesInLobbyController.updateTablesInLobby();
        }

    }


    @EventListener
    public void handleContextStart(SessionDisconnectEvent sde) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sde.getMessage());
        gamemain.removeUserFromTable("tmp"+sha.getSessionId());
    }

}
