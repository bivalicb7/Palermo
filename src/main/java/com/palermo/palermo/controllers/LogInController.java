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
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    ServletContext context;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userLogInValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogInPage(ModelMap mm
    ) {

        mm.addAttribute("user", new User());
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testMe(ModelMap mm,
            @RequestParam("myfile") MultipartFile multipart
    ) throws IOException{

//        File tmp = File.createTempFile("test", multipart.getOriginalFilename());
//        OutputStream os = Files.newOutputStream(tmp.toPath());
//        os.write(multipart.getBytes());
        String multipartBase64 = Base64.getEncoder().encodeToString(multipart.getBytes());
        mm.addAttribute("image", multipartBase64);
//        File f = new File(context.getResource("/WEB-INF/").getPath(), "uploadedfiles/" + multipart.getOriginalFilename());
//        
//        System.out.println(f.toPath());
//        OutputStream os = Files.newOutputStream(f.toPath());
//            os.write(multipart.getBytes());
//        File f = null;
//        try {
//            f = new File(context.getResource("/WEB-INF/").toString(), "uploadedfiles/" + multipart.getOriginalFilename());
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Path filepath;
//        filepath = Paths.get(f.getCanonicalPath());
//        System.out.println(filepath);
//        try (OutputStream os = Files.newOutputStream(filepath)) {
//            os.write(multipart.getBytes());
//        } catch (IOException ex) {
//            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            System.out.println(context.getResource("/WEB-INF/uploadedfiles/index1.html"));
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return "test";
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

//            return "home";
            return "redirect:/lobby/home";
        }
    }

}
