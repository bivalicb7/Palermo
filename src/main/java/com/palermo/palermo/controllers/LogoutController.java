/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import static com.palermo.palermo.entities.Userprofile_.user;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author djbil
 */
@Controller

public class LogoutController {
    
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String showLogOutPage (HttpSession session){
//    session.getAttribute(loggedinuser(user));
    session.invalidate();
    return "redirect:/index/login";
    }
}
