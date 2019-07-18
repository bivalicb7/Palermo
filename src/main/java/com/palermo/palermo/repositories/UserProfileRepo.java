/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.repositories;
import com.palermo.palermo.entities.Userprofile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author djbil
 */
@Repository
public interface UserProfileRepo extends JpaRepository<Userprofile, Integer>{

}
