/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nci.encrypt.userdatabase;

//imports

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

/*
 * UserAuth.java
 * 9th November 2021
 * Security Fundamentals and Development CA1 Part 2
 * @authors: Group F - Ruby Lennon (x19128355), Emanuela Dudau (x19180675)
 * Description - Encrypted (Hash / SHA-1 MessageDigest Algorithm) user creation / login
 * Related database - userstore (userstore.sql database schema)
 */

//Lab recording: https://studentncirl.sharepoint.com/:v:/r/sites/SecurityFundamentalsandDevelopmentBSHCE3MoodleTeam-21/Shared%20Documents/General/Recordings/View%20Only/Meeting%20in%20_General_-20211028_200546-Meeting%20Recording.mp4?csf=1&web=1&e=Csbejh

public class UserAuth {
    public static void main(String[] args){
            //declare variables
            String userChoice, username, password;
        
            //create scanner object
            Scanner scan = new Scanner(System.in);
            
            //ask what the user wants to do
            System.out.println("Please select 1 of the following options");
            System.out.println("1: Create User");
            System.out.println("2: Login");
            //get user input 
            userChoice = scan.nextLine();
            
            //get new or existing username and password to create a new user or to login to an existing account
            System.out.println("Please enter username");//get username
            username = scan.nextLine();
            System.out.println("Please enter password");//get password
            password = scan.nextLine();
            
            String encryptedPassword = HashPassword(password);//use the HashPassword function to convert the password to Hash and store in new String
            
            //userchoice switch statement
            switch (userChoice){
                case "1"://if userchoice equals 1
                    CreateUser(username, encryptedPassword);//create new user using username and encrypted password
                    break;
                case "2"://if userchoice equals 2
                    CheckUser(username, encryptedPassword);//check if user exists using username and password
                    break;
            }           
        }
    
    //method to encrypt password using Hash (SHA-1 MessageDigest Algorithm)
    public static String HashPassword(String password){
        MessageDigest sha = null;//Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value
        try{
            sha = MessageDigest.getInstance("SHA-1");//SHA-1 MessagDigest Algorithm selected
        }catch(NoSuchAlgorithmException e){
            System.out.println("No such algorithm");
        }
        
        byte b[] = password.getBytes();//Get they byte value of password 
        
        byte[] hash = sha.digest(b);//byte array used to store the digested password using SHA-1 algorithm
        
        String encryptedPassword = new String(hash, StandardCharsets.UTF_8);//converting the hash to a string and storing in encryptedPassword
        
        return encryptedPassword;//return encryptedPassword
        
    }
    
    //method to create a new user in the database
    private static void CreateUser(String username, String password){
        try{            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userstore", "root", "RLNCIsqlPass123*");//connect to userstore schema/database on localhost
            String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";//SQL statement to create new user in users table
            PreparedStatement statement = con.prepareStatement(sql);//prepared statement is more secure than plain statement against SQL injection
            statement.setString(1, username);//set the first statement parameter (?) to the username value
            statement.setString(2, password);//set the second statement parameter (?) to the password value
            
            statement.executeUpdate();//execute the statement
            
            con.close();//close connection
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    //method to check if a user exists in the database
    private static void CheckUser(String username, String password){
        try{            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userstore", "root", "RLNCIsqlPass123*");//connect to userstore schema/database on localhost
            String sql = "SELECT * FROM users where user_name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(sql);//prepared statement is more secure than plain statement against SQL injection
            statement.setString(1, username);//set the first statement parameter (?) to the username value
            statement.setString(2, password);//set the second statement parameter (?) to the password value
            
            ResultSet results = statement.executeQuery();//execute the statement
            
            if(results.next()){
                System.out.println("You have successfully logged in.");
            }
            
            con.close();//close connection
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
