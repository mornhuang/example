package com.itsz.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.parameter.util.ParameterPaser;
import com.itsz.util.database.AppConfig;



public class ContextListener implements ServletContextListener {
	private Log log = LogFactory.getLog(ContextListener.class);

	public void contextInitialized(ServletContextEvent arg0) {	
		
//		String path = arg0.getServletContext().getRealPath("");
//		BasePropertiesParser.setBasePath(path + "/WEB-INF/classes");
        File filePath = new File(arg0.getServletContext().getRealPath(""));
        AppConfig.setBaseAppPath(filePath);
		new ParameterPaser().paser();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
      //  DBConnPoolUtil.shutDownDriver();
        
	}
	
	
	

}
