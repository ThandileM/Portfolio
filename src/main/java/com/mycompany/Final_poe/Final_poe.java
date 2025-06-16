/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Final_poe;

/**
 *
 * @author RC_Student_lab
 */

import java.util.List;
import javax.swing.JOptionPane;

public class Final_poe {
   

   public static void main(String[]args){ 
       
       String firstName;
       String lastName;
       String username = null;
       String password = null;
       String cellPhoneNumber = null;
       
       firstName = JOptionPane.showInputDialog("Enter first name: ");
       lastName = JOptionPane.showInputDialog("Enter last name: ");
       
    
       
       
       //Validate username
       boolean isValidUsername = false;
       while(!isValidUsername){
           username = JOptionPane.showInputDialog("Enter username (must contain an underscore and be no more than 5 character):");
           if (username != null && username.contains("_") && username.length() <=5){
            isValidUsername = true;
           }else{
               JOptionPane.showMessageDialog(null, "username is not correctly formatted.");
           }
       }
       
        // Validate password
        
        boolean isValidPassword = false;
        while (!isValidPassword) {
            password = JOptionPane.showInputDialog("Enter password (must contain a capital letter, a special character, a number, and be no more than 8 characters):");
            if (password != null && isValidPassword(password)) {
                isValidPassword = true;
            } else {
                JOptionPane.showMessageDialog(null, "Password is incorrectly formatted. Please ensure that the password contains at least one capital letter, a number, a special character, and is no more than 8 characters.");
            }
        }

        // Validate cellphone number
       
        boolean isValidCellphoneNumber = false;
        while (!isValidCellphoneNumber) {
            cellPhoneNumber = JOptionPane.showInputDialog("Enter cellphone number (must start with +27 and be 12 digits):");
            if(cellPhoneNumber != null && isValidCellphoneNumber(cellPhoneNumber)){    
                isValidCellphoneNumber = true;
            } else {
                JOptionPane.showMessageDialog(null, "Cellphone is incorrectly formatted");
            }
        }

        JOptionPane.showMessageDialog(null, "Cellphone Number successfully added");
      
        // Login phase
        boolean loggedIn =  false;
        while(!loggedIn){
            String loginUsername = JOptionPane.showInputDialog( "=== Log In ===\nEnter username");
            String loginPassword = JOptionPane.showInputDialog("Enter password:");

        if (loginUsername != null && loginPassword != null &&
            loginUsername.equals(username) &&  loginPassword.equals(password)) {
            loggedIn = true;
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + ", it is great to have you back!");
        } else {
           JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
        }
      }
        
        runQuickChat();
    
   }
       //Validates password with specific conditions 
       private static boolean isValidPassword(String password){
           if (password.length() > 8){
               return false;
           }
           boolean hasUpperCase = false;
           boolean hasDigit = false;
           boolean hasSpecialChar = false;
           String specialCharacters = "@#$%^&*+=";
           
           for (char ch : password.toCharArray()){
               if(Character.isUpperCase(ch)) hasUpperCase = true;
               if(Character.isDigit(ch)) hasDigit = true;
               if(specialCharacters.indexOf(ch) >= 0) hasSpecialChar = true;
           }
           
           return hasUpperCase && hasDigit && hasSpecialChar;
       }
       
       //Validate cellphone Number
       private static boolean isValidCellphoneNumber(String cellPhoneNumber){
           if(cellPhoneNumber.length() != 12 && !cellPhoneNumber.startsWith("+27")){
               return false;
           }
           for (int i = 3; i < cellPhoneNumber.length(); i++){
               if(!Character.isDigit(cellPhoneNumber.charAt(i))){
                   return false;
               }
           }
           return true;
       }


    // Messaging functionality
    private static void runQuickChat() {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        boolean running = true;

      while (running) {
        String[] options = {
            "Send Message",
            "View Sent Messages",
            "View Longest Message",
            "Search by Message ID",
            "Search by Recipient",
            "Delete by Hash",
            "Display Report",
            "Quit"
        };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option:",
                "QuickChat Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0 -> handleSendMessage();
            case 1 -> displaySentMessages();
            case 2 -> displayLongestMessage();
            case 3 -> searchByMessageID();
            case 4 -> searchByRecipient();
            case 5 -> deleteByHash();
            case 6 -> displayFullReport();
            case 7, JOptionPane.CLOSED_OPTION -> {
                running = false;
                JOptionPane.showMessageDialog(null, "Goodbye!");
            }
            default -> JOptionPane.showMessageDialog(null, "Invalid option.");
        }
    }
    }
    private static void handleSendMessage() {
    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
    if (recipient == null) return;

    for (int i = 1; i <= 5; i++) {
        String message = JOptionPane.showInputDialog("Enter message " + i + " of 5:");
        if (message == null) {
            JOptionPane.showMessageDialog(null, "Message entry cancelled.");
            break;
        }

        if (message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message under 250 characters.");
            i--; // retry
        } else {
            MessageManager.sendMessage(recipient, message, "Sent");
            JOptionPane.showMessageDialog(null, "Message " + i + " sent.");
        }
    }
}
    private static void displaySentMessages() {
    StringBuilder sb = new StringBuilder("=== Sent Messages ===\n");
    for (Message m : MessageManager.sentMessages) {
        sb.append("To: ").append(m.getRecipient()).append("\n");
        sb.append("Message: ").append(m.getMessage()).append("\n\n");
    }
    JOptionPane.showMessageDialog(null, sb.toString());
}
    private static void displayLongestMessage() {
    Message longest = MessageManager.findLongestMessage();
    if (longest != null) {
        JOptionPane.showMessageDialog(null,
            "Longest Sent Message:\n" + longest.getMessage());
    } else {
        JOptionPane.showMessageDialog(null, "No sent messages available.");
    }
}
    private static void searchByMessageID() {
    String id = JOptionPane.showInputDialog("Enter message ID to search:");
    Message msg = MessageManager.searchByID(id);
    if (msg != null) {
        JOptionPane.showMessageDialog(null,
            "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessage());
    } else {
        JOptionPane.showMessageDialog(null, "Message not found.");
    }
}
private static void searchByRecipient() {
    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
    List<Message> results = MessageManager.searchByRecipient(recipient);
    if (results.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
    } else {
        StringBuilder sb = new StringBuilder("Messages for " + recipient + ":\n");
        for (Message m : results) {
            sb.append("- ").append(m.getMessage()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
    private static void deleteByHash() {
    String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
    boolean deleted = MessageManager.deleteByHash(hash);
    String msg = deleted ? "Message successfully deleted." : "No message found with that hash.";
    JOptionPane.showMessageDialog(null, msg);
}
    private static void displayFullReport() {
    String report = MessageManager.displayReport();
    JOptionPane.showMessageDialog(null, report);
}


}
