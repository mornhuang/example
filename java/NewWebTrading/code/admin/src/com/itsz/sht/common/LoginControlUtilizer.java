/**
 * Copyright (c) 2003 Tai Fook Securities Group Limited.
 * All rights reserved.
 *
 * This file contains the valuable properties of Tai Fook Securities
 * Group Limited, embodying substantial creative efforts and confidential
 * information, ideas and expressions. No part of this file may be
 * reproduced or distributed in any form or by any means, or stored
 * in a data base or a retrieval system, without the prior written
 * permission of Tai Fook Securities Group Limited.
 *
 */

package com.itsz.sht.common;

import java.util.*;

public class LoginControlUtilizer {
    /*  0-9   	048-057
        A-Z 	065-090
        a-z	    097-122
    */
    public static String genPwd(int noOfChar, boolean allowChar) {
        String password = "";
        Random pwdGenerator = new Random();
        int randomInt;

        int i = 0;

        while (i < noOfChar){
            if (allowChar){
                randomInt =  pwdGenerator.nextInt(74);
                randomInt = randomInt + 48;
                if(!((randomInt > 57 && randomInt < 65 )||(randomInt > 90 && randomInt < 97))) {
                    i = i + 1;
                    password=password + (char)randomInt;
                }
            }else{
                password=password + pwdGenerator.nextInt(9);
            }
        }
        return password;
    }


    //Modify the password generation method to generate password according to the type
    public static String genPwd(int noOfChar, int passwordType) {
        String password = "";
        Random pwdGenerator = new Random();
        int randomInt;

        int i = 0;

        while (i < noOfChar){
            //Password contains numeric value only
            if (passwordType==0){
                password=password + pwdGenerator.nextInt(9);
            }

            //Password contains alpha numeric value
            if (passwordType==1){
                randomInt =  pwdGenerator.nextInt(74);
                randomInt = randomInt + 48;
                if(!((randomInt > 57 && randomInt < 65 )||(randomInt > 90 && randomInt < 97))) {
                    i = i + 1;
                    password=password + (char)randomInt;
                }
            }

            //Passsword contains numeric and capital letters
            if (passwordType==2){
                randomInt =  pwdGenerator.nextInt(42);
                randomInt = randomInt + 48;
                if(!(randomInt > 57 && randomInt < 65 )) {
                    i = i + 1;
                    password=password + (char)randomInt;
                }
            }


            //Passsword contains numeric and capital letters
            if (passwordType==3){
                randomInt =  pwdGenerator.nextInt(74);
                randomInt = randomInt + 48;
                if(!((randomInt > 57 && randomInt < 97))) {
                    i = i + 1;
                    password=password + (char)randomInt;
                }
            }

        }
        return password;
    }



}
