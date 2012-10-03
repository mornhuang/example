package com.taifook.adminportal.common.util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.proxy.ProxyAdminPortalDAO;
import com.taifook.adminportal.proxy.impl.ProxyAdminportalImpl;
/**
 * 
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public final class SystemMonitor implements ServletContextListener {
	static Log log=LogFactory.getLog(SystemMonitor.class);
	private Timer timer;
	
	public void contextInitialized(ServletContextEvent arg0) {
		timer=new Timer();
		ProxyAdminPortalDAO proxy=new ProxyAdminportalImpl();
		int period=proxy.getSystemMonitorPeriod();
		
//		System.out.println("Listener initial...");
		log.info("Listener initial...");
		timer.schedule(new MonitorWorker(),0,period*60*1000);
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
//		System.out.println("Listener end...");
		log.info("Listener end...");
		timer.cancel();
	}	
 }

