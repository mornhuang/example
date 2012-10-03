package com.itsz.sht.common.model.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.itsz.sht.common.SSOConfig;

public class GlobalParameters {
	
	private Properties props;
	
	private static GlobalParameters gp = new GlobalParameters();
	
	private GlobalParameters() {
		props = new SSOConfig().loadProperties(SSOConfig.PROPERTIES_FILENAME, "the default configuration");
	}
	
	public static GlobalParameters getInstance(){
		return gp;
	}
	
	public String getIDPIPAddr(){
		return props.getProperty("IDPIPADDR");
	}
	
	public List getIDPIPAddrs(){
		List webAddressList = new ArrayList();
		webAddressList.add(props.getProperty("IDPIPADDR"));
		return webAddressList;
	}
        
	public String getEServiceInit(){
		return props.getProperty("ESERVICEInit");
	}
        
	public String getEServiceLogin(){
		return props.getProperty("ESERVICELogin");
	}
        
	public String getEServiceLoginUrl(){
		return props.getProperty("ESERVICELoginUrl");
	}
        
	public int getMaxConnectionsPerHost(){
		return Integer.parseInt(props.getProperty("maxConnectionsPerHost"));
	}
        
	public int getMaxTotalConnections(){
		return Integer.parseInt(props.getProperty("maxTotalConnections"));
	}
        
	public int getHttpClientTimeout(){
		return Integer.parseInt(props.getProperty("httpClientTimeout"));
	}
        
	public int getCnnTimeout(){
		return Integer.parseInt(props.getProperty("cnnTimeout"));
	}
        
	public int getHttpCnnTimeout(){
		return Integer.parseInt(props.getProperty("httpCnnTimeout"));
	}
}
