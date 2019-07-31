/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.services;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userprofile;
import static com.palermo.palermo.entities.Userprofile_.userprofileid;
import com.palermo.palermo.repositories.UserProfileRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author djbil
 */
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepo userprofileRepo;

    public void addUserProfile(Userprofile userprofile) {
        userprofileRepo.save(userprofile);
    }

    public Userprofile getUserProfileById(int userprofileid) {
        return userprofileRepo.findById(userprofileid).get();
    }

    public List<Userprofile> getAll() {
        return userprofileRepo.findAll();
    }
}
