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
        gamemain.setUserReady(Integer.parseInt(variable), "a");
        gamemain.setUserReady(Integer.parseInt(variable), "b");
        gamemain.setUserReady(Integer.parseInt(variable), "c");
        gamemain.setUserReady(Integer.parseInt(variable), "d");
        gamemain.setUserReady(Integer.parseInt(variable), "e");

        //TO Be DELETED
    }

    @MessageMapping("/vote/gamevote/{variable}")

    public void incomingVote(Vote vote, @Headers Map headers, @DestinationVariable String variable) throws Exception {

        //Check if  vote is empty
        if (!vote.getPersonvotedout().isEmpty()) {

            String sessionid = headers.get("simpSessionId").toString();
            vote.setVoter(sessionid);
            gamemain.collectVotes(Integer.parseInt(variable), vote);

        }

        //TO Be DELETED
//        gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("a").setDead(true);
//        gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("b").setDead(true);
//        System.out.println(gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("a").isDead());
//        System.out.println(gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("b").isDead());
//        
        Vote testvote = new Vote();
        testvote.setVoter("a");
        testvote.setPersonvotedout("c");
        gamemain.collectVotes(Integer.parseInt(variable), testvote);

        testvote.setVoter("b");
        testvote.setPersonvotedout("a");
        gamemain.collectVotes(Integer.parseInt(variable), testvote);

        testvote.setVoter("c");
        testvote.setPersonvotedout("b");
        gamemain.collectVotes(Integer.parseInt(variable), testvote);

        testvote.setVoter("d");
        testvote.setPersonvotedout("a");
        gamemain.collectVotes(Integer.parseInt(variable), testvote);

        testvote.setVoter("e");
        testvote.setPersonvotedout("a");
        gamemain.collectVotes(Integer.parseInt(variable), testvote);

        //TO Be DELETED
        
        //Send vote back to table to be displayed
        smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), vote);

    }
}
