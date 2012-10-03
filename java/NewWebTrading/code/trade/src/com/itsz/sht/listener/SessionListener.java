/*
 * Created on 2005-3-31
 *
 */
package com.itsz.sht.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.MySessionContext;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.common.user.UserObject;
/**
 * @author wzzhan
 *
 */
public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener 
{
	private Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
		MySessionContext.AddSession(arg0.getSession());
		log_debug.info("****SessionListener>>> sessionCreated session id: "+session.getId());
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session=arg0.getSession();
	    MySessionContext.DelSession(session);
		log_debug.info("****SessionListener>>> sessionDestroyed session id: "+session.getId());
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		HttpSession session=arg0.getSession();
		if(arg0.getName().equals(Constants.USER)&&session.getAttribute(Constants.USER)!=null){
			UserObject userObj=(UserObject)session.getAttribute(Constants.USER);
			log_debug.info("****SessionListener>>> attributeAdded User: "+userObj.getChannelType()+"   "+userObj.getLoginName()+"   "+userObj.getSessionID());
			UserManagement.addOrUpdateUser(userObj);
		}
	}
	
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		if(arg0.getName().equals(Constants.USER)&&arg0.getValue()!=null){
			UserObject user=(UserObject)arg0.getValue();
			log_debug.info("****SessionListener>>> attributeRemoved User: "+user.getChannelType()+"   "+user.getLoginName()+"   "+user.getSessionID());
		    if(UserManagement.removeUser(user.getChannelType(),user.getLoginName(),user.getSessionID())){
		    	// ================================================================
		    	//��admin��ݿ���ɾ��ǰ�����û�?		
		    	AdminServiceDelegate.getInstance().processUserLogoutLog(user);
				// ================================================================
		    }
		    /*
			try {
				RTQInfo.deductAccessUnit(user,esDelegate);
			} catch (Exception e) {
				log_debug.info(e.getMessage());
			}*/
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
	    HttpSession session=arg0.getSession();
		if(arg0.getName().equals(Constants.USER)&&session.getAttribute(Constants.USER)!=null){
			UserObject userObj=(UserObject)session.getAttribute(Constants.USER);
			log_debug.info("****SessionListener>>> attributeReplaced User: "+userObj.getChannelType()+"   "+userObj.getLoginName()+"   "+userObj.getSessionID());
			UserManagement.addOrUpdateUser(userObj);
		}
	}
}

