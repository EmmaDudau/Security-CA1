/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.userdatabase;

//imports
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/*
 * PrivateKeyEncryption.java
 * 9th November 2021
 * Security Fundamentals and Development CA1 Part 2
 * @authors: Group F - Ruby Lennon (x19128355), Emanuela Dudau (x19180675)
 * Description - AES Symmetric key algorithm (private key), 1 key used to encrypt and decrypt plain text
 * Related database - userstore (userstore.sql database schema)
 */

//Lab recording: https://studentncirl.sharepoint.com/:v:/r/sites/SecurityFundamentalsandDevelopmentBSHCE3MoodleTeam-21/Shared%20Documents/General/Recordings/View%20Only/Meeting%20in%20_General_-20211028_200546-Meeting%20Recording.mp4?csf=1&web=1&e=Csbejh

//Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8 Download
//Download Link - https://www.oracle.com/java/technologies/javase-jce8-downloads.html

public class PrivateKeyEncryption {
    public static void main(String[] args){
        try{
            
            //plain text
            String plainText = "Some Random Text";//plain text to be encrypted
            byte[] plainTextBytes = plainText.getBytes();//create a byte array and store the byte value of plainText
            
            //check max key length for AES Algorithm - if equals 128 bytes, updated local_policy.jar file may need to be downloaded for Java V.8 and added to Java JRE Security Library
            int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            System.out.println("MaxAllowedKeyLength=[" + maxKeyLen + "].");
            
            //key - AES Encryption requires a minimum key length of 128 bytes (128, 192, or 256 bytes key length required)
            String key = "IDeCVaBRGoWE1Xb+X4MdZrq7UXgB3M58m3Xpdk4b+uU=";//key to encrypt/decrypt the data
            Key k = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
            
            System.out.println("Key: " + new String(k.getEncoded(), StandardCharsets.UTF_8));
            
            //Cipher class provides the functionality of a cryptographic cipher for encryption and decryption.
            
            //Encryption
            Cipher cipher = Cipher.getInstance("AES");//AES Cipher transformation selected
            
            cipher.init(Cipher.ENCRYPT_MODE, k);//Cipher initialisation requires mode(encrypt or decrypt) & key
            
            byte[] cipherText = cipher.doFinal(plainTextBytes);//create array to store encryped plain text bytes
            
            String cipherString = new String(cipherText, StandardCharsets.UTF_8);//create a new string to store the encrypted bytes
            
            System.out.println("Encrypted String: " + cipherString);//print the encrypted string
            
            //Decryption
            Cipher decryptCipher = Cipher.getInstance("AES");//AES Cipher transformation selected
            
            decryptCipher.init(Cipher.DECRYPT_MODE,k);//Cipher initialisation requires mode(encrypt or decrypt) & key
            
            byte[] decryptedText = decryptCipher.doFinal(cipherText);//create array to store decrypted plain text bytes
            
            String decryptedString = new String(decryptedText, StandardCharsets.UTF_8);//create a new string to store the decrypted bytes
            
            System.out.println("Decrypted String: " + decryptedString);//print the decrypted string
            
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(NoSuchPaddingException e){
            e.printStackTrace();
        }catch(InvalidKeyException e){
            e.printStackTrace();
        }catch(IllegalBlockSizeException e){
            e.printStackTrace();
        }catch(BadPaddingException e){
            e.printStackTrace();
        }
    }
}
