/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nci.encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * FileIOWithText.java
 * 14th November 2021
 * Security Fundamentals and Development CA1 Part 2
 * @authors: Group F - Ruby Lennon (x19128355), Emanuela Dudau (x19180675)
 * Description - Write a string to a file, read a string from a file
 */

//Tutorial links:
//File I/O with Text: Part 2 - https://www.youtube.com/watch?v=dDsCP3dyspE
//File I/O with Text: Part 3 - https://www.youtube.com/watch?v=N2ZTDQG7oLU
//File I/O with Text: Part 4 - https://www.youtube.com/watch?v=zjyTVq6xO8E
//File I/O with Text: Part 5 - https://www.youtube.com/watch?v=2OdGRnbRqCo (to append to a file instead of overwriting each time - not implemented in this class)

public class FileIOWithText {
    public static void main(String[] args){
        //writing text to the file
        
        //declare variables
        File outFile;
        FileWriter fw;
        BufferedWriter bw;
        String message = "This is a test message";
        
        try{
            //create objects
            outFile = new File("test.txt");//contains name of file we want to write the data to
            fw = new FileWriter(outFile);//encase file in character stream
            bw = new BufferedWriter(fw);//encase character stream in buffered stream

            bw.write(message);//write the message to the file

            bw.close();//close the buffered stream
            System.out.println("Text successfully written to file.");
        }catch(IOException e){
            System.out.println("Error writing to file: ");
            e.printStackTrace();
        }
        
        //reading the text from the file
        
        //declare variables
        File inFile;
        FileReader fr;
        BufferedReader br;
        String recievedMessage;
        
        try{
            inFile = new File("test.txt");//contains name of file we want to read the data from
            fr = new FileReader(inFile);//encase file in character stream
            br = new BufferedReader(fr);//encase character stream in buffered stream
            
            recievedMessage = br.readLine();
            System.out.println("Text read from '" + inFile + "' file: " + recievedMessage);
        }catch(IOException e){
            System.out.println("");
            e.printStackTrace();
        }
    }
    
}
