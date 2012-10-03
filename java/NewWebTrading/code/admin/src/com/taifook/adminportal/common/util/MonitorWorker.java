package com.taifook.adminportal.common.util;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.proxy.ProxyAdminPortalDAO;
import com.taifook.adminportal.proxy.impl.ProxyAdminportalImpl;
/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public final class MonitorWorker extends TimerTask {
	static Log log=LogFactory.getLog(MonitorWorker.class);
	ProxyAdminPortalDAO proxy=new ProxyAdminportalImpl();
	
	public void run() {	
	  try {
		  if(proxy.isSystemMonitor()){
			 String status=getServiceStatus("MCS");              //from Channel Server get Service status.
			 proxy.saveSystemMonitor("MCS","202.198.192.10",status);  //save System monitor in the DB.	
		    }
		} catch (Exception e) {
			log.error("MonitorWorker-run:save Exception");
			log.error(e); }
	}
	
	private String getServiceStatus(String servicename){   //from Channel Server get Service status.
		return Constants.NORMAL_STATUS;
	}
}

