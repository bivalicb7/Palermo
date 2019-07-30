/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.services;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userinfinishedgames;
import com.palermo.palermo.entities.Usersingame;
import com.palermo.palermo.repositories.UserInFinishedGamesRepo;
import com.palermo.palermo.repositories.UserRepo;
import com.palermo.palermo.repositories.UsersInGameRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Los_e
 */
@Service
public class UserInFinishedGamesService {
    @Autowired
    private UserInFinishedGamesRepo userInFinishedGamesRepo;

    
//    public boolean checkIfUsernameExists(String username) {
//        return userRepo.existsUserByUsername(username);
//    }
//    
//    public User getUserByUsername(String username) {
//        return userRepo.findUserByUsername(username);
//    }
//    
    public List<Userinfinishedgames> getUserInFinishedGamesById(int userid) {
       return userInFinishedGamesRepo.findByUseridEquals(userid);
    }
     
//    public void addUserInGame(Usersingame usersingame) {
//        usersInGameRepo.save(usersingame);
//    }
}
