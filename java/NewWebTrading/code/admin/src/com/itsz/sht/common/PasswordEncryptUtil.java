package com.itsz.sht.common;

import org.apache.log4j.Logger;


public class PasswordEncryptUtil {

	private final static Logger log = Logger.getLogger(PasswordEncryptUtil.class);

public PasswordEncryptUtil() { }
    //The same algorithm  with MIS
    public static String encrypt(String password) {
        String encryptedPassword = "";
        int charValue;

        if(password.length()!=0){
            for (int i=0; i< password.length(); i++){
                charValue = (int)(password.charAt(i)) + i + 1;
                encryptedPassword = encryptedPassword + (char)charValue;
            }
        }else{
            encryptedPassword = null;
        }

        return encryptedPassword;
    }
    public static String decrypt(String password){
        String decryptedPassword = "";
        int charValue;
        if(password.length()!=0){
            for (int i=0; i< password.length(); i++){
                charValue = (int)(password.charAt(i)) - i- 1;
                decryptedPassword = decryptedPassword + (char)charValue;
            }
        }else{
            decryptedPassword = null;
        }
        return decryptedPassword;
    }
    public static void main(String args[]){
        String pw = "admin";
        String encrypt = encrypt(pw);
        String decrypt = decrypt("1ZGutu");
        System.out.println("encrypt="+encrypt);
        System.out.println("decrypt="+decrypt);
        System.out.println("==="+pw.equals(decrypt));
    }
}
