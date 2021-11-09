/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.userdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * UserAuth.java
 * 9th November 2021
 * Security Fundamentals and Development CA1 Part 2
 * @authors: Group F - Ruby Lennon (x19128355), Emanuela Dudau (x19180675)
 */

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
            
            //userchoice switch statement
            switch (userChoice){
                case "1"://if userchoice equals 1
                    CreateUser(username, password);//create new user using username and password
                    break;
                case "2"://if userchoice equals 2
                    CheckUser(username, password);//check if user exists using username and password
                    break;
            }           
        }
         
    private static void CreateUser(String username, String password){
        try{            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userstore", "root", "password");//connect to userstore schema/database on localhost
            String sql = "INSERT INTO users (user_name, password) VALUES (?, ?)";//SQL statement to create new user in users table
            PreparedStatement statement = con.prepareStatement(sql);//prepared statement is more secure than plain statement against SQL injection
            statement.setString(1, username);
            statement.setString(2, password);
            
            statement.executeUpdate();//execute the statement
            
            con.close();//close connection
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private static void CheckUser(String username, String password){
        try{            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userstore", "root", "password");//connect to userstore schema/database on localhost
            String sql = "SELECT * FROM users where user_name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(sql);//prepared statement is more secure than plain statement against SQL injection
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet results = statement.executeQuery();
            
            if(results.next()){
                System.out.println("You have successfully logged in.");
            }
            
            con.close();//close connection
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
