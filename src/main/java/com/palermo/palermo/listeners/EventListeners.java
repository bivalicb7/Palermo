/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.listeners;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 *
 * @author Los_e
 */
@Component
public class EventListeners {
    
        @EventListener
    public void handleContextStart(SessionSubscribeEvent sse) {
        System.out.println("Handling SessionSubscribeEvent event." + sse.getMessage().getHeaders().toString());
    }
    
        @EventListener
    public void handleContextStart(SessionConnectedEvent sce) {
        System.out.println("Handling SessionConnectedEvent event." + sce.getMessage().getHeaders().toString());
    }
}
