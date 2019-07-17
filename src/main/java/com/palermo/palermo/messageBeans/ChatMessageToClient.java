/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.messageBeans;

/**
 *
 * @author Los_e
 */
public class ChatMessageToClient {
        private String content;

    public ChatMessageToClient() {
    }

    public ChatMessageToClient(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
