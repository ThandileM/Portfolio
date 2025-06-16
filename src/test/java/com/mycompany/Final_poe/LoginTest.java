/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.Final_poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class LoginTest {
    private Login login;
   
    @BeforeEach
    public void setUp() {
    login = new Login("Kyle", "", "kyl_1", "Ch@sec8ke99", "083896897");
    }
    
    @Test
    public void checkUsername() {
      
        assertEquals(true, login.checkUsername("kyl_1"));
        assertEquals(false, login.checkUsername("kyle!!!!!!!"));
        
    }
    
    @Test
    public void checkcellPhoneNumber(){
        
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("cellPhoneNumber"));
    }

   @Test
    public void checkPasswordComplexity() {
      
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
}

    @Test
    public void loginUser() {
        
        assertTrue(login.checkUsername("kyl_1"));
        assertFalse(login.checkCellPhoneNumber("0838968976"));
    }
}
    
  