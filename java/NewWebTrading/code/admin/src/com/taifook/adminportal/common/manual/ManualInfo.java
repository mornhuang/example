/**
 * 
 */
package com.taifook.adminportal.common.manual;

import java.util.Properties;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.parameter.ParameterManager;
import com.taifook.adminportal.common.util.ReaderPropertiesUtil;

/**
 * @author swliu
 *
 */
public class ManualInfo {		
	
	public static final String MANUAL_URL_HOME=Constants.ParamKey.MANUAL_URL_HOME;
	
	public static String getManualHome(){
		
		try{
			String manual_home=ParameterManager.getParameterValueByKey(MANUAL_URL_HOME);
			if(manual_home==null || manual_home.trim().equals("")){
				return "";
			}
			return manual_home;
		}catch(Exception e){
			return "";
		}
	}
		

}
