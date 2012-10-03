package com.taifook.common.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SynDataCfgManager {
	

	private static SynDataCfgManager manager = new SynDataCfgManager();
	
	Log log = LogFactory.getLog(SynDataCfgManager.class);
	
	private Map cfgMap = new HashMap();
	
	private static String CLIENT_ROLE="client";
	
	private static String SERVER_ROLE="server";	

	private SynDataCfgManager() {

	}

	public void addConfig(SynDataCfgInfo sfgInfo) {		
		
		if(sfgInfo!=null){
			this.cfgMap.put(sfgInfo.getServiceName(),sfgInfo);
		
			SynDataCfgInfo sfgInfoOut=(SynDataCfgInfo)this.cfgMap.get(sfgInfo.getServiceName());
			log.info("+++++++++++IP:"+sfgInfo.getIp()+", portal:"+sfgInfo.getPort()+", timout:"+sfgInfo.getTimeout()+", serviceName:"+sfgInfo.getServiceName()+", role:"+sfgInfo.getRole()+"+++++++++++");
		}
	}

	public List getClientConfig() {
		return this.getConfigForRole(CLIENT_ROLE);
	}
	
	public List getServerConfig() {
		return this.getConfigForRole(SERVER_ROLE);
	}	

	public SynDataCfgInfo getConfig(String serviceName) {
		return (SynDataCfgInfo)this.cfgMap.get(serviceName);
	}
	
	private List getConfigForRole(String role){
		List cfgList=new ArrayList();
		Set cfgSet=this.cfgMap.keySet();
		Iterator it=cfgSet.iterator();
		while(it.hasNext()){
			SynDataCfgInfo sfgInfo=(SynDataCfgInfo)this.cfgMap.get(it.next());			
			if(sfgInfo.getRole().equalsIgnoreCase(role)){				
				cfgList.add(sfgInfo);
			}			
		}
		return cfgList;
	}

	public static SynDataCfgManager getInstance() {
		if(manager==null){
			manager = new SynDataCfgManager();
		}
		return manager;
	}
}
