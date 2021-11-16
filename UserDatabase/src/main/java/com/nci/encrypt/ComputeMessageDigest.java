/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nci.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * ComputeMessageDigest.java
 * 14th November 2021
 * Security Fundamentals and Development CA1 Part 2
 * @authors: Group F - Ruby Lennon (x19128355), Emanuela Dudau (x19180675)
 * Description - Compare two hash values of two different strings to see if they match
 */

//Lab recording: https://studentncirl.sharepoint.com/:v:/r/sites/SecurityFundamentalsandDevelopmentBSHCE3MoodleTeam-21/Shared%20Documents/General/Recordings/View%20Only/Security%20Fundamentals%20and%20Development_Remote%20Lec-20211021_200815-Meeting%20Recording.mp4?csf=1&web=1&e=wAv0iv

public class ComputeMessageDigest {
    public static void main(String[] args){       
        //decalre variables
        MessageDigest sha = null;//MessageDigest class provides applications the functionality of a message digest algorithm, such as SHA-1 or SHA-256. Message digests are secure one-way hash functions that take arbitrary-sized data and output a fixed-length hash value.
        
        try{
            sha = MessageDigest.getInstance("SHA-1");//define which algorithm to use - MD5 Algorithm can be used instead also
        }catch(NoSuchAlgorithmException e){//catch in case algorithm is not found
            System.out.println("No such algorithm");
        }
        
//        System.out.println("Algorithm: " + sha.getAlgorithm());//print algorithm that is being used
//        System.out.println("Algorithm Provider: " + sha.getProvider());//print the algorithm provider that is being used
               
        String s1 = "Some Random Text";
        
        byte b1[] = s1.getBytes();//convert the string s to bytes and store in bytes array
        
        //method 1
        //sha.update(b);//updates the digest using the specified array of bytes.
        //sha.digest();//completes the hash computation by performing final operationssuch as padding.
        
        //method 2
        byte[] hash1 = sha.digest(b1);//performs a final update on the digest using the specified arrayof bytes, then completes the digest computation.
        
        System.out.println("Hash of '" + s1 + "' is:");
        
        for(byte h: hash1){
            System.out.print(h + " ");
        }
        
        String s2 = "Some Random Text.";
        
        byte b2[] = s2.getBytes();//convert the string s to bytes and store in bytes array
        
        //method 1
        //sha.update(b);//updates the digest using the specified array of bytes.
        //sha.digest();//completes the hash computation by performing final operationssuch as padding.
        
        //method 2
        byte[] hash2 = sha.digest(b2);//performs a final update on the digest using the specified arrayof bytes, then completes the digest computation.
        
        System.out.println("\n\nHash of '" + s2 + "' is:");
        
        for(byte h: hash2){
            System.out.print(h + " ");
        }
    }
}
