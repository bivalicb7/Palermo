/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.validators;

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
public class UserRegisterValidator implements Validator {
        @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        boolean check = userService.checkIfUsernameExists(user.getUsername());
        if (check) {
            errors.rejectValue("username", "username.unique");
        } 
        boolean checkEmail= userService.checkIfEmailExists(user.getEmail());
        if (checkEmail) {
            errors.rejectValue("email", "email.unique");
        }
       
//        if (!user.getPassword().equals(user.getPasswordconfirm())){
//            errors.rejectValue("passwordconfirm","password.not.match");
//        }
    String pass1=user.getPassword();
    String pass2=user.getPasswordconfirm();
    if(!pass1.equals(pass2)){
    errors.rejectValue("passwordconfirm","password.not.match");
    } 
    }
    
}
