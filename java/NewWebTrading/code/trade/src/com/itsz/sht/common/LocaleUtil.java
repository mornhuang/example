/**
 * <p>Title:Channels Server for STT2.5</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;

import com.itsz.sht.common.Constants;

public class LocaleUtil {

    public static void setLocale(HttpServletRequest request,String lang) {
        System.out.println("+++++++++++++++++++set locale ++++++++++++++++");
        HttpSession session = request.getSession();
        Locale defaultLocle;
        if (Constants.LANG_ENG.equals(lang)) {
            defaultLocle = Locale.US;
        } else if (Constants.LANG_GB.equals(lang)) {
            defaultLocle = Locale.SIMPLIFIED_CHINESE;
        } else {
            defaultLocle = Locale.TRADITIONAL_CHINESE;
        }
        session.setAttribute(Globals.LOCALE_KEY,defaultLocle);       
    }
}
