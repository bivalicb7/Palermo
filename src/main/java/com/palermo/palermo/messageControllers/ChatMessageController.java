/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.messageBeans.ChatMessageToClient;
import com.palermo.palermo.messageBeans.ChatMessageFromClient;
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
public class ChatMessageController {
    @Autowired
    SimpMessagingTemplate smp;
    


    @MessageMapping("/chatsending/{variable}")
//    @SendTo("/topic/greetings/1")
    public void greeting(ChatMessageFromClient message, @DestinationVariable String variable) throws Exception {
//        Thread.sleep(1000); // simulated delay
        smp.convertAndSend("/topic/chatincoming/"+variable, new ChatMessageToClient(HtmlUtils.htmlEscape(message.getName())+": "+ HtmlUtils.htmlEscape(message.getMessage())));
    }
    
    @MessageMapping("/killer_chatsending/{variable}")
//    @SendTo("/topic/greetings/1")
    public void killerGreeting(ChatMessageFromClient message, @DestinationVariable String variable) throws Exception {
//        Thread.sleep(1000); // simulated delay
        smp.convertAndSend("/topic/killer_chatincoming/"+variable, new ChatMessageToClient(HtmlUtils.htmlEscape(message.getName())+": "+ HtmlUtils.htmlEscape(message.getMessage())));
    }

    //    @MessageMapping("/hello1")
//    @SendTo("/topic/greetings/1")
//    public ChatMessageToClient greeting(ChatMessageFromClient message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new ChatMessageToClient("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }
}
