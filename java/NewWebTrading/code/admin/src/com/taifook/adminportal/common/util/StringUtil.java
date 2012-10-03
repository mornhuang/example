/*
 *  Copyright (c) 2004 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.taifook.adminportal.common.util;

import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil {

    public static String fillLeadingCharacterToLength(String s, char toFill,
        int length) {

        String result = null;
        if (s != null) {
            if (s.length() >= length) {
                result = s;
            }
            else {
                StringBuffer sb = new StringBuffer();
                int di = length - s.length();
                for (int i = 0; i < di; i++) {
                    sb.append(toFill);
                }
                sb.append(s);
                result = sb.toString();
            }
        }
        return result;
    }

    public static String trimFirstCharacter(String pattern, String content) {

        String s = content;

        if (pattern != null && pattern.length() > 0 && content != null &&
            content.length() > 0) {
            int pos = 0;
            for (int i = 0; i < content.length(); i++) {
                pos = i;
                if (content.charAt(i) != pattern.charAt(0)) {
                    break;
                }
            }

            s = content.substring(pos, content.length());
        }

        return s;
    }

    public static String toUTF8(String src) {
        String s = null;
        try {
            s = new String(src.getBytes("UTF8"));
        }
        catch (Exception e) {
        }
        return s;
    }


    public static String delQuotationMark(String src) {
        String s = null;
        try {
            src = src.replace('\'',',');
            s = src.replace('\"',',');
        }
        catch (Exception e) {
        }
        return s;
    }

    public static String addLeadingZero(String inString, int length) {

        StringBuffer bs = new StringBuffer(inString);
        int i = 0;
        int size = bs.length();
        int diff = length - size;
        while (diff > i) {
            bs.insert(0, '0');
            i = i + 1;
        }
        return bs.toString();
    }

    /**
     * 
     */

    public static Vector strSplit(String s, String delim) {
        Vector vec = new Vector();
        if (s == null || s.equals("")) {
            return null;
        }
        if (s.indexOf(delim) == -1) {
            vec.add(s);
            return vec;
        }
        String beforStr = null;
        String temp = "";

        StringTokenizer st = new StringTokenizer(s, delim, true);
        while (st.hasMoreTokens()) {
            temp = st.nextToken();
            if (temp.equals(beforStr)) {
                vec.add("");
            }
            else {
                if (!temp.equals(delim)) {
                    vec.add(temp);
                }
            }
            beforStr = temp;
        }
        return vec;
    }

    public static String formatInstrCode(String s, int digit) {
        if (s == null) {
            return s;
        }
        s = s.trim();
        int size = s.length();
        String ss = s;
        for (int j = digit; j > size; j--) {
            ss = "0" + s;
            s = ss;
        }
        return ss;
    }

    public static String makeStatus(String oldStatus) {
        String newStatus = "message.ipo.status.status0";
        if ("received".equals(oldStatus)) {
            newStatus = "message.ipo.status.status1";
        }
        if ("Cancelled".equals(oldStatus)) {
            newStatus = "message.ipo.status.status2";
        }

        if ("0".equals(oldStatus)) {
            newStatus = "message.ipo.common.status0";
        }
        if ("1".equals(oldStatus)) {
            newStatus = "message.ipo.common.status1";
        }
        if ("2".equals(oldStatus)) {
            newStatus = "message.ipo.common.status2";
        }
        if ("3".equals(oldStatus)) {
            newStatus = "message.ipo.common.status3";
        }

        return newStatus;

    }

    public static String makeIPOStatus(String oldStatus) {
        String newStatus = "message.ipoself.status.status0";
        if ("OPEN".equals(oldStatus)) {
            newStatus = "message.ipoself.status.status1";
        }
        if ("CLOSE".equals(oldStatus)) {
            newStatus = "message.ipoself.status.status2";
        }

        return newStatus;

    }

    public static void main(String args[]) {

        Vector vec = strSplit(
            "IP05000033|02-0064995-30|100000|73888.76||62|1||90001,90005|",
            "|");
        System.out.println("third=" + vec.get(0));
        System.out.println("third=" + vec.get(1));
        System.out.println("third=" + vec.get(2));
        System.out.println("third=" + vec.get(3));
        System.out.println("third=" + vec.get(4));
        System.out.println("third=" + vec.get(5));
        System.out.println("third=" + vec.get(6));
        System.out.println("third=" + vec.get(7));
        System.out.println("third=" + vec.get(8));
        /* System.out.println(trimFirstCharacter("#", "###233232"));
         System.out.println(trimFirstCharacter("#", "###"));
         System.out.println(trimFirstCharacter("#", "233232"));
         System.out.println(trimFirstCharacter("#", ""));
         System.out.println(trimFirstCharacter("#", null));
         */

    }
}
