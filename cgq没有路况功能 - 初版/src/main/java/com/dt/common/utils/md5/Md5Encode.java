package com.dt.common.utils.md5; 
import java.security.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import java.security.NoSuchAlgorithmException; 

/*
 *  MD5ENCODE.java
 *  @author: GYG
 *  time: 2006-12-12 10:11:49 
 *  Description: A progrm to encrypt a string using MD5 or SHA-1 or SHA-256.
 */


public class Md5Encode {
	    
        public Md5Encode() {}
        
        public String Encrypt(String PassW,String EncName) {
                //parameter PassW is a string will be encrypted,
                //parameter EncName is the algorithm name will be used.
				//parameter EncPage is the algorithm name will be used in page.
				//EncName default to "MD5"
				//EncPage default to "ISO-8859-1"
             MessageDigest m_digest=null;


             try {
                        if ((EncName==null)&&(EncName.equals(""))) {
                                EncName="MD5";
                        }

                        m_digest=MessageDigest.getInstance(EncName);
                   
		                byte bytesForDigest[] = PassW.getBytes();
                        byte bytesFromDigest[] = m_digest.digest(bytesForDigest);      
 
	     
                        StringBuffer result;
        
                        result = new StringBuffer();
                        for(int i = 0; i < bytesFromDigest.length; i++)
                        {
                               String addZerro = Integer.toHexString(128 + bytesFromDigest[i]); 

		                       if(addZerro.length() < 2) {
                                       addZerro = "0" + addZerro;
							   } 
                               result.append(addZerro);
                         }
                         
						 return result.toString();

             }
             catch (NoSuchAlgorithmException e) {
                    System.out.println("Invalid algorithm.");
                    return null;
             }

 }

        
        public static void main(String[]args) {
            Md5Encode te=new Md5Encode();
            String PassW="123456";
            System.out.println("Source String:"+PassW);
            System.out.println("Encrypted String:");
            //System.out.println("Use Def:"+te.Encrypt(PassW,null,null));
            System.out.println("Use MD5:"+te.Encrypt(PassW,"MD5"));
            System.out.println("Use SHA:"+te.Encrypt(PassW,"SHA-1"));
            System.out.println("Use SHA-256:"+te.Encrypt(PassW,"SHA-256"));
        }
}

