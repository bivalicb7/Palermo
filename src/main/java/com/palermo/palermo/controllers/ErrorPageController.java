/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.controllers;

import com.palermo.palermo.gameModel.GameTable;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Los_e
 */
@Controller
@RequestMapping(value = "error")
public class ErrorPageController {

    @RequestMapping(value = "/inactiveaccount", method = RequestMethod.GET)
    public String showInactive(ModelMap mm) {

        return "errorpage";
    }
}
