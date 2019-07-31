/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userinfinishedgames;
import com.palermo.palermo.entities.Userprofile;
import com.palermo.palermo.entities.Userprofileview;
import com.palermo.palermo.repositories.UserProfileRepo;
import com.palermo.palermo.repositories.UserRepo;
import com.palermo.palermo.services.UserInFinishedGamesService;
import com.palermo.palermo.services.UserProfileService;
import com.palermo.palermo.services.UserProfileViewService;
import java.util.List;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author djbil
 */
@Controller
@SessionAttributes("loggedinuser")
@RequestMapping(value = "allusers")
public class AllUsersController {

    @Autowired
    private UserRepo userrepo;

    @Autowired
    private UserProfileViewService userProfileViewService;
    
    @Autowired
    private UserInFinishedGamesService userInFinishedGamesService;

    @RequestMapping(value = "/showallusers", method = RequestMethod.GET)
    public String showAllUsers(
            ModelMap mm
            ) {
        
        List<Userprofileview> list = userProfileViewService.getAll();
        System.out.println(list.toString());
        mm.addAttribute("allusers",  list);
        return "allusers";
    }
}
