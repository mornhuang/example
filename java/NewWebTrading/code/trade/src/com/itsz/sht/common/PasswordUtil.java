package com.itsz.sht.common;

/**
 * 
 * $Id: PasswordUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portal-head
 * @File:PasswordUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-26
 */
public class PasswordUtil {

    public PasswordUtil() {
    }

    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-11-26 下午03:15:05
     * @param pwd
     * @return
     */
    public static String EncPwd(String pwd) {
        if(pwd != null && pwd.trim().length() > 0) {
            pwd = pwd.trim();
            StringBuffer buf = new StringBuffer();
            char c = '\0';
            int tmp = 0;
            for(int i = 0; i < pwd.length(); i++) {
                c = pwd.charAt(i);
                tmp = c + (i + 1);
                buf.append((char)tmp);
            }
            return buf.toString();
        } else {
            return null;
        }
    }

    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-11-26 下午03:15:11
     * @param pwd
     * @return
     */
    public static String DecPwd(String pwd) {
        if(pwd != null && pwd.trim().length() > 0) {
            pwd = pwd.trim();
            StringBuffer buf = new StringBuffer();
            char c = '\0';
            int tmp = 0;
            for(int i = 0; i < pwd.length(); i++) {
                c = pwd.charAt(i);
                tmp = c - (i + 1);
                buf.append((char)tmp);
            }
            return buf.toString();
        } else {
            return null;
        }
    }
}
