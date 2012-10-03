/*
 * Created on 2005-7-20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.listener;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserObject;

/**
 * @author lmzhang
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SessionManagement {
	//private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_COMMON);
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);

	//��session��ѹ������
	public static void setSessionAttribute(HttpSession session ,String attrName,Object attrValue){
        log_debug.info("====session===========set "+attrName+" to "+attrValue);
	    session.setAttribute(attrName,attrValue);
	}
	
	//��session��ɾ������
	public static void removeSessionAttribute(HttpSession session ,String attrName){
        if(session.getAttribute(attrName)!=null){
            log_debug.info("====session===========remove "+attrName);
            session.removeAttribute(attrName);
        }
	}

	//��user����ѹ��session
	public static void setUserObject(HttpSession session ,UserObject user){
        log_debug.info("=====session==========set UserObject and loginname is " +user.getLoginName());
	    session.setAttribute(Constants.USER,user);
	}

	//��session��ɾ��user����
	public static void removeUserObject(HttpSession session){
        if(session.getAttribute(Constants.USER)!=null){
            log_debug.info("=====session==========remove UserObject ");
            session.removeAttribute(Constants.USER);
        }
	}
	
	//����session����
	public static void setLocale(HttpSession session){
		Locale locale=(Locale)session.getAttribute(Constants.defaultLocaleAttributeName);
		if(locale==null||!locale.toString().equals("en_US")){
		    locale=Locale.TRADITIONAL_CHINESE;
		    session.setAttribute(Constants.defaultLocaleAttributeName,locale);
		}
		System.out.println(">>>>>>>>>>>>Locale="+ locale);
	}
	
	//�ж��Ƿ�Ϊreload���ظ��ύ
	public static boolean isReload(HttpSession session,String validation){
	    String val=(String) session.getAttribute(Constants.VALIDATION);
	    if(val!=null&&val.equals(validation)){
	      return true;
	    }
	    session.setAttribute(Constants.VALIDATION,validation);
	    return false;
		}
	
}
