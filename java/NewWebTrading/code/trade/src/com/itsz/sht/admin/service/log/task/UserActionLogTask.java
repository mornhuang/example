package com.itsz.sht.admin.service.log.task;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.common.AbstractTask;
import com.itsz.sht.admin.common.Task;
import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.itsz.sht.admin.service.OnLineUserMgrService;
import com.itsz.sht.admin.service.UserActionMgrService;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsOnlineuser;
import com.taifook.adminportal.model.CsOnlineuserKey;
import com.taifook.adminportal.model.CsUseractionlog;

public class UserActionLogTask extends AbstractTask {
	
	private static Log log = LogFactory.getLog(UserActionLogTask.class);
	
	private String loginName;
	private String channelID;
	private String channelType;
	private  HttpServletRequest request;
	private String sessionID;
	private String IP;
	private String actionType;
	
	public String getIP() {
		return IP;
	}

	public void setIP(String ip) {
		IP = ip;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public UserActionLogTask(){
		
	}
	
	public UserActionLogTask(String loginName, String channelID,
			String channelType,String sessionID,String IP, String actionType){
		this.loginName=loginName;
		this.channelID=channelID;
		this.channelType=channelType;
		this.request=request;
		this.sessionID=sessionID;
		this.IP=IP;
		this.actionType=actionType;
		
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public void runTask(){
		CsOnlineuser log = new CsOnlineuser();
		CsOnlineuser onlineuser = new CsOnlineuser();
		CsOnlineuserKey key = new CsOnlineuserKey();
		//key.setChannelcode(channelType+ com.taifook.adminportal.common.Constants.DELIMITER+ channelID);
		key.setChannelcode(channelType);
		key.setUserid(loginName);
		onlineuser.setId(key);
		onlineuser.setLogintime(new Date());
		onlineuser.setSessionid(sessionID);

		CsUseractionlog actionlog = new CsUseractionlog();
		actionlog.setUserid(loginName);
		actionlog.setChannelType(channelType);
		actionlog.setIp(IP);
		actionlog.setActionid(this.actionType);
		actionlog.setOperationtime(new Date());
		
		this.log.info("logging userAction:  loginName:"+this.loginName+"  channelType:"+this.channelType+"  sessionID:"+this.sessionID);
		
		if(this.actionType.equals(Constants.PW_LOGIN_ACTION) || this.actionType.equals(Constants.EC_LOGIN_ACTION)){
			OnLineUserMgrService.getInstance().saveOnlineuser(onlineuser);
		}
		
		UserActionMgrService.getInstance().saveUserAction(actionlog);
		
	}

}
