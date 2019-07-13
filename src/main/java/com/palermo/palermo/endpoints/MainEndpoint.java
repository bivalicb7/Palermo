/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.endpoints;

import java.io.IOException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Los_e
 */
@ServerEndpoint(value = "/game")
public class MainEndpoint {
    @Autowired
     SessionRegistry registry = SessionRegistry.getInstance();
    
    //Sti sunartisi auti mpainei otan apo o browser anoigei to JS arxeio kai dimiourgeitai to neo Websocket
    
    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) throws IOException {
        System.out.println("In HEREEEEEEEEEEEE");
        registry.add(session);
        session.getBasicRemote().sendText("People in chat " + registry.getAll().size());
    }
 
    
}
