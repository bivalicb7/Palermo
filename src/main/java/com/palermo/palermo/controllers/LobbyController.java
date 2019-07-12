/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

//import com.palermo.palermo.configurationClasses.WebSocketConfig;
//import com.palermo.palermo.configurationClasses.WebSocketConfig;
import com.palermo.palermo.entities.User;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.EndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.PathMappedEndpoints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author Los_e
 */
@Controller
@RequestMapping(value = "lobby")
public class LobbyController {
//    @Autowired
//     WebSocketConfig wsc;
//    @Autowired
//    private WebMvcStompEndpointRegistry reg;
//    @Autowired PathMappedEndpoints pme;
    
    @RequestMapping(value = "/startgame", method = RequestMethod.GET)
    public String startGame(ModelMap mm
     //    StompEndpointRegistry  registry
    ) {
//        wsc.registerStompEndpoints(ser, "liana");
//    WebMvcStompEndpointRegistry ser = new WebMvcStompEndpointRegistry(WebSocketHandler webSocketHandler, WebSocketTransportRegistration transportRegistration, TaskScheduler defaultSockJsTaskScheduler);
//           registry.addEndpoint("/liana").withSockJS();
//        Collection<String> allPaths = pme.getAllPaths();

//PathMappedEndpoints pme = new PathMappedEndpoints("/palermo", () -> {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//});
        mm.addAttribute("user", new User());
        return "game";
    }
}
