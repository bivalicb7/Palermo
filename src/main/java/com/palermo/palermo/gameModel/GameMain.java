/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.services.UserService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Los_e
 */
@Component
public class GameMain {

    @Autowired
    UserService userService;

    private static int nexttableid;

    // Key: Inetger tableid  Value: Gametable
    private Map<Integer, GameTable> gametables = new HashMap();
    // Key: String user websocket sessionid  Value: Integer tableid
    private Map<String, Integer> usersintablesmapping = new HashMap();

    public GameMain() {
    }

    public static int getNexttableid() {
        return nexttableid;
    }

    public static void setNexttableid(int nexttableid) {
        GameMain.nexttableid = nexttableid;
    }

    public Map<Integer, GameTable> getGametables() {
        return gametables;
    }

    public void setGametables(Map<Integer, GameTable> gametables) {
        this.gametables = gametables;
    }

    public int returnNextTableId() {
        GameMain.nexttableid = nexttableid + 1;
        return GameMain.nexttableid;
    }

    public void addUserToTable(int tableid, int userid, String sessionid) {

        GameTable table = gametables.get(tableid);
        GameUserInTable newuserintable = new GameUserInTable();
        newuserintable.setUser(userService.getUserById(userid));
        newuserintable.setUsersessionid(sessionid);
        table.getUsersintable().put(sessionid, newuserintable);

        //Also add usersessionid to usersintablesmapping for quick removal
        //This is because i can't get the tableid as a header in the Disconnect event listener
        usersintablesmapping.put(sessionid, tableid);
        System.out.println(usersintablesmapping.toString());

    }

    public void removeUserFromTable(String sessionid) {
        int tableid = usersintablesmapping.get(sessionid);

        GameTable table = gametables.get(tableid);
        table.getUsersintable().remove(sessionid);

        //Also remove from the usersintablesmapping
        usersintablesmapping.remove(sessionid);
        System.out.println(usersintablesmapping.toString());

    }

}
