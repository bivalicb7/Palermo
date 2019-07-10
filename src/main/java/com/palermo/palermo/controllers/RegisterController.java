/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.palermo.palermo.entities.User;
import com.palermo.palermo.services.UserService;
import com.palermo.palermo.validators.UserRegisterValidator;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "register")
public class RegisterController {
        @Autowired
    private UserService userService;
    @Autowired
    private UserRegisterValidator userValidator;
    
        @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }
    
    @RequestMapping(value = "/createaccount", method = RequestMethod.GET)
    public String showRegisterPage(ModelMap mm
    ) {

        mm.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/docreateaccount", method = RequestMethod.POST)
    public String doRegister(
            ModelMap mm,
            @Valid @ModelAttribute("user") User user,
            HttpSession session,
            BindingResult br
    ) {

        if (br.hasErrors()) {
            return "register";
        } else {
            
            user.setRole("plainuser");
            
            //hash password
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(bcryptHashString);
            
            userService.addUser(user);
//            mm.addAttribute("loggedinuser", );
            User loggedinuser = userService.getUserByUsername(user.getUsername());
            loggedinuser.setPassword(null);
            session.setAttribute("loggedinuser", loggedinuser);
            return "home";
        }

    }
}
