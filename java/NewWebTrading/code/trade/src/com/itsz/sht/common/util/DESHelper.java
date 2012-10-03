package com.itsz.sht.common.util;
/*
 <Descriptions>
 A sample Java app to encrypt a string (stored in plainText) using DES/CBC/PKCS5 for 3HK CCG integration.
 </Descriptions>
 
 <LEGAL>
 You may freely copy or modify this code to use in your application.
 You must not redistribute whole or in part this code without explicit written permission from 3HK.
 Copyright 2003,2004 3HK. All rights reserved.
 </LEGAL>
 
 <Notes>
     URLencode MUST be applied to the plainText (7-bit ASCII string) before passing to here for encryption.
     Not all parameters specified in CCG-SDS.pdf are mandatory.
 </Notes>
 
 <Usage>
 The suggested parameters to include in a typical CCG call are:
 
     id: unique for each 3HK partner (must obtain from 3HK)
     sasuffix: src address (must obtain from 3HK)
     sid: service id (must obtain from 3HK)
 
     aid: unique id for each kind of article to be delivered
     cprefid: unique reference number for each transaction
     surl: URI to redirect to when successfully billed the customer
     furl: URI to redirect to when billing action is failed
     type: s=streaming, d=download
 </Usage>
 
 */

// Details please refer to CCG-SDS.pdf included with the MATK.

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESHelper{
    public static void main(String []args){
    	System.out.println(EncryptStringForH3G("jsdlfjklasdjiojeljfklajdklfjskldjfohg"));
        try{
            byte [] iv = new byte[8];
            String h3gkey = "h3gcpkey";
            
            // plainText is the string to encrypt
            String plainText = "aid={articleid, unique}&cpcid=001&cprefid=20031214233230577&furl=http://cp.com/failurl&sasuffix={srcadd assign by h3g}&sid={sid assign by h3g}&surl=http://cp.com/success.jsp&type=s" + '\0';
            System.out.println("Text to be encrypted using [" + h3gkey + "] as key: " + plainText);
            
            // Do the encryption using h3gkey on plainText (7-bit ASCII)
            byte [] cipherText = encryptText(h3gkey, plainText.getBytes("utf-8"));
            System.out.println("DES/CBC/PKCS5 encrypted: " + new String(Base64.encode(cipherText)));
            
            // Do the decryption using h3gkey on cipherText (8-bit Binary)
            // simulate Base64 encoding/decode process as in real CCG call where the value is B64 encoded
            String decodedtext = decodeUrl(h3gkey ,Base64.decode((new String(Base64.encode(cipherText))).toCharArray()));
            System.out.println("Decrypted: " + decodedtext);
            
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
    static String h3gKey= "g85Hd97X";
  

    public static String getUrl(String sasuffix,String sid,String aid,String cprefid,String surl,String furl,String type){
    	String url="sasuffix="+sasuffix+"&sid="+sid+"&aid="+aid+"&cprefid="+cprefid+"&surl="+surl+"&furl="+furl+"&type="+type+ '\0';
    	System.out.println(url);
    	return url;
    
    }
    public static String getFullEncryptURL(String h3g_URL,String id,String url){
    	String fullURL=h3g_URL+id+"&amp;a="+EncryptStringForH3G(url);
    	System.out.println(fullURL);
    	return fullURL;
    }
    public  static String EncryptStringForH3G(String plainText){
    	try{
    		byte [] cipherText = encryptText(h3gKey, plainText.getBytes("utf-8"));
    		return  new String(Base64.encode(cipherText));
    	 } catch(Exception e){
            e.printStackTrace(System.out);
            return null;
        }
    	 
    }
    
    public static byte [] encryptText(String strKey, byte[] contents) throws Exception{
        
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        
        DESKeySpec dks = new DESKeySpec(strKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Key key = kg.generateKey();
        // System.out.println("Encoded Key : " + key.getEncoded());
        
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,key,new IvParameterSpec(Base64.decode("9XZ8QJAkaPo=".toCharArray())));
        byte[] plainText = contents;
        byte []ciphertext = cipher.doFinal(plainText);
        
        return ciphertext;
    }
    
    public static String decodeUrl(String strKey, byte [] contents) throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        
        DESKeySpec dks = new DESKeySpec(strKey.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Key key = kg.generateKey();
        // System.out.println("Encoded Key : " + key.getEncoded());
        
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,key,new IvParameterSpec(Base64.decode("9XZ8QJAkaPo=".toCharArray())),null);
        byte[] cipherText = contents;
        byte []plainText = cipher.doFinal(cipherText);
        
        return new String(plainText);
    }
}

/*
<Revision>
    2.1
</Revision>
<Authors>
    <Name id="Full name">
        Ryan Chan
    </Name>
    <Name id="Full name">
        Edwin Tam
    </Name>
</Authors>
 
*/
