package com.itsz.Contingency;
import javax.servlet.*;
import java.util.Properties;
import java.io.*;
/**
 *  This is a system startup class which must be instantiated only once
 */
public class SystemStartup implements ServletContextListener {
	public SystemStartup() {

	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		init(sce);
	}

	//Start the schedule task
	public void init(ServletContextEvent sce) {
		String file = sce.getServletContext().getRealPath("config.properties") ;
		//String file = ".." + File.separatorChar + "";
		//System.out.println("File path======"+file);
		try {
			Properties props =new Properties();
			FileInputStream inStream = new FileInputStream(file);
			props.load(inStream);
			ClientMain.refreshMin = props.getProperty("refresh_min") ;
			ClientMain.refreshMax = props.getProperty("refresh_max");
			ClientMain.secStatus = props.getProperty("trade_system_status") ;
			
			ClientMain.eSerStatus = props.getProperty("eService_system_status");
			ClientMain.futStatus = props.getProperty("futures_trade_system_status");
			ClientMain.authPass = props.getProperty("admin_pass") ;
			ClientMain.authUser = props.getProperty("admin_name") ;
			inStream.close() ;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}	

	}

}