/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.Final_poe;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class MessageTest {

   @BeforeEach
    public void setUp() {
        MessageManager.sentMessages.clear();
        MessageManager.storedMessages.clear();
        MessageManager.disregardedMessages.clear();
        MessageManager.messageHashes.clear();
        MessageManager.messageIDs.clear();
        MessageManager.populateTestData();
    }

    @Test
    public void testSentMessagesPopulated() {
        List<String> messages = MessageManager.sentMessages.stream()
            .map(Message::getMessage)
            .toList();
        assertEquals(List.of("Did you get the cake?", "It is dinner time !"), messages);
    }

    @Test
    public void testLongestMessage() {
        Message longest = MessageManager.getLongestSentMessage();
        assertEquals("Did you get the cake?", longest.getMessage());
    }

    @Test
    public void testSearchByID() {
        Message msg = MessageManager.sentMessages.get(1);
        Message found = MessageManager.searchByMessageID(msg.getMessageID());
        assertEquals("It is dinner time !", found.getMessage());
    }

    @Test
    public void testSearchByRecipient() {
        List<Message> messages = MessageManager.searchByRecipient("+27838884567");
        List<String> actual = messages.stream().map(Message::getMessage).toList();
        assertEquals(List.of(
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        ), actual);
    }

    @Test
    public void testDeleteByHash() {
        String hash = MessageManager.storedMessages.get(0).getHash();
        boolean deleted = MessageManager.deleteByHash(hash);
        assertFalse(deleted);
    }
}
  