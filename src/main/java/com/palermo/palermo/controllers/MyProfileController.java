/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userprofile;
import com.palermo.palermo.repositories.UserProfileRepo;
import com.palermo.palermo.repositories.UserRepo;
import com.palermo.palermo.services.UserProfileService;
import com.palermo.palermo.services.UserService;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author djbil
 */
@Controller
@SessionAttributes("loggedinuser")
@RequestMapping(value = "myprofile")
public class MyProfileController {

    @Autowired
    private UserRepo UserRepo;
    @Autowired
    private UserProfileRepo UserProfileRepo;
    @Autowired
    private UserService UserService;
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/showmyprofile", method = RequestMethod.GET)
    public String showmyprofile(
            ModelMap mm
    ) {
        Userprofile userprofile = new Userprofile();
        mm.addAttribute("myprofile", userprofile);
        return "myprofile";
    }

    @RequestMapping(value = "/addmydata", method = RequestMethod.POST)
    public String addMyprofile(
            ModelMap mm,
            @ModelAttribute("myprofile") Userprofile userprofile,
            @ModelAttribute("loggedinuser") User user,
            @RequestParam(value = "myfile") MultipartFile sourcefile
    //            ,
    //            HttpServletRequest req
    ) {

        userprofile.setUserprofileid(user.getUserid());
        userprofile.setProfileimage("image");
        userProfileService.addUserProfile(userprofile);

//        OutputStream os = null;
//        try {
//            Path path = Paths.get(context.getRealPath("userimages"), sourcefile.getOriginalFilename());
//
//            File f = new File(path.toString());
//
//            os = Files.newOutputStream(path);
//            os.write(sourcefile.getBytes());
//            System.out.println(path.toString());
//            //        File f = new File("C:\\Users\\djbil\\OneDrive\\Έγγραφα\\GitHub\\websocket_test\\src\\main\\resources\\static\\userimages" + );
//
//        } catch (IOException ex) {
//            Logger.getLogger(MyProfileController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                os.close();
//            } catch (IOException ex) {
//                Logger.getLogger(MyProfileController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return "home";

    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String showLogInpPage(ModelMap mm
//    ) {
//
//        mm.addAttribute("user", new User());
//        return "login";
//    }
//    
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String showLogInPage(ModelMap mm
//    ) {
//
//        mm.addAttribute("userprofile", new Userprofile());
//        return "myprofile";
//    }
//   
}
