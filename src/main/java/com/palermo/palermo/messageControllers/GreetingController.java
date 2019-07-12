/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.messageBeans.Greeting;
import com.palermo.palermo.messageBeans.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Los_e
 */
@Controller
public class GreetingController {
    @Autowired
    SimpMessagingTemplate smp;
    

//    @MessageMapping("/hello1")
//    @SendTo("/topic/greetings/1")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
    @MessageMapping("/hello/{variable}")
//    @SendTo("/topic/greetings/1")
    public void greeting(HelloMessage message, @DestinationVariable String variable) throws Exception {
        Thread.sleep(1000); // simulated delay
        smp.convertAndSend(
                "/topic/greetings/"+variable,
                new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!" + variable));
    }

}
