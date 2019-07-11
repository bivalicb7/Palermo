/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.endpoints;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Los_e
 */
public class SessionRegistry {

    private static SessionRegistry INSTANCE;

    private SessionRegistry() {
    }
// Multi threading

    public synchronized static SessionRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SessionRegistry();
        }
        return INSTANCE;
    }

    private Set<Session> sessions = new HashSet<>();

    public Set<Session> getAll() {
        return Collections.unmodifiableSet(sessions);
    }

    public synchronized void add(Session session) {
        sessions.add(session);
    }

    public synchronized void remove(Session session) {
        sessions.remove(session);
    }

    public int getPeople() {
        return sessions.size();
    }
}
