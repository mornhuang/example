/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.common.util;

import java.util.Locale;

import com.itsz.common.Constants;


public class LangUtil {
    public static String mcs2admin(String lang) {
        if (Constants.LANG_ENG.equals(lang)) {
            return "en_US";
        } else if (Constants.LANG_GB.equals(lang)) {
            return "zh_CN";
        } else {
            return "zh_TW";
        }
    }
    public static String mcs2sp(String lang) {
        if (Constants.LANG_ENG.equals(lang)) {
            return "01";
        } else if (Constants.LANG_GB.equals(lang)) {
            return "03";
        } else {
            return "02";
        }
    }
    public static Locale lang2locale(String lang) throws Exception{
    	if(lang==null){
    		throw new NullPointerException();
    	}
    	if(lang.equals("zh_CN")){
    		return Locale.SIMPLIFIED_CHINESE;
    	}else if(lang.equals("en_US")){
    		return Locale.US;
    	}else {
    		return Locale.TRADITIONAL_CHINESE;
    	}
    	
    }
    public static String mcs2iq(String lang) {
        if (Constants.LANG_ENG.equals(lang)) {
            return "EN";
        } else if (Constants.LANG_GB.equals(lang)) {
            return "SC";
        } else {
            return "TC";
        }
    }
    public static String mcs2qs(String lang) {
        if (Constants.LANG_ENG.equals(lang)) {
            return "en";
        } else if (Constants.LANG_GB.equals(lang)) {
            return "zh_TW";
        } else {
            return "zh_TW";
        }
    }
    public static String locale2lang(Locale locale){
        if(locale==null){
            return "BIG5";
        }
        if(Locale.ENGLISH.getLanguage().equals(locale.getLanguage())){
            return "ENG";
        }
        if(Locale.SIMPLIFIED_CHINESE.getLanguage().equals(locale.getLanguage())){
            return "GB";
        }
        return "BIG5";
    }
}

