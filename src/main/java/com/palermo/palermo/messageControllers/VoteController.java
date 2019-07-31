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
    @Autowired
    TableStateController tableStateController;

    @MessageMapping("/vote/readystate/{variable}")

    public void ready(@Headers Map headers, @DestinationVariable String variable) throws Exception {
        String sessionid = headers.get("simpSessionId").toString();
        gamemain.setUserReady(Integer.parseInt(variable), "tmp" + sessionid);
    }

    @MessageMapping("/vote/gamevote/{variable}")

    public void incomingVote(Vote vote, @Headers Map headers, @DestinationVariable String variable) throws Exception {

        //TO Be DELETED
//        gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("a").setDead(true);
//        gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("b").setDead(true);
        //TO Be DELETED
        //Check if  vote is empty
        if (!vote.getPersonvotedout().isEmpty()) {

            String sessionid = "tmp" + headers.get("simpSessionId").toString();
            vote.setVoter(sessionid);

            if (!gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(vote.getVoter()).isHasvoted() && !gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(vote.getPersonvotedout()).isDead()) {

                //Send vote back to table to be displayed if  it is a daykill
                if (vote.getPhase().equals("daykill")) {
                    smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), vote);
                }

                gamemain.collectVotes(Integer.parseInt(variable), vote);

            }
        }

//        //TO Be DELETED
////        System.out.println(gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("a").isDead());
////        System.out.println(gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get("b").isDead());
////        
//        Vote testvote1 = new Vote();
//
//        testvote1.setVoter("tmpa");
//        testvote1.setPhase("daykill");
//        testvote1.setPersonvotedout("tmpc");
////        gamemain.collectVotes(Integer.parseInt(variable), testvote1);
//
//        if (!testvote1.getPersonvotedout().isEmpty()) {
//
//            if (!gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote1.getVoter()).isHasvoted() && !gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote1.getPersonvotedout()).isDead()) {
//
//                //Send vote back to table to be displayed if  it is a daykill
////                if (testvote1.getPhase().equals("daykill")) {
////                    smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), testvote1);
////                }
//
//                gamemain.collectVotes(Integer.parseInt(variable), testvote1);
//
//            }
//        }
//
//        Vote testvote2 = new Vote();
//
//        testvote2.setVoter("tmpb");
//        testvote2.setPhase("daykill");
//        testvote2.setPersonvotedout("tmpb");
////        gamemain.collectVotes(Integer.parseInt(variable), testvote2);
//
//        if (!testvote2.getPersonvotedout().isEmpty()) {
//
//            if (!gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote2.getVoter()).isHasvoted() && !gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote2.getPersonvotedout()).isDead()) {
//
//                //Send vote back to table to be displayed if  it is a daykill
////                if (testvote2.getPhase().equals("daykill")) {
////                    smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), testvote2);
////                }
//
//                gamemain.collectVotes(Integer.parseInt(variable), testvote2);
//
//            }
//        }
//
//        Vote testvote3 = new Vote();
//
//        testvote3.setVoter("tmpc");
//        testvote3.setPhase("daykill");
//        testvote3.setPersonvotedout("tmpb");
////        gamemain.collectVotes(Integer.parseInt(variable), testvote3);
//
//        if (!testvote3.getPersonvotedout().isEmpty()) {
//
//            if (!gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote3.getVoter()).isHasvoted() && !gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote3.getPersonvotedout()).isDead()) {
//
//                //Send vote back to table to be displayed if  it is a daykill
////                if (testvote3.getPhase().equals("daykill")) {
////                    smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), testvote3);
////                }
//
//                gamemain.collectVotes(Integer.parseInt(variable), testvote3);
//
//            }
//        }
//
//        Vote testvote4 = new Vote();
//
//        testvote4.setVoter("tmpd");
//        testvote4.setPhase("daykill");
//        testvote4.setPersonvotedout("tmpc");
////        gamemain.collectVotes(Integer.parseInt(variable), testvote4);
//
//    if (!testvote4.getPersonvotedout().isEmpty()) {
//
//            if (!gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote4.getVoter()).isHasvoted() && !gamemain.getGametables().get(Integer.parseInt(variable)).getUsersintable().get(testvote4.getPersonvotedout()).isDead()) {
//
//                //Send vote back to table to be displayed if  it is a daykill
////                if (testvote4.getPhase().equals("daykill")) {
////                    smp.convertAndSend("/topic/displayvote/" + Integer.parseInt(variable), testvote4);
////                }
//
//                gamemain.collectVotes(Integer.parseInt(variable), testvote4);
//
//            }
//        }
//
////        testvote.setVoter("tmpe");
////        testvote.setPhase("daykill");
////        testvote.setPersonvotedout("a");
////        gamemain.collectVotes(Integer.parseInt(variable), testvote);
//        //TO Be DELETED
    }

    @MessageMapping("/vote/reset/{variable}")

    public void resetTable(@Headers Map headers, @DestinationVariable String variable) throws Exception {
        gamemain.resetTableForNewGame(Integer.parseInt(variable));
    }

    @MessageMapping("/vote/ban/{tableid}/{sessionid}")

    public void banFromTable(@Headers Map headers, @DestinationVariable String sessionid, @DestinationVariable String tableid) throws Exception {
        System.out.println("banned " + tableid + " " + sessionid);
        gamemain.removeUserFromTable(sessionid);
        tableStateController.sendBan(Integer.parseInt(tableid), sessionid);
    }
}
