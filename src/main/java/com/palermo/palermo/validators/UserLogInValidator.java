/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.validators;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.palermo.palermo.entities.User;
import com.palermo.palermo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Los_e
 */
@Component
public class UserLogInValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //Check username
        boolean checkusername = userService.checkIfUsernameExists(user.getUsername());
        if (!checkusername) {
            errors.rejectValue("username", "username.wrong");
        } else {

            //Also check password
            BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), userService.getUserByUsername(user.getUsername()).getPassword());
            boolean checkpassword = result.verified;

            if (!checkpassword) {
                errors.rejectValue("password", "password.wrong");
            }
        }

    }

}
