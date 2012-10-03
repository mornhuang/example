package com.itsz.sht.admin.service.util;

import java.util.HashMap;
import java.util.Map;

public class WebAdminCfgManager {

	private static WebAdminCfgManager manager = new WebAdminCfgManager();

	private Map srvMap = new HashMap();

	public Map getSrvMap(String key) {
		return srvMap;
	}

	public void addParaMap(String key) {
		this.srvMap.put(key, new HashMap());
	}

	public void addParam(String srvMapKey, String paramMapKey, String value) {
		if (this.srvMap.get(srvMapKey) == null) {
			this.addParaMap(srvMapKey);
		}
		((Map) this.srvMap.get(srvMapKey)).put(paramMapKey, value);
	}

	public void addParam(String srvMapKey, String paramMapKey, Object value) {
		if (this.srvMap.get(srvMapKey) == null) {
			this.addParaMap(srvMapKey);
		}
		((Map) this.srvMap.get(srvMapKey)).put(paramMapKey, value);
	}
	
	public String getParamValue(String srvMapKey, String paramMapKey) {
		return (String) ((Map) this.srvMap.get(srvMapKey)).get(paramMapKey);
	}

	public Map getParamMap(String srvMapKey) {
		return (Map) this.srvMap.get(srvMapKey);
	}

	public static WebAdminCfgManager getInstance() {
		return manager;
	}

}
