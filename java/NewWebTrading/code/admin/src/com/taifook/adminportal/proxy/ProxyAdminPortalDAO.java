package com.taifook.adminportal.proxy;
/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public interface ProxyAdminPortalDAO {
	public String readParameter(String key);  //from parameter db
	
	public String readWebStatus();
	public String readSttStatus();
	public String read3GStatus();
	
	public boolean isSystemMonitor();   //from system monitor
	public int getSystemMonitorPeriod();
	
	public String readBroadcast(String channelcode);  //from broadcast db
	
	public void saveOnLineUser(String userid,String channelcode,String sessionid);    //from onlineuser db
	public void  delOnLineUser(String userid,String channelcode); 
	
	public void saveUserAction(String userid,String actionid,String ip);  //from useractionlog db
	
	public void saveSystemMonitor(String servicename,String ip,String status);    //from systemmonitor db

}

