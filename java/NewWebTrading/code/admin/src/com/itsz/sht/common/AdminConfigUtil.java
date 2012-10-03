package com.itsz.sht.common;

import java.io.InputStream;
import java.util.Properties;

public class AdminConfigUtil {
	
	private  static AdminConfigUtil adminConfigUtil=null;
	
	private String emailFrom;
	private String emailTarget;
	private String emailHost;
	private String ccisUrl1;
	private String ccisUrl2;
	private String ccisUser;
	private String ccisPass;
	
	public String getCcisUrl1() {
		return ccisUrl1;
	}
	public void setCcisUrl1(String ccisUrl1) {
		this.ccisUrl1 = ccisUrl1;
	}
	public String getCcisUrl2() {
		return ccisUrl2;
	}
	public void setCcisUrl2(String ccisUrl2) {
		this.ccisUrl2 = ccisUrl2;
	}
	public String getCcisUser() {
		return ccisUser;
	}
	public void setCcisUser(String ccisUser) {
		this.ccisUser = ccisUser;
	}
	public String getCcisPass() {
		return ccisPass;
	}
	public void setCcisPass(String ccisPass) {
		this.ccisPass = ccisPass;
	}
	public String getEmailHost() {
		return emailHost;
	}
	private void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	private void setEmailTarget(String emailTarget) {
		this.emailTarget = emailTarget;
	}
	private void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public String getEmailTarget() {
		return emailTarget;
	}
	
	
	private AdminConfigUtil(){
		try {
	  	    InputStream is = getClass().getResourceAsStream("/adminConfig.properties");
	        Properties timeProps = new Properties();
	     	 timeProps.load(is);
	     	setEmailFrom(timeProps.getProperty("emailFrom", ""));
	     	setEmailTarget(timeProps.getProperty("emailTarget", ""));	     	
	     	setEmailHost(timeProps.getProperty("emailHost", ""));
	     	setCcisUrl1(timeProps.getProperty("ccisUrl1", ""));
	     	setCcisUrl2(timeProps.getProperty("ccisUrl2", ""));
	     	setCcisUser(timeProps.getProperty("ccisUser", ""));
	     	setCcisPass(timeProps.getProperty("ccisPass", ""));
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}	
	}
	
	public static AdminConfigUtil getInstance(){
		if(adminConfigUtil==null){
			adminConfigUtil=new AdminConfigUtil();
		}
		return adminConfigUtil;
	}
	Properties timeProps=null;
	
	
	
}
