/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;

import com.itsz.sht.common.Constants;

public class LangUtil {
	
	public static String getLang(String lang) {
        if (Constants.LANG_GB.equals(lang)) {
        	return "ZH_CN";
        } else if (Constants.LANG_ENG.equals(lang)) {
        	return "EN";
        } else {
        	return "ZH_TW";
        }
	}
	
	public static String mcs2admin(String lang) {
		if (Constants.LANG_ENG.equals(lang)) {
			return "en_US";
		} else
			if (Constants.LANG_GB.equals(lang)) {
				return "zh_CN";
			} else {
				return "zh_TW";
			}
	}

	public static String mcs2sp(String lang) {
		if (Constants.LANG_ENG.equals(lang)) {
			return "01";
		} else
			if (Constants.LANG_GB.equals(lang)) {
				return "03";
			} else {
				return "02";
			}
	}

	public static Locale lang2locale(String lang) throws Exception {
		if (lang == null) {
			throw new NullPointerException();
		}
		if (lang.equals("zh_CN")) {
			return Locale.SIMPLIFIED_CHINESE;
		} else
			if (lang.equals("en_US")) {
				return Locale.US;
			} else {
				return Locale.TRADITIONAL_CHINESE;
			}

	}

	public static String mcs2iq(String lang) {
		if (Constants.LANG_ENG.equals(lang)) {
			return "EN";
		} else
			if (Constants.LANG_GB.equals(lang)) {
				return "SC";
			} else {
				return "TC";
			}
	}

	public static String mcs2qs(String lang) {
		if (Constants.LANG_ENG.equals(lang)) {
			return "en";
		} else
			if (Constants.LANG_GB.equals(lang)) {
				return "zh_CN";
			} else {
				return "zh_TW";
			}
	}

	public static String locale2lang(Locale locale) {
		if (locale == null) {
			return "BIG5";
		}
		if (Locale.US.toString().equals(locale.toString())) {
			return "ENG";
		}
		if (Locale.SIMPLIFIED_CHINESE.toString().equals(locale.toString())) {
			return "GB";
		}
		return "BIG5";
	}

	public static String locale2eMessage(String lang) {
		if (lang == null) {
			return "zh_TW";
		}
		if (Constants.LANG_ENG.equals(lang)) {
			return "en_US";
		}
		if (Constants.LANG_GB.equals(lang)) {
			return "zh_CN";
		}
		return "zh_TW";
	}	
	
    public static String GetRtqLang(HttpServletRequest request){
		Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        String lang = "chi"; //default
        if (locale != null) {
            if ("en_US".equals(locale.toString())) {
                return "eng";
            }
            else if ("zh_CN".equals(locale.toString())) {
                return "chn";
            }
        }
        return lang;
    }
    
    public static String GetEtnetLang(HttpServletRequest request){
		Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        String lang = "chi"; //default
        if (locale != null) {
            if ("en_US".equals(locale.toString())) {
                return "eng";
            }else if ("zh_CN".equals(locale.toString())) {
                return "chn";
            }
        }
        return lang;
    }
    
    public static String getNewEtnetLang(HttpServletRequest request){
		Locale locale = (Locale) request.getSession().getAttribute(Globals.LOCALE_KEY);
        String lang = "tc"; //default
        if (locale != null) {
            if ("en_US".equals(locale.toString())) {
                return "eng";
            }else if ("zh_CN".equals(locale.toString())) {
                return "sc";
            }
        }
        return lang;
    }
}
