/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Final_poe;

/**
 *
 * @author RC_Student_lab
 */
public class Login {
     //Attributes
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;
    
    //Constructor
     Login(String firstName, String lastName, String username, String Password, String cellPhoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = Password;
        this.cellPhoneNumber = cellPhoneNumber;
       
    }
     public  Login(String firstName, String lastName,String username){
         this(firstName, lastName, username, "","");
     }
     
    
   //Getter method 
    public String getfirstName(){
        return firstName;
    }
    public String getlastName(){
        return lastName;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getCellNumber(){
        return cellPhoneNumber;
    }
   
    
   //Setter method
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword (String password){
        this.password = password;
    }
    public void setCellPhoneNumber(String CellPhoneNumber){
        this.cellPhoneNumber = CellPhoneNumber;
    } 
  
    
    //instant method(non-static)
    public boolean checkUsername(String username){
        return username.contains("_")&& username.length()<=5;
    }
    public boolean checkPasswordComplexity(String password){
        return password.length()>= 8 &&
               password.matches(".*[A-Z].*")&&
               password.matches(".*[0-9].*")&&
               password.matches(".*[!@#$%^&*()].*");
    }
    public boolean checkCellPhoneNumber(String cellPhoneNumber){
        return cellPhoneNumber != null &&
               cellPhoneNumber.startsWith("+27")&&
               cellPhoneNumber.length()==12 &&
               cellPhoneNumber.matches("\\+27\\d{9}");
    } 
  
    //void method (no return)
    public void displayFullName(){
        System.out.println("Welcome" + firstName + "" + lastName + "It is great to see you again.");
    }
   //register user
     public String registerUser(){    
        if(checkUsername(username)){
            System.out.println("Username successfully captured.");
        }else {
            System.out.println("Username is not correctly formatted; please ensure that the passward contains an underscore and is no more that five characters in length");
        }
        if(checkPasswordComplexity(password)){
            System.out.println("Password successfuly captured.");
        }else{
            System.out.println("Password is not correctlt formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }
        if(checkCellPhoneNumber(cellPhoneNumber)){
            System.out.println("Cell phone number successfull added.");
        }else{
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }
      
        return "User registered successfully";
        
     }
    
    //LoginUser
    public boolean loginUser(String username, String password) {
     return this.username.equals(username) && this.password.equals(password);
    }


    //Login Status message
    public String returnLoginStatus(String username, String password, String cellPhoneNumber){
       if (loginUser(username, password)){
           return "Welcome " + " " + lastName + ", it is great to see you again!";
       }else {
           return "Username or password is incorrect, please try again.";
       }
    }
}


