/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Final_poe;

/**
 *
 * @author RC_Student_lab
 */
import java.util.*;
import java.util.stream.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class MessageManager {
    
    //Populate Arrays
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();

    
    public static void addMessage(Message msg) {
        switch (msg.getFlag()) {
            case "Sent" -> sentMessages.add(msg);
            case "Stored" -> storedMessages.add(msg);
            case "Disregard" -> disregardedMessages.add(msg);
        }
        messageIDs.add(msg.getMessageID());
        messageHashes.add(msg.getHash());
    }

    public static void populateTestData() {
        addMessage(new Message("+27834557896", "Did you get the cake?", "Sent"));
        addMessage(new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        addMessage(new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard"));
        addMessage(new Message("0838884567", "It is dinner time !", "Sent"));
        addMessage(new Message("+27838884567", "Ok, I am leaving without you.", "Stored"));
    }

    public static void showSendersAndRecipients() {
        for (Message m : sentMessages) {
            System.out.println("Recipient: " + m.getRecipient());
        }
    }

    public static Message getLongestSentMessage() {
        return sentMessages.stream()
            .max(Comparator.comparingInt(m -> m.getMessage().length()))
            .orElse(null);
    }

    public static Message searchByMessageID(String id) {
        for (Message m : sentMessages) {
            if (m.getMessageID().equals(id)) return m;
        }
        return null;
    }

    public static List<Message> searchByRecipient(String recipient) {
        List<Message> result = new ArrayList<>();
        Stream.of(sentMessages, storedMessages)
            .flatMap(List::stream)
            .filter(m -> m.getRecipient().equals(recipient))
            .forEach(result::add);
        return result;
    }

    public static boolean deleteByHash(String hash) {
        Iterator<Message> it = sentMessages.iterator();
        while (it.hasNext()) {
            Message msg = it.next();
            if (msg.getHash().equals(hash)) {
                it.remove();
                messageHashes.remove(hash);
                messageIDs.remove(msg.getMessageID());
                return true;
            }
        }
        return false;
    }

    public static void displaySentReport() {
        for (Message m : sentMessages) {
            System.out.println("Hash: " + m.getHash());
            System.out.println("Recipient: " + m.getRecipient());
            System.out.println("Message: " + m.getMessage());
            System.out.println("------------------");
        }
    }

  public static void sendMessage(String recipient, String message, String flag) {
    Message msg = new Message(recipient, message, flag);
    
    // Add to message ID and hash arrays
    messageIDs.add(msg.getMessageID());
    messageHashes.add(msg.getHash());

    // Store in the correct category
    switch (flag.toLowerCase()) {
        case "sent" -> sentMessages.add(msg);
        case "stored" -> storedMessages.add(msg);
        case "disregard" -> disregardedMessages.add(msg);
        default -> System.out.println("Unknown flag: " + flag);
    }
}
  public static Message getLongestOverallMessage() {
    return Stream.of(sentMessages, storedMessages, disregardedMessages)
        .flatMap(List::stream)
        .max(Comparator.comparingInt(m -> m.getMessage().length()))
        .orElse(null);
}

   public static Message findLongestMessage() {
    Message longest = null;
    int maxLength = 0;

    List<List<Message>> allMessages = List.of(sentMessages, storedMessages, disregardedMessages);

    for (List<Message> messages : allMessages) {
        for (Message msg : messages) {
            if (msg.getMessage().length() > maxLength) {
                maxLength = msg.getMessage().length();
                longest = msg;
            }
        }
    }

    return longest;
}

    public static Message searchByID(String id) {
    for (Message msg : sentMessages) {
        if (msg.getMessageID().equals(id)) {
            return msg;
        }
    }
    for (Message msg : storedMessages) {
        if (msg.getMessageID().equals(id)) {
            return msg;
        }
    }
    for (Message msg : disregardedMessages) {
        if (msg.getMessageID().equals(id)) {
            return msg;
        }
    }
    return null;
}
    public static String displayReport() {
    if (sentMessages.isEmpty()) return "No messages to display.";

    StringBuilder report = new StringBuilder("=== Sent Messages Report ===\n");

    for (Message msg : sentMessages) {
        report.append("Message Hash: ").append(msg.getHash()).append("\n");
        report.append("Recipient: ").append(msg.getRecipient()).append("\n");
        report.append("Message: ").append(msg.getMessage()).append("\n");
        report.append("Message ID: ").append(msg.getMessageID()).append("\n");
        report.append("--------------------------\n");
    }

    return report.toString();
}
    public static void displayLongestMessageDetails() {
    Message longest = findLongestMessage();
    if (longest != null) {
        System.out.println("=== Longest Message Details ===");
        System.out.println("Recipient: " + longest.getRecipient());
        System.out.println("Message: " + longest.getMessage());
        System.out.println("Message ID: " + longest.getMessageID());
        System.out.println("Hash: " + longest.getHash());
        System.out.println("Flag: " + longest.getFlag());
    } else {
        System.out.println("No messages found.");
    }
}


     // Attribution: JSON reading logic assisted by ChatGPT – OpenAI
    public static void readMessagesFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONArray messages = (JSONArray) parser.parse(reader);
            for (Object obj : messages) {
                JSONObject jsonObj = (JSONObject) obj;
                String recipient = (String) jsonObj.get("recipient");
                String message = (String) jsonObj.get("messageText");
                String flag = (String) jsonObj.get("flag");
                sendMessage(recipient, message, flag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
