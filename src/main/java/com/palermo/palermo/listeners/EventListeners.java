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

            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), Integer.parseInt(sha.getNativeHeader("userid").get(0)), sha.getSessionId());

            //TO BE DELETED
            //Add 5 more dummy players to the table to  go on with development
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), 5, "a");
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), 6, "b");
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), 9, "c");
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), 10, "d");
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), 7, "e");
            
            
//            gamemain.getGametables().get(Integer.parseInt(sha.getNativeHeader("tableid").get(0))).assignRoles();
            //TO BE DELETED
            tableStateController.updateTableState(Integer.parseInt(sha.getNativeHeader("tableid").get(0)));
            tablesInLobbyController.updateTablesInLobby();

        }

        if (sha.getDestination().matches("/topic/tablesinlobby")) {
            tablesInLobbyController.updateTablesInLobby();
        }

    }

//    @EventListener
//    public void handleContextStart(SessionConnectedEvent sce) {
//        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sce.getMessage());
//    }
    @EventListener
    public void handleContextStart(SessionDisconnectEvent sde) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sde.getMessage());

        int tableid = gamemain.getUsersintablesmapping().get(sha.getSessionId());
        gamemain.removeUserFromTable(sha.getSessionId());

        //TO BE DELETED
        //Remove all 5 more dummy players to closetable 
        gamemain.removeUserFromTable("1");
        gamemain.removeUserFromTable("2");
        gamemain.removeUserFromTable("3");
        gamemain.removeUserFromTable("4");
        gamemain.removeUserFromTable("5");

        //TO BE DELETED
        //Check if table still exists and users are still connected
        
        if (gamemain.getGametables().get(tableid) != null) {
            tableStateController.updateTableState(tableid);
        }
    }

}
