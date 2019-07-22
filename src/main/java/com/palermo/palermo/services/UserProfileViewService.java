/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.services;

import com.palermo.palermo.entities.Userprofile;
import com.palermo.palermo.entities.Userprofileview;
import com.palermo.palermo.repositories.UserProfileRepo;
import com.palermo.palermo.repositories.UserProfileViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author djbil
 */
@Service
public class UserProfileViewService {

    @Autowired
    private UserProfileViewRepo userprofileviewRepo;
    
    public Userprofileview getUserProfileViewById(int userprofileid) {
       return userprofileviewRepo.findById(userprofileid).get();
    }
}
