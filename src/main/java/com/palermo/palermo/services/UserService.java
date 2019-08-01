/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.services;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Los_e
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean checkIfUsernameExists(String username) {
        return userRepo.existsUserByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public User getUserById(int userid) {
        return userRepo.findById(userid).get();
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    public User findBySerial(String serial) {
        return userRepo.findBySerial(serial);
    }
     public boolean checkIfEmailExists(String email) {
        return userRepo.existsUserByEmail(email);
    
    public void delete(int userid) {
        userRepo.deleteById(userid);
    }
}
