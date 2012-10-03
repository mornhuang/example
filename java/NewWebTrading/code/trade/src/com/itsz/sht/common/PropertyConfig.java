/*
 * Created on 2005-3-29
 *
 */
package com.itsz.sht.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.itsz.sht.admin.parameter.util.ParaManagement;
import com.itsz.sht.common.model.response.ConfigInfo;
/**
 * @author wzzhan
 *PropertyConfig.userManagemet
 * set config after server startup
 */
public class PropertyConfig {
	
	public PropertyConfig(){
	}
	public PropertyConfig(String path_file) {

    }
	
	public static String getProviderName(String property){
	    return ParaManagement.getParaValueByKey(property);
	}
	public static String getCommonProperty(String property) {
	    return ParaManagement.getParaValueByKey(property);
    }
	public static Collection getAllCommonProperty() {
		Collection configs = new ArrayList();
		Hashtable hash = ParaManagement.getAllParas();
		Set entries = hash.entrySet();
		Iterator it=entries.iterator();
		while(it.hasNext()){
			Map.Entry entry=(Map.Entry)it.next();
			configs.add(new ConfigInfo((String)entry.getKey(),(String)entry.getValue()));
		}
	    return configs;
    }

}
