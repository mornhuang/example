package com.taifook.adminportal.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.XMLParser;
import com.taifook.adminportal.common.manual.ManualInfo;
import com.taifook.adminportal.common.parameter.SysParameterParser;
import com.taifook.adminportal.common.util.BasePropertiesParser;
import com.taifook.adminportal.common.util.DiskFileUtil;
import com.taifook.adminportal.job.ClearOnlineUserJob;
import com.taifook.common.socket.SynDataCfgParser;

public class ContextListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(ContextListener.class);;

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//
		String path = arg0.getServletContext().getRealPath("");
		XMLParser.setFilepath(path + "/WEB-INF/classes");
		BasePropertiesParser.setBasePath(path + "/WEB-INF/classes");

		try {
			log.info("<<<<<<<<<<<<<<<<<<<<<STARTING GET PARAMETER>>>>>>>>>>>>>>>>>");
			new SysParameterParser().parser();
			log.info("<<<<<<<<<<<<<<<<<<<<<GET PARAMETER SUCCESS>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			log.info("<<<<<<<<<<<<<<<<<<<<<GET PARAMETER OCCUR EXCEPTION>>>>>>>>>>>>>>>>>");
			log.error(e);
		}

		//
		try {
			log.info("<<<<<<<<<<<<<<<<<<<<<STARTING GET SYNDATACONFIG>>>>>>>>>>>>>>>>>");
			new SynDataCfgParser().parserNotifyServerCfg();
			log.info("<<<<<<<<<<<<<<<<<<<<<GET SYNDATACONFIG SUCCESS>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			log.info("<<<<<<<<<<<<<<<<<<<<<GET SYNDATACONFIG OCCUR EXCEPTION>>>>>>>>>>>>>>>>>");
			log.error(e);
		}

		try {
			log.info("<<<<<<<<<<<<<<<<<<<<<STARTING CLEAR ONLINEUSER JOB>>>>>>>>>>>>>>>>>");
			ClearOnlineUserJob.getInstance().start();
			log.info("<<<<<<<<<<<<<<<<<<<<<START CLEAR ONLINEUSER JOB SUCCESS>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			log.info("<<<<<<<<<<<<<<<<<<<<<START CLEAR ONLINEUSER JOB OCCUR EXCEPTION>>>>>>>>>>>>>>>>>");
			log.error(e);
		}

		//
		String tempFileStr = arg0.getServletContext().getRealPath(
				Constants.TEMP_FILE_PATH);
		File tempFilePath = new File(tempFileStr);
		if (!tempFilePath.exists()) {
			tempFilePath.mkdirs();
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  "))
					.format(new Date())
					+ " created application temp directory: "
					+ tempFilePath.toString());
		}
		arg0.getServletContext().setAttribute(Constants.TEMP_FILE_PATH,
				tempFilePath);

		arg0.getServletContext().setAttribute(ManualInfo.MANUAL_URL_HOME,
				ManualInfo.getManualHome());
		log.info("Manual Home URL: "
				+ arg0.getServletContext().getAttribute(
						ManualInfo.MANUAL_URL_HOME));
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		//
		log.info("<<<<<<<<<<<<<<<<<<<<<REMOVE SYSTEM TEMP DRIECTORY>>>>>>>>>>>>>>>>>");
		File tempFilePath = (File) arg0.getServletContext().getAttribute(
				Constants.TEMP_FILE_PATH);
		if (tempFilePath != null) {
			DiskFileUtil.deepDeleteFilePath(tempFilePath);
			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss  "))
					.format(new Date())
					+ " deleted application temp directory: "
					+ tempFilePath.toString());
		}
		log.info("<<<<<<<<<<<<<<<<<<<<<REMOVE SYSTEM TEMP DRIECTORY SUCCESS>>>>>>>>>>>>>>>>>");
		//
		log.info("<<<<<<<<<<<<<<<<<<<<<CLOSE DATABASE CONNECTION>>>>>>>>>>>>>>>>>");
		HibernateBase.closeConnection();
		log.info("<<<<<<<<<<<<<<<<<<<<<DATABASE CONNECTION CLOSED>>>>>>>>>>>>>>>>>");
		
		try {
			log.info("<<<<<<<<<<<<<<<<<<<<<STOPPING GET SYNDATACONFIG>>>>>>>>>>>>>>>>>");
			ClearOnlineUserJob.getInstance().stop();
			log.info("<<<<<<<<<<<<<<<<<<<<<STOP GET SYNDATACONFIG SUCCESS>>>>>>>>>>>>>>>>>");
		} catch (Exception e) {
			log.info("<<<<<<<<<<<<<<<<<<<<<STOP GET SYNDATACONFIG OCCUR SUCCESS>>>>>>>>>>>>>>>>>");
			log.error(e);
		}
		
	}

}
