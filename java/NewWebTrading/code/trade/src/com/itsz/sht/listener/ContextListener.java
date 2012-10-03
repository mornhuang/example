/*
 * Created on 2005-8-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.listener;

//import java.io.IOException;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.itsz.sht.admin.broadcast.util.BroadcastParser;
import com.itsz.sht.admin.channel.util.ChannelParser;
import com.itsz.sht.admin.parameter.util.MobileAgentManagement;
import com.itsz.sht.admin.parameter.util.ParaParser;
import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.itsz.sht.admin.service.util.ServiceManagement;
import com.itsz.sht.admin.util.xmlparser;
import com.taifook.adminportal.common.XMLParser;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
///////////////////////////////////////////////////////

public class ContextListener implements ServletContextListener,
		ServletContextAttributeListener {
	
	private static Logger log = LoggerFactory.instance().getLogger(ContextListener.class);
	
	public void contextDestroyed(ServletContextEvent arg) {
		//锟斤拷锟斤拷锟斤拷admin锟斤拷氐锟斤拷锟街撅拷锟较拷锟斤拷锟??
		AdminServiceDelegate.getInstance().processAppStopLog();	
	}

	public void contextInitialized(ServletContextEvent arg) {

		String path = arg.getServletContext().getRealPath("");
		xmlparser.setFilepath(path + "/WEB-INF/classes");
		XMLParser.setFilepath(path + "/WEB-INF/classes");

		try {
			BroadcastParser bp = new BroadcastParser("en_US");
			bp.readFile();
			bp = new BroadcastParser("zh_TW");
			bp.readFile();
			bp = new BroadcastParser("zh_CN");
			bp.readFile();
			
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("error parse BroadcastParser xmlfile");	
		}
		
//		try {
//			log.info("**********Start Load MessageSender Config**********");
//			new MessageSender().initConfig();
//			log.info("**********Load MessageSender Config Success**********");
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("**********Load MessageSender Config Fail**********"+e.getMessage());
//		}

		// 锟斤拷channel锟斤拷息锟絵锟斤拷诖锟??
		try {
			ChannelParser cp = new ChannelParser();
			cp.readChannelInfo();
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("error parse ChannelParser xmlfile");
		}

		// 锟斤拷始锟斤拷锟斤拷锟斤拷状态
		ServiceManagement.init();

		// 锟窖诧拷锟斤拷锟侥硷拷锟絵锟斤拷诖锟??
		try {
			ParaParser pp = new ParaParser();
			pp.initParaTable();
		} catch (Exception e) {
			System.out.println("error parse parameter xmlfile");
		}
		//Mobile Agent init
		try {
			MobileAgentManagement.initAgentSet();
		} catch (Exception e) {
			log.error("error init Mobile Agent");
			log.error(e.getMessage());
		}
		
		//锟斤拷锟斤拷锟斤拷admin锟斤拷氐锟斤拷锟街撅拷锟较拷锟斤拷锟??
		AdminServiceDelegate.getInstance().processAppStartLog();
	}

	public void attributeAdded(ServletContextAttributeEvent arg) {

	}

	public void attributeRemoved(ServletContextAttributeEvent arg) {
	}

	public void attributeReplaced(ServletContextAttributeEvent arg) {
	}

}
