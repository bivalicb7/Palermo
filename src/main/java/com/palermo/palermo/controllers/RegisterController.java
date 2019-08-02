/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.palermo.palermo.emailutils.EmailService;
import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userprofile;
import com.palermo.palermo.services.UserProfileService;
import com.palermo.palermo.services.UserService;
import com.palermo.palermo.validators.UserRegisterValidator;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

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
    private UserProfileService userProfileService;
    @Autowired
    private UserRegisterValidator userValidator;
    @Autowired
    private EmailService emailService;

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
            HttpSession session,
            HttpServletResponse response,
            @Valid @ModelAttribute("user") User user,
            BindingResult br
    ) {

        if (br.hasErrors()) {
            return "register";
        } else {

            String subject = "Confirmation email Palermo";
            String text = "Click the following link to confirm your account!";
            String serial = UUID.randomUUID().toString();
            String link = "http:/10.150.13.137:8080/palermo/register/confirm/" + serial;
            boolean emailsuccess = false;
            
                        try {
                emailService.sendSimpleMessage(user.getEmail(), subject, HtmlUtils.htmlEscape(text + " " + link));
                emailsuccess = true;
            } catch (Exception e) {
                emailsuccess = false;
            }
            
            user.setRole("plainuser");
            user.setSerial(serial);
            user.setActive(0);

            //hash password
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(bcryptHashString);

            userService.addUser(user);
            
            Userprofile userprofile= new Userprofile();
            userprofile.setUserprofileid(user.getUserid());
            userProfileService.addUserProfile(userprofile);
            

            mm.addAttribute("user", user);
            mm.addAttribute("emailsuccess", emailsuccess);

            return "register";
        }
    }

    @RequestMapping(value = "/confirm/{serial}", method = RequestMethod.GET)
    public String confirmRegistration(
            ModelMap mm,
            @PathVariable("serial") String serial
    ) {
        
        User user = userService.findBySerial(serial);
        user.setActive(1);
        userService.addUser(user);
        return "redirect:/index/login";
    }

}
