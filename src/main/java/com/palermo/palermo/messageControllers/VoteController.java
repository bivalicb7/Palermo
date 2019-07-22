/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.gameModel.GameMain;
import com.palermo.palermo.messageBeans.ChatMessageToClient;
import com.palermo.palermo.messageBeans.ChatMessageFromClient;
import com.palermo.palermo.messageBeans.Vote;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
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
public class VoteController {

    @Autowired
    GameMain gamemain;
    @Autowired
    SimpMessagingTemplate smp;

    @MessageMapping("/vote/gamevote")
    @SendTo("/topic/voting")
    public ChatMessageToClient greeting(Vote vote) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessageToClient(HtmlUtils.htmlEscape(vote.getVoter()) + " voted for " + HtmlUtils.htmlEscape(vote.getPersonvotedout()) + "!");
    }

    @MessageMapping("/vote/readystate/{variable}")

    public void ready(@Headers Map headers, @DestinationVariable String variable) throws Exception {
        String sessionid = headers.get("simpSessionId").toString();
        gamemain.setUserReady(Integer.parseInt(variable), sessionid);

        //TO Be DELETED
        gamemain.setUserReady(Integer.parseInt(variable), "1");
        gamemain.setUserReady(Integer.parseInt(variable), "2");
        gamemain.setUserReady(Integer.parseInt(variable), "3");
        gamemain.setUserReady(Integer.parseInt(variable), "4");
        gamemain.setUserReady(Integer.parseInt(variable), "5");


        //TO Be DELETED

    }
}
