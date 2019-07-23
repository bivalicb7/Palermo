/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

import com.palermo.palermo.gameModel.GameUserInTable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Los_e
 */
public class Roles {

    //Key: String websocket sessionid , Value: String role
    private Map<String, String> roles;

    public Roles(Map<String, GameUserInTable> rolesmap) {

        this.roles = new HashMap();
        String role = null;
        String sessionid = null;
        for (Map.Entry<String, GameUserInTable> entry : rolesmap.entrySet()) {

            role = entry.getValue().getIngamerole();
            sessionid = entry.getKey();
            roles.put(sessionid, role);

        }
        
    }

    public Map<String, String> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, String> roles) {
        this.roles = roles;
    }

}
