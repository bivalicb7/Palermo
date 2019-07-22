/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.gameModel;

import com.palermo.palermo.entities.User;
import com.palermo.palermo.entities.Userprofile;
import com.palermo.palermo.entities.Userprofileview;
import com.palermo.palermo.messageBeans.Roles;
import com.palermo.palermo.messageControllers.TableStateController;
import com.palermo.palermo.messageControllers.TablesInLobbyController;
import com.palermo.palermo.services.UserProfileService;
import com.palermo.palermo.services.UserProfileViewService;
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
    @Autowired
    UserProfileViewService userProfileViewService;
        @Autowired
    TablesInLobbyController tablesInLobbyController;
        @Autowired
        TableStateController tableStateController;

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

    public Map<String, Integer> getUsersintablesmapping() {
        return usersintablesmapping;
    }

    public void setUsersintablesmapping(Map<String, Integer> usersintablesmapping) {
        this.usersintablesmapping = usersintablesmapping;
    }
    
    

    public int returnNextTableId() {
        GameMain.nexttableid = nexttableid + 1;
        return GameMain.nexttableid;
    }

    public void addUserToTable(int tableid, int userid, String sessionid) {

        GameTable table = gametables.get(tableid);
        GameUserInTable newuserintable = new GameUserInTable();
        
        Userprofileview newuserprofileintable = userProfileViewService.getUserProfileViewById(userid);
//        User newuseruserintable = userService.getUserById(userid);
        newuserintable.setUserprofileview(newuserprofileintable);
        newuserintable.setUsersessionid(sessionid);
//        newuserintable.setUsername(newuseruserintable.getUsername());
//        newuserintable.setFirstname(newuserprofileintable.getFirstname());
//        newuserintable.setLastname(newuserprofileintable.getLastname());
//        newuserintable.setProfileimagebase64(newuserprofileintable.getProfileimagebase64());
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
        
        //Check if table is empty of users and remove it from the game
        removeTableFromMain(tableid);
    }

    public void removeTableFromMain(int tableid) {

        if (gametables.get(tableid).getUsersintable().isEmpty()) {
            gametables.remove(tableid);
            tablesInLobbyController.updateTablesInLobby();
        }
    }
    
    public void setUserReady(int tableid, String sessionid) {
        GameTable table = gametables.get(tableid);
        table.getUsersintable().get(sessionid).setReady(true);
        
        //Everytime a user is ready check if all of them are ready in order to start game
        if(table.checkIfAllUsersReady()) {
            table.assignRoles();
            Roles roles = table.returnRolesObject();
            tableStateController.sendRoles(tableid, roles);
        }
        
    }
        
}
