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
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.math3.util.Precision;
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
@RequestMapping(value = "updateprofile")
public class UpdateMyProfileController {

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

    @RequestMapping(value = "/createmyprofile", method = RequestMethod.GET)
    public String showmyprofile(
            ModelMap mm
    ) {
        Userprofile userprofile = new Userprofile();
        mm.addAttribute("myprofile", userprofile);
        return "updateuserdata";
    }

    @RequestMapping(value = "/updatemydata", method = RequestMethod.GET)
    public String showmyprofilewithdata(
            ModelMap mm,
            @ModelAttribute("loggedinuser") User user
    ) {
        Userprofile userprofile = userProfileService.getUserProfileById(user.getUserid());
        mm.addAttribute("myprofile", userprofile);
        return "updateuserdata";
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

        if (!sourcefile.isEmpty()) {
            String multipartBase64 = null;
            try {

                //Resizing the image
                //1. Create temp file in order to create BufferedImage object
                String extension = FilenameUtils.getExtension(sourcefile.getOriginalFilename());
                File tmp = File.createTempFile("test", "." + extension);
                OutputStream os = Files.newOutputStream(tmp.toPath());
                os.write(sourcefile.getBytes());

                BufferedImage img = null;
                img = ImageIO.read(tmp);

                //2. Set new width and height. About 300px width and equivalent height. But first check if image is small than that
                //Get how many times smaller must the new image be
                double factor = 1;
                if (img.getWidth() > 300) {
                    factor = Precision.round((double) img.getWidth() / 300, 1);
                }

                //Get new widths and heights based according to the factor
                int newW = (int) (Precision.round((double) img.getWidth(), -1) / factor);
                int newH = (int) (Precision.round((double) img.getHeight(), -1) / factor);
                //Create the new image
                BufferedImage newimg = new BufferedImage(newW, newH, img.getType());
                Graphics2D g = newimg.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.drawImage(img, 0, 0, newW, newH, 0, 0, img.getWidth(), img.getHeight(), null);
                g.dispose();
                //Transform to File in order to get Inputstream->byte[]
                File tmp2 = File.createTempFile("newtest", "." + extension);
                ImageIO.write(newimg, extension, tmp2);
                InputStream targetStream = new FileInputStream(tmp2);
                //Encode to base64 string
                multipartBase64 = Base64.getEncoder().encodeToString(IOUtils.toByteArray(targetStream));

            } catch (IOException ex) {
                Logger.getLogger(UpdateMyProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Save multipart as blob in DB
//        try {
//            userprofile.setProfileimage(sourcefile.getBytes());
//        } catch (IOException ex) {
//            Logger.getLogger(UpdateMyProfileController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            userprofile.setProfileimageoriginalfilename(sourcefile.getOriginalFilename());
            userprofile.setProfileimagebase64(multipartBase64);

        } else {
            
            //Check if record in table has been created (not first visit) and get old image data, if new image has not been uploaded
            Userprofile olduserprofile = null;
            try {
                olduserprofile = userProfileService.getUserProfileById(user.getUserid());
                userprofile.setProfileimageoriginalfilename(olduserprofile.getProfileimageoriginalfilename());
                userprofile.setProfileimagebase64(olduserprofile.getProfileimagebase64());
            } catch (Exception e) {
                System.out.println("User Profile has not been created yet");
            }

        }

        userprofile.setUserprofileid(user.getUserid());
        userProfileService.addUserProfile(userprofile);

        return "redirect:/myprofile/showmydata";

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
