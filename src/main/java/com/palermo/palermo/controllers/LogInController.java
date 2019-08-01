/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.services.UserService;
import com.palermo.palermo.validators.UserLogInValidator;
import com.palermo.palermo.validators.UserRegisterValidator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Los_e
 */
@Controller
@RequestMapping(value = "index")
public class LogInController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserLogInValidator userLogInValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userLogInValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogInPage(ModelMap mm
    ) {

        mm.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogIn(
            ModelMap mm,
            HttpSession session,
            HttpServletResponse response,
            @Valid @ModelAttribute("user") User user,
            BindingResult br
    ) {
                
        if (br.hasErrors()) {
            return "login";
        } else {
            //Add user in session
//            mm.addAttribute("loggedinuser", userService.getUserByUsername(user.getUsername()));
            User loggedinuser = userService.getUserByUsername(user.getUsername());
            loggedinuser.setPassword(null);
            session.setAttribute("loggedinuser", loggedinuser);
            
            //Add user in cookie   
            // create a cookie
            Cookie cookie = new Cookie("useridincookie", Integer.toString(loggedinuser.getUserid()));
            cookie.setPath("/");
            //add cookie to response
            response.addCookie(cookie);
            //Add user's username in cookie   
            // create a cookie
            Cookie cookiewithusername = new Cookie("usernameincookie", loggedinuser.getUsername());
            cookiewithusername.setPath("/");
            //add cookie to response
            response.addCookie(cookiewithusername);
            
            //Add user's role in cookie   
            // create a cookie
            Cookie cookiewithrole = new Cookie("roleincookie", loggedinuser.getRole());
            cookiewithrole.setPath("/");
            //add cookie to response
            response.addCookie(cookiewithrole);
            
            
//            return "home";
            return "redirect:/lobby/home";
        }
    }

}
