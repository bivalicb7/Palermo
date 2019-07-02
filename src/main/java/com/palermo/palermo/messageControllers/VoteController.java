/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageControllers;

import com.palermo.palermo.messageBeans.Greeting;
import com.palermo.palermo.messageBeans.HelloMessage;
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
    public Greeting greeting(Vote vote) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting(HtmlUtils.htmlEscape(vote.getVoter()) + " voted for " + HtmlUtils.htmlEscape(vote.getPersonvotedout()) + "!");
    }
}
