/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

//import com.palermo.palermo.configurationClasses.WebSocketConfig;
//import com.palermo.palermo.configurationClasses.WebSocketConfig;
import com.palermo.palermo.entities.User;
import com.palermo.palermo.gameModel.GameMain;
import com.palermo.palermo.gameModel.GameTable;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.EndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.PathMappedEndpoints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 *
 * @author Los_e
 */
@Controller
@SessionAttributes("loggedinuser")
@RequestMapping(value = "lobby")
//@SessionAttributes("loggedinuser")

public class LobbyController {

    @Autowired
    GameMain gamemain;

    @Autowired
    LogInController logInController;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showLobby(ModelMap mm) {

        System.out.println("Tables running" + gamemain.getGametables().size());
        Map<Integer, GameTable> map = gamemain.getGametables();
        mm.addAttribute("runningtables", map);
        return "home";
    }

    @RequestMapping(value = "/startgame", method = RequestMethod.GET)
    public String startGame(ModelMap mm,
            @RequestParam("numofplayers") String numofplayers,
            HttpServletResponse response
    ) {

        int newtableid = gamemain.returnNextTableId();
        GameTable newtable = new GameTable();
        newtable.setGametableid(newtableid);
        newtable.setNumofplayers(numofplayers);
        newtable.setGameid(gamemain.returnGameId());
        gamemain.getGametables().put(newtableid, newtable);

        Cookie cookie = new Cookie("tableidincookie", Integer.toString(newtableid));
        //TODO set path to specific game page. (check link)
        cookie.setPath("/");
        //add cookie to response
        response.addCookie(cookie);

        return "game";
    }

    @RequestMapping(value = "/joingame", method = RequestMethod.GET)

    public String joinGame(ModelMap mm,
            //            @ModelAttribute("loggedinuser") User user,
            @RequestParam("tableid") String tableid,
            HttpServletResponse response
    ) {

//        System.out.println(user.getUserid());
        Cookie cookie = new Cookie("tableidincookie", tableid);
        //TODO set path to specific game page. (check link)
        cookie.setPath("/");
        //add cookie to response
        response.addCookie(cookie);

        return "game";
    }
}
