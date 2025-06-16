/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Final_poe;

/**
 *
 * @author RC_Student_lab
 */

import java.util.UUID;

public class Message {

    private String recipient;
    private String message;
    private String flag;
    private String messageID;
    private String hash;

    public Message(String recipient, String message, String flag) {
        this.recipient = recipient;
        this.message = message;
        this.flag = flag;
        this.messageID = UUID.randomUUID().toString();
        this.hash = Integer.toHexString((recipient + message).hashCode());
    }

    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getFlag() { return flag; }
    public String getMessageID() { return messageID; }
    public String getHash() { return hash; }
} 