package com.taifook.adminportal.proxy.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.util.DataFormatUtil;
import com.taifook.adminportal.dao.OnLineUserDAO;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.dao.SystemMonitorDAO;
import com.taifook.adminportal.dao.UserActionDAO;
import com.taifook.adminportal.model.CsOnlineuser;
import com.taifook.adminportal.model.CsOnlineuserKey;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.model.CsServicemonitor;
import com.taifook.adminportal.model.CsUseractionlog;
import com.taifook.adminportal.proxy.ProxyAdminPortalDAO;
/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public final class ProxyAdminportalImpl implements ProxyAdminPortalDAO {
	private Log log = LogFactory.getLog(this.getClass()); 
	 
	public String readParameter(String key) {
		String value="";	
		try {
			CsParameter parameter=(CsParameter)((ParameterDAO)ServiceManager.getInstance()
			        .getService("com.taifook.adminportal.service.ParameterService"))
			        .findById(key);
			value=parameter.getValue();
		} catch (Exception e) {
			log.error("ProxyAdminportalImpl-readParameter:Exception!");
			log.error(e.getMessage());
			value=null;
	
		}
		return value;
	}

	public String readWebStatus() {
		return readParameter(Constants.CHANNEL_CODE_WEB);
	}
	public String readSttStatus() {	
		return readParameter(Constants.CHANNEL_CODE_STT);
	}
	public String read3GStatus() {	
		return readParameter(Constants.CHANNEL_CODE_3G);
	}
	
	public boolean isSystemMonitor(){
		String temp=null;
		boolean switchmonitor=Constants.DEFAULT_SYSTEM_MONITOR_SWITCH;
		try {
			temp=readParameter(Constants.SYSTEM_MONITOR_SWITCH);				
			if(temp.equalsIgnoreCase("open"))
				switchmonitor=true;
			else
				switchmonitor=false;
			
		   } catch (Exception e) {
			  log.error("ProxyAdminportalImpl-getSystemmonitorConfig: switch is Exception!");
			  log.error(e.getMessage());
			  switchmonitor=Constants.DEFAULT_SYSTEM_MONITOR_SWITCH;
		}
		return switchmonitor;
	}
	public int getSystemMonitorPeriod(){
		String period=null;
		int periodtime=Constants.DEFAULT_SYSTEM_MONITOR_PERIOD;
		try {
			period=this.readParameter(Constants.SYSTEM_MONITOR_PERIOD);
			periodtime=Integer.parseInt(period);
		   } catch (Exception e) {
			  log.error("SystemMonitor-getPeriodConfig: period is Exception!");
			  log.error(e.getMessage());
			  periodtime=Constants.DEFAULT_SYSTEM_MONITOR_PERIOD;
		}
		return periodtime;
	}
	
	public String readBroadcast(String channelcode) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOnLineUser(String userid, String channelcode ,String sessionid) {
		CsOnlineuser onlineuser=new CsOnlineuser();
		CsOnlineuserKey id=new CsOnlineuserKey();
		try {
		  id.setUserid(userid);
		  id.setChannelcode(channelcode);
			
		  onlineuser.setId(id);
		  onlineuser.setSessionid(sessionid);
		  onlineuser.setLogintime(new Date());
		   
		  ((OnLineUserDAO)ServiceManager.getInstance()
				  .getService("com.taifook.adminportal.service.OnLineUserService"))
			      .saveOrUpdate(onlineuser);
		  
		} catch (Exception e) {
			log.error("ProxyAdminportalImpl-saveOnLineUser:saveOrUpdate exception!");
			log.error(e.getMessage());
		}
	}

	public void delOnLineUser(String userid, String channelcode) {
		CsOnlineuser onlineuser=new CsOnlineuser();
		CsOnlineuserKey id=new CsOnlineuserKey();
		try {
		  id.setUserid(userid);
		  id.setChannelcode(channelcode);
		  onlineuser.setId(id);
		  
	      ((OnLineUserDAO)ServiceManager.getInstance()
				  .getService("com.taifook.adminportal.service.OnLineUserService"))
			      .delete(onlineuser);		  
		} catch (Exception e) {
			log.error("ProxyAdminportalImpl-delOnLineUser:delete exception!");
			log.error(e.getMessage());
		}
	}

	public void saveUserAction(String userid, String actionid,String ip) {
		CsUseractionlog user=new CsUseractionlog();		  
		try {
		     user.setUserid(userid);
		     user.setActionid(actionid);
		     user.setIp(ip);
		     user.setOperationtime(new Date());

	      ((UserActionDAO)ServiceManager.getInstance()
				  .getService("com.taifook.adminportal.service.UserActionService"))
			      .save(user); 
		} catch (Exception e) {
			log.error("ProxyAdminportalImpl-saveUserAction:save exception!");
			log.error(e.getMessage());
		}
	}
	
	 
	public void saveSystemMonitor(String servicename,String ip,String status) {
      CsServicemonitor  monitor=new CsServicemonitor();	
	   monitor.setServicename(servicename);
	   monitor.setIp(ip);
	   monitor.setStatus(status);
	   monitor.setAccesstime(new Date());	
          try {
			((SystemMonitorDAO)ServiceManager.getInstance()
			        .getService("com.taifook.adminportal.service.SystemMonitorService"))
			        .save(monitor);
		} catch (Exception e) {
			log.warn("ProxyAdminportalImpl-saveSystemMonitor:save Exception");
			log.warn(e.getMessage());
			e.printStackTrace();
		}
		
	}

}

