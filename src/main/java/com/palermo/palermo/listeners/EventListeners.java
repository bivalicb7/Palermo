/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.listeners;

import com.palermo.palermo.gameModel.GameMain;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

/**
 *
 * @author Los_e
 */
@Component
public class EventListeners {

    @Autowired
    GameMain gamemain;

    @EventListener
    public void handleContextStart(SessionSubscribeEvent sse) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sse.getMessage());

        if (sha.getDestination().matches("/topic/tablestate/\\d*")) {
//            
//                    NativeMessageHeaderAccessor nmha = (NativeMessageHeaderAccessor)sha;
//        Map<String,List<String>> map = nmha.toNativeHeaderMap();
            
            gamemain.addUserToTable(Integer.parseInt(sha.getNativeHeader("tableid").get(0)), Integer.parseInt(sha.getNativeHeader("userid").get(0)), sha.getSessionId());
            System.out.println("STOMP HA " + sha.getNativeHeader("userid").get(0) + " Destination " + sha.getDestination() + " Session id " + sha.getSessionId() + " Host  " + sha.getHost());
        System.out.println("Handling SessionSubscribeEvent event." + sse.getMessage().getHeaders().toString());
        }
    }

    @EventListener
    public void handleContextStart(SessionConnectedEvent sce) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sce.getMessage());

//        System.out.println("Handling SessionConnectedEvent event." + sce.getMessage().getHeaders().toString());
    }

    @EventListener
    public void handleContextStart(SessionDisconnectEvent sde) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(sde.getMessage());
        
        gamemain.removeUserFromTable(sha.getSessionId());
    }

}
