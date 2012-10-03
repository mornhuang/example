package com.taifook.adminportal.common;

/**
 * <p> * Title: admin_portal           * </p>
 * <p> * Description:                  * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */

public final class ServiceManager {
	private static ServiceManager me;
	static{
		me=new ServiceManager();
	}
	private ServiceManager(){ }
	
	public static ServiceManager getInstance()
	 {    return me;	} 

	public Object getService(String ServiceName)throws Exception
	{
	  try {
			return Class.forName(ServiceName).newInstance();
		  } catch (Exception ex) {
			     ex.printStackTrace();
			     throw new Exception(ex.getMessage());
		  } 
		
	}
}
