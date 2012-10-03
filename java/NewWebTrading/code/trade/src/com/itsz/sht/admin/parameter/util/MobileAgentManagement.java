package com.itsz.sht.admin.parameter.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.service.MobileAgentMgrService;
import com.itsz.sht.common.Constants;
import com.taifook.adminportal.model.CsSetParameter;

/**
 * $Id: MobileAgentManagement.java,v 1.1 2010/11/09 03:57:17 kyzou Exp $
 * @Project:portal
 * @File:MobileAgentManagement.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class MobileAgentManagement {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	private static Set AgentBlackList=new HashSet<String>();
	private static Set AgentPcBlackList=new HashSet<String>();
	private static Set AgentWhiteList=new HashSet<String>();
	
    public static synchronized void putAgentSet(String name,String value){
    	log_debug.debug("==========put putAgentSet name="+name+" value="+value);
        if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_BLACK_LIST)){
        	AgentBlackList.add(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_PC_BLACK_LIST)){
        	AgentPcBlackList.add(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_WHITE_LIST)){
        	AgentWhiteList.add(compileRex(value));
        }
    }
    
    public static boolean containsKey(String name,String value){
        if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_BLACK_LIST)){
        	return AgentBlackList.contains(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_PC_BLACK_LIST)){
        	return AgentPcBlackList.contains(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_WHITE_LIST)){
        	return AgentWhiteList.contains(compileRex(value));
        }
        return false;
    }
    
    public static synchronized void remove(String name,String value){
        if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_BLACK_LIST)){
        	AgentBlackList.remove(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_PC_BLACK_LIST)){
        	AgentPcBlackList.remove(compileRex(value));
        }else if(name.equals(com.taifook.adminportal.common.Constants.ParamKey.AGENT_WHITE_LIST)){
        	AgentWhiteList.remove(compileRex(value));
        }
    }
    
    public static boolean isWhiteList(String agent){
    	try {
    		Pattern pattern = null;
    		Iterator it=AgentWhiteList.iterator();
    		for(int j=0;it.hasNext();j++){
            	pattern = Pattern.compile(it.next().toString().toUpperCase());
            	Matcher matcher = pattern.matcher(agent);
            	if(matcher.find()){
            		return true;
            	}
    		}		
		} catch (Exception e) {
			log_debug.error(e);
		}
    	return false;
    }
    
    public static boolean isBlackList(String agent){
    	try {
    		Pattern pattern = null;
    		Iterator it=AgentBlackList.iterator();
    		for(int j=0;it.hasNext();j++){
            	pattern = Pattern.compile(it.next().toString().toUpperCase());
            	Matcher matcher = pattern.matcher(agent);
            	if(matcher.find()){
            		return true;
            	}
    		}
		} catch (Exception e) {
			log_debug.error(e);
		}
    	return false;
    }
    
    public static boolean isPcBlackList(String agent){
    	try {
    		Pattern pattern = null;
    		Iterator it=AgentPcBlackList.iterator();
    		for(int j=0;it.hasNext();j++){
            	pattern = Pattern.compile(it.next().toString().toUpperCase());
            	Matcher matcher = pattern.matcher(agent);
            	if(matcher.find()){
            		return true;
            	}
    		}
		} catch (Exception e) {
			log_debug.error(e);
		}
    	return false;
    }
    
	public static void initAgentSet() {
		MobileAgentMgrService service =MobileAgentMgrService.getInstance();
		List parainfos = (List) service.findByAllMobileAgent();
		log_debug.debug("total mobile agent size: "+parainfos.size());
		if (parainfos == null || parainfos.size() < 1) {
			return;
		}
		Iterator it = parainfos.iterator();
		while (it.hasNext()) {
			CsSetParameter para = (CsSetParameter) it.next();
			String name = para.getId().getParamName();
			String value = para.getId().getParamValue();
			if (name != null && !name.equals("")) {				
				putAgentSet(name, value);
			}
		}
	}
	
	public static String compileRex(String baseRex){
		StringBuffer sb = new StringBuffer();
		try {
			String dd[] = StringUtils.split(baseRex,"%");
			sb.append("(^.*");
			for (int i = 0; i < dd.length; i++) {
				sb.append(dd[i]).append(".*");
			}
			sb.append("$)");			
		} catch (Exception e) {
			log_debug.error(e);
		}
		return sb.toString();
	}
}
