/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.messageBeans.ChatMessageToClient;
import com.palermo.palermo.messageBeans.ChatMessageFromClient;
import com.palermo.palermo.messageBeans.Vote;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 *
 * @author Los_e
 */
@Controller
public class VoteController {

    @MessageMapping("/vote")
    @SendTo("/topic/voting")
    public ChatMessageToClient greeting(Vote vote) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new ChatMessageToClient(HtmlUtils.htmlEscape(vote.getVoter()) + " voted for " + HtmlUtils.htmlEscape(vote.getPersonvotedout()) + "!");
    }
}
