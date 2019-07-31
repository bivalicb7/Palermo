/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.repositories;

import com.palermo.palermo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Los_e
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
        boolean existsUserByUsername(String username);
        User findUserByUsername(String username);
        User findByEmail(String email);
        User findBySerial(String serial);
}

