package com.itsz.sht.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.user.UserObject;

/**
 * 
 * $Id: SessionUtil.java,v 1.1 2010/11/09 03:57:26 kyzou Exp $
 * @Project:portalBranch41
 * @File:SessionUtil.java
 * @Description:
 */
public class SessionUtil {

	public static String getChannelID(HttpServletRequest request) {
        String channelID = null;
        UserObject user = (UserObject)request.getSession().getAttribute(Constants.USER);
        if (user != null) {
           channelID = user.getChannelID();
        }
        return channelID;
	}
	
	public static void setSessionLocation(HttpServletRequest request, String lang){
		
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-12-12 10:39:34
	 * @param session
	 * @param user
	 */
	public static final void setSessionUser(HttpSession session, UserObject user){
		session.setAttribute(Constants.USER, user);
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-12-12 10:39:38
	 * @param session
	 * @return
	 */
	public static final UserObject getSessionUser(HttpSession session){
		Object obj=session.getAttribute(Constants.USER);
		if(obj != null && obj instanceof UserObject){
			return (UserObject)obj;
		}
		return null;
	}
	
}
